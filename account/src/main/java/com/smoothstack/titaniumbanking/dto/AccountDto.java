package com.smoothstack.titaniumbanking.dto;

import java.time.LocalDate;

import lombok.*;

@Data
@NoArgsConstructor
public class AccountDto {
    private int accountId;
    private String accountName;
    private String accountNumber;
    private int balance;
    private int interest;
    private LocalDate lastStatementDate;
    private LocalDate paymentDate;
}
