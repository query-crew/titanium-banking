package com.titanium.user.dto;

import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class MemberRegistration {
    @NotBlank(message = "Email is required.")
    @Email(message = "Email is not a valid email.")
    private String email;

    @NotBlank(message = "Username is required.")
    private String username;

    @NotBlank(message = "Password is required.")
    private String password;

    @NotBlank(message = "First name is required.")
    private String firstName;

    @NotBlank(message = "Last name is required.")
    private String lastName;

    @NotBlank(message = "Phone is required.")
    private String phone;

    @NotNull(message="Date of birth is required.")
    private LocalDate dateOfBirth;

    @NotNull(message="Social security number is required.")
    private String socialSecurityNumber;

    @NotNull(message="Address line 1 is required.")
    private String addressLine1;

    private String addressLine2;

    @NotNull(message="City is required.")
    private String city;

    @NotNull(message="State is required.")
    private String state;

    @NotNull(message="Zipcode is required.")
    private String zipcode;
}
