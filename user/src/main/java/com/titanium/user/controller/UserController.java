package com.titanium.user.controller;

import com.titanium.user.dto.*;
import com.titanium.user.exception.*;
import com.titanium.user.model.BankUser;
import com.titanium.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@Api( tags = "Users" )
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    @ResponseStatus(code = HttpStatus.CREATED, reason = "User created")
    public ResponseEntity<String> addUser(@RequestBody @Valid UserRegistration registration, HttpServletRequest request) {
        try {
            String siteURL = request.getRequestURL().toString();
            userService.addUser(registration);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (EmailExistsException | UsernameExistsException e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/member")
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Member created")
    public ResponseEntity<String> addMember(@RequestBody @Valid MemberRegistration registration, HttpServletRequest request) {
        try {
            String siteURL = request.getRequestURL().toString();
            userService.addMember(registration);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (EmailExistsException | UsernameExistsException | SocialSecurityNumberExistsException e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserLogin userLogin) {
        try {
            return new ResponseEntity<>(userService.login(userLogin), HttpStatus.CREATED);
        }
        catch (InvalidPasswordException | InvalidUsernameException | UserNotVerifiedException e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Returns all users")
    @GetMapping("/user")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<List<BankUser>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }
}
