package com.titanium.user.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class MemberRegistration {
    @NotBlank(message = "Email is required.")
    @Email(message = "Email is not a valid email.")
    private String email;

    @Max(value = 100, message = "Username must be less than 100 characters.")
    @Pattern(regexp="^[a-zA-Z\\d_.-]+$", message="Username can only contain alphanumeric characters, underscores, dashes, and dots.")
    @NotBlank(message = "Username is required.")
    private String username;

    @Size(min=8, max=100, message = "Password must be between 8 and 100 characters")
    @Pattern(regexp="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[a-zA-Z\\d]{8,100}$", message="Password must contain one digit, " +
            "one uppercase letter, and one lowercase letter. Special characters are not allowed.")
    @NotBlank(message = "Password is required.")
    private String password;

    @Max(value = 200, message="First name must be less than 200 characters.")
    @Pattern(regexp="^[^!@#$%^&*(),.?\":{}|<>//d]+$", message="First name cannot contain special characters or numbers")
    @NotBlank(message = "First name is required.")
    private String firstName;

    @Max(value = 200, message="First name must be less than 200 characters.")
    @Pattern(regexp="^[^!@#$%^&*(),.?\":{}|<>//d]+$", message="Last name cannot contain special characters or numbers")
    @NotBlank(message = "Last name is required.")
    private String lastName;

    @Size(max = 13, min = 13)
    @Pattern(regexp="^(\\(\\d{3}\\)\\d{3}-\\d{4})$")
    @NotBlank(message = "Phone is required.")
    private String phone;

    // Check date in service to make sure people can't be some crazy age like 300 or -100 or something
    @Size(max = 10, min = 10)
    @Pattern(regexp="^(\\d{4}-\\d{2}-\\d{2})$")
    @NotNull(message="Date of birth is required.")
    private LocalDate dateOfBirth;

    @Size(max = 11, min = 11)
    @Pattern(regexp="^\\d{3}-\\d{2}-\\d{4}$")
    @NotNull(message="Social security number is required.")
    private String socialSecurityNumber;

    @Size(max=46)
    @Pattern(regexp="^[\\da-z-A-Z.-]+$")
    @NotNull(message="Address line 1 is required.")
    private String addressLine1;

    @Size(max=46)
    @Pattern(regexp="^(?![\\s\\S])|[\\da-z-A-Z.-]+$")
    private String addressLine2;

    @Size(max=100)
    @Pattern(regexp = "^[a-zA-Z]+$")
    @NotNull(message="City is required.")
    private String city;

    @Size(max=100)
    @Pattern(regexp = "^[a-zA-Z]+$")
    @NotNull(message = "State is required.")
    private String state;

    @Size(max=10, min=5)
    @Pattern(regexp = "^(\\d{5}-\\d{4}|\\d{5}-\\d{0,4})$")
    @NotNull(message ="Zipcode is required.")
    private String zipcode;
}