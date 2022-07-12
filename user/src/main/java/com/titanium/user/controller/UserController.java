package com.titanium.user.controller;

import com.titanium.user.dto.MemberRegistration;
import com.titanium.user.dto.MemberResponse;
import com.titanium.user.dto.UserRegistration;
import com.titanium.user.dto.UserResponse;
import com.titanium.user.model.User;
import com.titanium.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public List<UserResponse> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/user")
    public void addUser(@RequestBody @Valid UserRegistration registration, HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        userService.addUser(registration, siteURL.replace(request.getServletPath(), ""));
    }

    @PostMapping("/member")
    public void addMember(@RequestBody @Valid MemberRegistration registration, HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        userService.addMember(registration, siteURL.replace(request.getServletPath(), ""));
    }

    @GetMapping("/user/{id}")
    public UserResponse getUser(@PathVariable int id) {
        return userService.getUserResponseById(id);
    }

    @GetMapping("user/{id}/member")
    public MemberResponse getMember(@PathVariable int id) {
        return userService.mapMemberResponse(userService.getUserById(id).getMember());
    }

    @GetMapping("member/{id}/user")
    public UserResponse getMemberUser(@PathVariable int id) {
        return userService.mapUserResponse(userService.getMemberById(id).getBankUser());
    }

    @GetMapping("/user/{id}/verify")
    public String verifyUser(@PathVariable int id) {
        return userService.verifyUser(id);
    }

    @PutMapping("/user/{id}/verify")
    public void resendVerifyEmail(@PathVariable int id, HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        userService.resendVerificationEmail(id, siteURL.replace(request.getServletPath(), ""));
    }
}
