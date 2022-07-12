package com.titanium.user.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberResponse {
    private Integer id;
    private String userType;
    private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private LocalDate dateOfBirth;
    private String socialSecurityNumber;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zipcode;
}
