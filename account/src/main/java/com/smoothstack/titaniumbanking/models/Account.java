package com.smoothstack.titaniumbanking.models;


import java.time.LocalDate;

import javax.persistence.*;

import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name ="Accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="account_id")
    private int accountId;

    @Column(name="account_name")
    private String accountName;

    @Column(name="account_number")
    private String accountNumber;

    @Column(name="balance")
    private int balance;

    @Column(name="interest")
    private int interest;

    @Column(name="last_statement_date")
    private LocalDate lastStatementDate;

    @Column(name="payment_date")
    private LocalDate paymentDate;

    // @ManyToOne(mappedBy="User_Id")
    // private User user;

    // @OneToOne
    // @JoinColumn(name="accountTypeId")
    // private AccountType accountType;

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
