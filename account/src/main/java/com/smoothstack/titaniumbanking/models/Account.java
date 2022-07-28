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
    @Column(name="account_id", nullable = false)
    private int accountId;

    @Column(name="accountName")
    private String accountName;

    @Column(name="accountNumber")
    private String accountNumber;

    @Column(name="balance")
    private int balance;

    @Column(name="interest")
    private int interest;

    @Column(name="lastStatementDate")
    private LocalDate lastStatementDate;

    @Column(name="paymentDate")
    private LocalDate paymentDate;

    @Column(name="accountTypeId")
    private Integer accountTypeId;

    // @ManyToOne(mappedBy="User_Id")
    // private User user;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="accountTypeId", referencedColumnName = "account_type_id", insertable = false, updatable = false)
    @JsonManagedReference
    private AccountType accountType;

    @Override
    public boolean equals(Object o) {
            if(getClass() != o.getClass()){
                return false;
            }
            Account account = (Account) o;
            return (accountName.equals(account.getAccountName()) &&
                    accountNumber.equals(account.getAccountNumber()));
    }

    public Account(String accountName, String accountNumber, int balance, int interest, LocalDate lastStatementDate, LocalDate paymentDate){
        super();
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.interest = interest;
        this.lastStatementDate = lastStatementDate;
        this.paymentDate = paymentDate;
    }

}
