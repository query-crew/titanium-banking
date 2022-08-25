package com.smoothstack.titaniumbanking.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class AccountTypeDto {

    @NotBlank(message = "Account type can not be empty")
    private String accountType;

    @NotBlank(message = "Account type abbreviation")
    private String accountTypeAbbr;

    @NotNull(message = "Interest can not be empty.")
    private Integer interest;

    @NotNull(message = "Balance requirement can not be empty.")
    private Long balanceRequirement;

    private Integer loanId;
}
