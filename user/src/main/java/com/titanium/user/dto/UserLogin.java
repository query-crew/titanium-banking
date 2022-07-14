package com.titanium.user.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserLogin {

    @NotBlank(message = "Username is required.")
    private String username;

    @NotBlank(message = "Password is required.")
    private String password;
}
