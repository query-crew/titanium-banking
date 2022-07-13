package com.titanium.user.dto;

import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserRegistration {

    @NotBlank(message = "User type is required.")
    private String userType;

    @NotBlank(message = "Email is required.")
    @Email(message = "Email is not a valid email.")
    private String email;

    @NotBlank(message = "Username is required.")
    private String username;

    @NotBlank(message = "Password is required.")
    private String password;
}
