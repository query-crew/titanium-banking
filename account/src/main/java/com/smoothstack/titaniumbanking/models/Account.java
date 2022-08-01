package com.smoothstack.titaniumbanking.models;


import java.time.LocalDate;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name ="Accounts")
@JsonInclude(JsonInclude.Include.ALWAYS)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="account_id", unique = true, nullable = false)
    private int accountId;

    @Column(name="account_name")
    private String accountName;

    @Column(name="account_type")
    private String accountType;

    @Column(name="account_number")
    private String accountNumber;

    @Column(name="balance")
    private Integer balance;

    @Column(name="interest")
    private Integer interest;

    @Column(name="last_statement_date")
    private LocalDate lastStatementDate;

    @Column(name="payment_date")
    private LocalDate paymentDate;

    @Override
    public boolean equals(Object o) {
            if(getClass() != o.getClass()){
                return false;
            }
            Account account = (Account) o;
            return (
                    accountName.equals(account.getAccountName()) &&
                            accountType.equals(account.getAccountType()) &&
                    accountNumber.equals(account.getAccountNumber()) &&
                            balance.equals(account.getBalance()) &&
                            interest.equals(account.getInterest())
                    );
    }

    public Account(String accountName, String accountType, String accountNumber, Integer balance, Integer interest, LocalDate lastStatementDate, LocalDate paymentDate){
        super();
        this.accountName = accountName;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.interest = interest;
        this.lastStatementDate = lastStatementDate;
        this.paymentDate = paymentDate;
    }

}
