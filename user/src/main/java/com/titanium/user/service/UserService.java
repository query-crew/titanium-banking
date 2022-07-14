package com.titanium.user.service;

import com.titanium.user.dto.*;
import com.titanium.user.exception.EmailExistsException;
import com.titanium.user.exception.SocialSecurityNumberExistsException;
import com.titanium.user.exception.UsernameExistsException;
import com.titanium.user.model.Member;
import com.titanium.user.model.MemberAddress;
import com.titanium.user.model.BankUser;
import com.titanium.user.model.UserToken;
import com.titanium.user.repository.MemberRepository;
import com.titanium.user.repository.UserRepository;
import com.titanium.user.repository.UserTokenRepository;
import com.titanium.user.security.JwtUtils;
import com.titanium.user.security.UserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import javax.validation.Valid;
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
        if (memberRepo.existsBySocialSecurityNumber(registration.getSocialSecurityNumber()))
            throw new SocialSecurityNumberExistsException();
        BankUser user = new BankUser("member", registration.getEmail(), registration.getUsername(), passwordEncoder().encode(registration.getPassword()));
        Member member = new Member(registration.getFirstName(), registration.getLastName(), registration.getPhone(), registration.getDateOfBirth(), registration.getSocialSecurityNumber());
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
            return "invalid_username_or_user";
        }
        BankUser bankUser = userRepo.findByUsername(login.getUsername());
        if (!passwordEncoder().matches(login.getPassword(), bankUser.getPassword())) {
            return "invalid_password";
        }
        try {
            authenticate(login.getUsername(), login.getPassword());
        }
        catch (Exception e) {
            return e.toString();
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(login.getUsername());
        String token = jwtUtils.generateJwtToken(userDetails);
        return token;
    }

    private UserToken getUserToken() {
        String verifyCode;
        do {
            verifyCode = UUID.randomUUID().toString();
        }
        while (tokenRepo.existsByConfirmationToken(verifyCode));
        return new UserToken(verifyCode, LocalDateTime.now());
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
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

    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
