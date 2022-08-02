package com.titanium.user.service;

import com.titanium.user.dto.*;
import com.titanium.user.exception.*;
import com.titanium.user.model.Member;
import com.titanium.user.model.MemberAddress;
import com.titanium.user.model.BankUser;
import com.titanium.user.model.UserToken;
import com.titanium.user.repository.MemberRepository;
import com.titanium.user.repository.UserRepository;
import com.titanium.user.repository.UserTokenRepository;
import com.titanium.user.security.AuthTokenFilter;
import com.titanium.user.security.JwtUtils;
import com.titanium.user.security.UserDetailsService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import javax.servlet.http.Cookie;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepo;
    private final UserTokenRepository tokenRepo;
    private final MemberRepository memberRepo;

    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;


    public BankUser addUser(@Valid UserRegistration registration) {
        if (userRepo.existsByUsername(registration.getUsername()))
            throw new UsernameExistsException();

        if (userRepo.existsByEmail(registration.getEmail()))
            throw new EmailExistsException();

        BankUser user = modelMapper.map(registration, BankUser.class);
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        UserToken token = getUserToken();
        token.setBankUser(user);
        user.setToken(token);
        userRepo.save(user);
        return user;
    }

    public Member addMember(@Valid MemberRegistration registration) {
        if (userRepo.existsByEmail(registration.getEmail())) {
            throw new EmailExistsException();
        }
        if (userRepo.existsByUsername(registration.getUsername())) {
            throw new UsernameExistsException();
        }
        if (memberRepo.existsBySocialSecurityNumber(registration.getSocialSecurityNumber())) {
            throw new SocialSecurityNumberExistsException();
        }
        BankUser user = new BankUser("member", registration.getEmail(), registration.getUsername(), passwordEncoder().encode(registration.getPassword()));
        Member member = new Member(registration.getFirstName(), registration.getLastName(), registration.getPhone(), LocalDate.parse(registration.getDateOfBirth()), registration.getSocialSecurityNumber());
        MemberAddress address = new MemberAddress(registration.getAddressLine1(), registration.getAddressLine2(), registration.getCity(), registration.getState(), registration.getZipcode());
        UserToken token = getUserToken();
        address.setMember(member);
        member.setMemberAddress(address);
        member.setBankUser(user);
        user.setMember(member);
        token.setBankUser(user);
        user.setToken(token);
        userRepo.save(user);
        return member;
    }

    public String login(@Valid UserLogin login) {
        if (!userRepo.existsByUsername(login.getUsername())) {
            throw new InvalidUsernameException();
        }
        BankUser bankUser = userRepo.findByUsername(login.getUsername());
        if (!passwordEncoder().matches(login.getPassword(), bankUser.getPassword())) {
            throw new InvalidPasswordException();
        }
        if (bankUser.getEnabled() == 0) {
            throw new UserNotVerifiedException();
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(login.getUsername());
        String token = jwtUtils.generateJwtToken(userDetails);
        return jwtUtils.packageJwtToken(token);
    }

    public String getUserType(String username) {
        if (!userRepo.existsByUsername(username)) {
            throw new InvalidUsernameException();
        }
        BankUser bankUser = userRepo.findByUsername(username);
        if (bankUser.getEnabled() == 0) {
            throw new UserNotVerifiedException();
        }
        return bankUser.getUserType();
    }

    private UserToken getUserToken() {
        String verifyCode;
        do {
            verifyCode = UUID.randomUUID().toString();
        }
        while (tokenRepo.existsByConfirmationToken(verifyCode));
        return new UserToken(verifyCode, LocalDateTime.now());
    }

    public boolean userExists() {
        return userRepo.count() != 0;
    }

    public void deleteAllUsers() {
        userRepo.deleteAll();
    }

    public List<BankUser> getUsers() {
        return userRepo.findAll();
    }

    public BankUser setEnabled(int id) {
        BankUser user = userRepo.findByUserId(id);
        user.setEnabled(1);
        userRepo.save(user);
        return user;
    }

    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
