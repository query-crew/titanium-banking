package com.smoothstack.titaniumbanking.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.*;

@Data
@NoArgsConstructor
public class AccountDto {
    //private int accountId;
    //account name should not be null or empty
    @NotBlank(message = "Account name should not be empty")
    @Size(min = 8, max = 25)
    private String accountName;
    //account type should not be null or empty
    @NotBlank(message = "Account type should not be empty")
    private String accountType;
    //account number should not be null or empty
    //account number should have a min of 5 digits and a max of 17 digits
    @NotBlank(message = "Account number should not be empty")
    @Size(min = 5, max = 17)
    private String accountNumber;
    //balance should not be null
    @NotNull(message = "balance may not be null")
    private int balance;
    //interest should not be null
    @NotNull(message = "interest may not be null")
    private int interest;
    private LocalDate lastStatementDate;
    private LocalDate paymentDate;
}
