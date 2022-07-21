package com.smoothstack.titaniumbanking.dto;

import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
@ToString
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
