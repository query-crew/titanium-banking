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

    @Size(min = 8, max = 25)
    @NotBlank(message = "Password is required.")
    private String password;

    @Size(max = 100)
    @NotBlank(message = "First name is required.")
    private String firstName;

    @Size(max = 100)
    @NotBlank(message = "Last name is required.")
    private String lastName;

    @Size(min = 7, max = 15)
    @NotBlank(message = "Phone is required.")
    private String phone;

    @Size(min = 8, max = 10)
    @NotNull(message="Date of birth is required.")
    private LocalDate dateOfBirth;

    @Size(min = 9, max = 11)
    @NotNull(message="Social security number is required.")
    private String socialSecurityNumber;

    @Size(max = 250)
    @NotNull(message="Address line 1 is required.")
    private String addressLine1;

    @Size(max = 250)
    private String addressLine2;

    @Size(max = 150)
    @NotNull(message="City is required.")
    private String city;

    @Size(max = 150)
    @NotNull(message="State is required.")
    private String state;

    @Size(min = 5, max=10)
    @NotNull(message="Zipcode is required.")
    private String zipcode;
}
