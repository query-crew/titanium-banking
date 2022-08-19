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
@Table(name="account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="accountId", unique = true, nullable = false)
    private int accountId;

    @Column(name="accountNumber", unique = true, nullable = false)
    private String accountNumber;

    @Column(name="balance")
    private Long balance;

    @Column(name="lastStatementDate")
    private LocalDate lastStatementDate;

    @Column(name="enabled")
    private int enabled;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountType", referencedColumnName = "accountTypeId")
    @JsonManagedReference
    private AccountType accountType;

    @Column(name="memberId", nullable=false)
    private int memberId;

    @Override
    public boolean equals(Object o) {
            if(getClass() != o.getClass()){
                return false;
            }
            Account account = (Account) o;
            return (
                            accountNumber.equals(account.getAccountNumber()) &&
                            balance.equals(account.getBalance()) &&
                                    enabled == account.getEnabled() &&
                            memberId == account.getMemberId() &&
                                    accountType.equals(accountType)
                    );
    }

    public Account(String accountNumber, Long balance, LocalDate lastStatementDate, int enabled, int memberId){
        super();
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.lastStatementDate = lastStatementDate;
        this.enabled = enabled;
        this.memberId = memberId;
    }

}
