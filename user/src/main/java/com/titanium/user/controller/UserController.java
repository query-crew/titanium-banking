package com.titanium.user.controller;

import com.titanium.user.dto.*;
import com.titanium.user.model.BankUser;
import com.titanium.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public void addUser(@RequestBody @Valid UserRegistration registration, HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        userService.addUser(registration);
    }

    @PostMapping("/member")
    public void addMember(@RequestBody @Valid MemberRegistration registration, HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        userService.addMember(registration);
    }

    @PostMapping("/user/login")
    public String login(@RequestBody @Valid UserLogin userLogin) {
        return userService.login(userLogin);
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('admin')")
    public List<BankUser> getUsers() {
        return userService.getUsers();
    }
}
