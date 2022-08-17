package com.titanium.user.dto;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class MemberConfirmation {
    @Size(max = 200, message="First name must be less than 200 characters.")
    @Pattern(regexp="^[^!@#$%^&*(),.?\":{}|<>//d]+$", message="First name cannot contain special characters or numbers.")
    @NotBlank(message = "First name is required.")
    private String firstName;

    @Size(max = 200, message="Last name must be less than 200 characters.")
    @Pattern(regexp="^[^!@#$%^&*(),.?\":{}|<>//d]+$", message="Last name cannot contain special characters or numbers.")
    @NotBlank(message = "Last name is required.")
    private String lastName;

    @Size(max = 13, min = 13)
    @Pattern(regexp="^(\\(\\d{3}\\)\\d{3}-\\d{4})$", message="Phone must be formatted: (###)###-####")
    @NotBlank(message = "Phone is required.")
    private String phone;

    @Size(max = 10, min = 10)
    @Pattern(regexp="^(\\d{4}-\\d{2}-\\d{2})$", message = "Date of birth must be formatted: ####-##-##")
    @NotNull(message="Date of birth is required.")
    private String dateOfBirth;

    // @Size(max = 11, min = 11)
    // @Pattern(regexp="^\\d{3}-\\d{2}-\\d{4}$", message = "Social security number must be formatted: ###-##-####")
    // @NotNull(message="Social security number is required.")
    private String socialSecurityNumber;

    @Size(max=46)
    @Pattern(regexp="^[\\da-z-A-Z\\s.-]+$", message = "Address must be alphanumeric and can only contain dots and dashes.")
    @NotNull(message="Address line 1 is required.")
    private String addressLine1;

    @Size(max=46)
    @Pattern(regexp="^(?![\\s\\S])|[\\da-z-A-Z.-]+$", message = "Address must be alphanumeric and can only contain dots and dashes.")
    private String addressLine2;

    @Size(max=100)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "City must be alphanumeric.")
    @NotNull(message="City is required.")
    private String city;

    @Size(max=100)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "State must be alphanumeric.")
    @NotNull(message="State is required.")
    private String state;

    @Size(max=10, min=5)
    @Pattern(regexp = "^(\\d{5}-\\d{4}|\\d{5}-\\d{0,4}|\\d{5})$", message = "Zipcode must be formatted: #####-####")
    @NotNull(message="Zipcode is required.")
    private String zipcode;
}
