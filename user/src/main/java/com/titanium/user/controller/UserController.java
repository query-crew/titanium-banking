package com.titanium.user.controller;

import com.titanium.user.dto.*;
import com.titanium.user.exception.*;
import com.titanium.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.security.Principal;
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
        } catch (EmailExistsException | UsernameExistsException e) {
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
        } catch (EmailExistsException | UsernameExistsException | SocialSecurityNumberExistsException e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserLogin userLogin, HttpServletResponse response) {
        try {
            response.addHeader("Set-Cookie", userService.login(userLogin));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (InvalidPasswordException | InvalidUsernameException | UserNotVerifiedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/authorize")
    public ResponseEntity<String> authorizeUser(Principal principal) {
        try {
            return new ResponseEntity<>(userService.getUserType(principal.getName()), HttpStatus.OK);
        }
        catch (InvalidUsernameException | UserNotVerifiedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
