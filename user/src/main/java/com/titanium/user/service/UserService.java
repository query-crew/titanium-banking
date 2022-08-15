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
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.*;


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


    public String getMember(String username, int id, MemberConfirmation user) {
        if (!userRepo.existsByUsername(username)) {
            throw new InvalidUsernameException();
        }
        BankUser bankUser = userRepo.findByUserId(id);
        Member member = bankUser.getMember();
        MemberAddress address = member.getMemberAddress();
        if (!member.getFirstName().equals(user.getFirstName())) {
            throw new FirstNameNotFoundException();
        }
        if (!member.getLastName().equals(user.getLastName())) {
            throw new LastNameNotFoundException();
        }
        if (!member.getPhone().equals(user.getPhone())) {
            throw new PhoneNotFoundException();
        }
        if (!member.getDateOfBirth().toString().equals(user.getDateOfBirth())) {
            throw new DateOfBirthNotFoundException();
        }
        // if (!member.getSocialSecurityNumber().equals(user.getSocialSecurityNumber())) {
        //     throw new SocialSecurityNumberExistsException();
        // }
        if (!address.getAddressLine1().equals(user.getAddressLine1())) {
            throw new AddressLineOneNotFoundException();
        }
        if (!address.getAddressLine2().equals(user.getAddressLine2())) {
            throw new AddressLineTwoNotFoundException();
        }
        if (!address.getCity().equals(user.getCity())) {
            throw new CityNotFoundException();
        }
        if (!address.getState().equals(user.getState())) {
            throw new StateNotFoundException();
        }
        if (!address.getZipCode().equals(user.getZipcode())) {
            throw new ZipcodeNotFoundException();
        }
        List<String> userInfo = new ArrayList<String>();
        // userInfo.add(String(member.getMemberId()));
        userInfo.add(member.getFirstName());
        userInfo.add(member.getLastName());
        userInfo.add(member.getPhone());
        userInfo.add(member.getDateOfBirth().toString());
        userInfo.add(member.getMemberAddress().getAddressLine1());
        userInfo.add(member.getMemberAddress().getAddressLine2());
        userInfo.add(member.getMemberAddress().getCity());
        userInfo.add(member.getMemberAddress().getState());
        userInfo.add(member.getMemberAddress().getZipCode());
        return userInfo.toString();
    }
    
    public String getMemberId(String username) {
        if (!userRepo.existsByUsername(username)) {
            throw new InvalidUsernameException();
        }
        BankUser bankUser = userRepo.findByUsername(username);
        if (bankUser.getEnabled() == 0) {
            throw new UserNotVerifiedException();
        }
        Member member = bankUser.getMember();
        if (member == null) {
            throw new MemberNotFoundException();
        }
        StringBuilder builder = new StringBuilder();
        builder.append(member.getMemberId());
        return builder.toString();
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

    public Member getMemberById(int memberId) throws MemberNotFoundException{
        Member member = memberRepo.findById(memberId);
        if (member == null) {
            throw new MemberNotFoundException();
        }
        return member;
    }

    public List<Member> getMembers() {
        List<Member> members = memberRepo.findAll();
        return members;
    }

    public List<Map<Integer, String>> getMemberNames() {
        List<Member> members = memberRepo.findAll();
        List<Map<Integer, String>> memberNames = new ArrayList<>();
        for (Member member : members) {
            HashMap<Integer, String> map = new HashMap();
            StringBuilder builder = new StringBuilder();
            builder.append(member.getFirstName());
            builder.append(" ");
            builder.append(member.getLastName());
            map.put(member.getMemberId(), builder.toString());
            memberNames.add(map);
        }
        return memberNames;
    }

    public String getMemberNameById(int memberId) throws MemberNotFoundException{
        Member member = memberRepo.findById(memberId);
        if (member == null) {
            throw new MemberNotFoundException();
        }
        StringBuilder builder = new StringBuilder();
        builder.append(member.getFirstName());
        builder.append(" ");
        builder.append(member.getLastName());
        return builder.toString();
    }

    public List<Integer> getMemberIdByFullName(String firstName, String lastName) throws MemberNotFoundException {
        List<Member> members = memberRepo.findAllByFirstNameAndLastName(firstName, lastName);
        List<Integer> memberIds = new ArrayList<>();
        for (Member member : members) {
            memberIds.add(member.getMemberId());
        }
        return memberIds;
    }

    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
