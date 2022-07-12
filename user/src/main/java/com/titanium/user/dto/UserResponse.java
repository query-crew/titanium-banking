package com.titanium.user.dto;

import lombok.Data;

@Data
public class UserResponse {
    private Integer id;
    private String userType;
    private String email;
    private String username;
    private String password;
}
