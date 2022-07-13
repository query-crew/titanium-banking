package com.titanium.user.service;

import com.titanium.user.dto.*;
import com.titanium.user.exception.EmailExistsException;
import com.titanium.user.exception.SocialSecurityNumberExistsException;
import com.titanium.user.exception.UsernameExistsException;
import com.titanium.user.model.Member;
import com.titanium.user.model.MemberAddress;
import com.titanium.user.model.User;
import com.titanium.user.model.UserToken;
import com.titanium.user.repository.MemberAddressRepository;
import com.titanium.user.repository.MemberRepository;
import com.titanium.user.repository.UserRepository;
import com.titanium.user.repository.UserTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepo;
    private final UserTokenRepository tokenRepo;
    private final MemberRepository memberRepo;

    // Create
    public User addUser(@Valid UserRegistration registration) {
        if (userRepo.existsByUsername(registration.getUsername()))
            throw new UsernameExistsException();

        if (userRepo.existsByEmail(registration.getEmail()))
            throw new EmailExistsException();

        User user = modelMapper.map(registration, User.class);
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
        User user = new User("member", registration.getEmail(), registration.getUsername(), passwordEncoder().encode(registration.getPassword()));
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
    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
