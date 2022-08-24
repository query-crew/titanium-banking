package com.smoothstack.titaniumbanking.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class InitialDepositAccountDto {

    @NotNull(message="Balance can not be empty.")
    private Long balance;

    @Pattern(regexp="^(\\d{4}-\\d{2}-\\d{2})$", message = "Last statement date must be formatted: YYYY-MM-DD")
    private String lastStatementDate;

    private String paymentMethodId;

    private Integer memberId;

    private Integer accountTypeId;
}
