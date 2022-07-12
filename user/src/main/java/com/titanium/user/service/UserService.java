package com.titanium.user.service;

import com.titanium.user.dto.MemberRegistration;
import com.titanium.user.dto.MemberResponse;
import com.titanium.user.dto.UserRegistration;
import com.titanium.user.dto.UserResponse;
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
    private final MemberAddressRepository addressRepo;
    private final JavaMailSender mailSender;

    // Create
    public User addUser(@Valid UserRegistration registration, String siteURL) {
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
        sendVerificationEmail(user, siteURL);
        return user;
    }

    public Member addMember(@Valid MemberRegistration registration, String siteURL) {
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
        sendVerificationEmail(user, siteURL);
        return member;
    }
    // Read
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserResponse> userResponses = new ArrayList<>();
        UserResponse response;
        for (User user : users) {
            response = mapUserResponse(user);
            userResponses.add(response);
        }
        return userResponses;
    }

    public User getUserById(int id) {
        return userRepo.findByUserId(id);
    }

    public Member getMemberById(int id) {
        return memberRepo.findById(id);
    }

    public UserResponse getUserResponseById(int id) {
        return mapUserResponse(userRepo.findByUserId(id));
    }

    // Update
    // Delete

    public UserResponse mapUserResponse(User user) {
        return modelMapper.map(user, UserResponse.class);
    }

    public MemberResponse mapMemberResponse(Member member) {
        return modelMapper.map(member, MemberResponse.class);
    }


    private UserToken getUserToken() {
        String verifyCode;
        do {
            verifyCode = UUID.randomUUID().toString();
        }
        while (tokenRepo.existsByConfirmationToken(verifyCode));
        return new UserToken(verifyCode, LocalDateTime.now());
    }
    private void sendVerificationEmail(User user, String siteURL) {
        String bankingEmailAddress = "titaniumbanking@gmail.com";
        String bankingCompanyName = "Titanium Banking";
        String subject = "Verify Your Email";
        String url = siteURL + "/verify?code=" + user.getToken().getConfirmationToken();
        String clientName;
        try {
            clientName = user.getUserType().equals("member") ? user.getMember().getFirstName() : "user";
        }
        catch (NullPointerException e){
            // Log member user has no member
            clientName = "user";
        }
        String emailContent = "Dear " + clientName + ",<br>" +
                "Please click the link below to verify your registration:<br>" +
                "<h3><a href=\"" + url + "\" target=\"_self\">VERIFY</a></h3>" +
                "Thank you,<br>" +
                bankingCompanyName + ".";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom(bankingEmailAddress, bankingCompanyName);
            helper.setTo(user.getEmail());
            helper.setSubject(subject);
            helper.setText(emailContent, true);
            mailSender.send(message);
        }
        catch (UnsupportedEncodingException | MessagingException e) {
            // Logger
        }
    }

    public void resendVerificationEmail(int id, String siteURL) {
        User user = userRepo.findByUserId(id);
        UserToken tokenToDelete = user.getToken();
        tokenRepo.delete(tokenToDelete);
        UserToken token = getUserToken();
        token.setBankUser(user);
        user.setToken(token);
        userRepo.save(user);
        sendVerificationEmail(user, siteURL);
    }

    public String verifyUser(int id) {
        User user = userRepo.findByUserId(id);
        UserToken token = user.getToken();
        if (tokenExpired(token)) {
            return "resend_verification_email";
        }
        else if (user.getEnabled() == 1){
            return "user_already_verified";
        }
        else {
            user.setToken(null);
            tokenRepo.delete(token);
            user.setEnabled(1);
            userRepo.save(user);
            return "verified";
        }
    }

    private boolean tokenExpired(UserToken token) {
        return token.getDateCreated().isAfter(LocalDateTime.now().plusHours(24));
    }

    public void deleteAllUsers() {
        userRepo.deleteAll();
    }

    public boolean userExists() {
        return userRepo.count() != 0;
    }

    public void updateToken(int id, UserToken token) {
        UserToken tokenToUpdate = tokenRepo.findById(id);
        tokenToUpdate.setDateCreated(token.getDateCreated());
        tokenToUpdate.setConfirmationToken(token.getConfirmationToken());
        tokenRepo.save(tokenToUpdate);
    }

    public void updateUser(int id, User user) {
        User userToUpdate = userRepo.findByUserId(id);
        userToUpdate.setUserType(user.getUserType());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setEnabled(user.getEnabled());
        userRepo.save(userToUpdate);
    }

    public List<MemberResponse> getAllMembers() {
        List<Member> members = memberRepo.findAll();
        List<MemberResponse> memberResponses = new ArrayList<>();
        MemberResponse response = new MemberResponse();
        for (Member member : members) {
            response.setId(member.getMemberId());
            response.setUsername(member.getBankUser().getUsername());
            response.setPassword(member.getBankUser().getPassword());
            response.setEmail(member.getBankUser().getEmail());
            response.setUserType(member.getBankUser().getUserType());
            response.setFirstName(member.getFirstName());
            response.setLastName(member.getLastName());
            response.setAddressLine1(member.getMemberAddress().getAddressLine1());
            response.setAddressLine2(member.getMemberAddress().getAddressLine2());
            response.setCity(member.getMemberAddress().getCity());
            response.setState(member.getMemberAddress().getState());
            response.setDateOfBirth(member.getDateOfBirth());
            response.setPhone(member.getPhone());
            response.setSocialSecurityNumber(member.getSocialSecurityNumber());
            response.setZipcode(member.getMemberAddress().getZipCode());
            memberResponses.add(response);
        }
        return memberResponses;
    }

    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
