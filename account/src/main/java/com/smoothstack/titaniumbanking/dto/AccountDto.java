package com.smoothstack.titaniumbanking.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.*;

@Data
@NoArgsConstructor
public class AccountDto {

    @NotNull(message="Balance can not be empty.")
    private Long balance;

    @Pattern(regexp="^(\\d{4}-\\d{2}-\\d{2})$", message = "Last statement date must be formatted: YYYY-MM-DD")
    private String lastStatementDate;

    private int enabled;

    private Integer memberId;

    private Integer accountTypeId;
}
