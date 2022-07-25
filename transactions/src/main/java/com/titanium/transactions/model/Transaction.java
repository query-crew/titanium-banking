package com.titanium.transactions.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "TEST_TBL_TRANSACTIONS")
public class Transaction {

    @Id
    @Column(name = "TRANSACTION_ID")
    private int transactionId;

    @Column(name = "TRANSACTION_TYPE")
    private int transactionType;

    @Column(name = "TRANSACTION_DATE")
    private Timestamp transactionDate;

    @Column(name = "TRANSACTION_DESCRIPTION")
    private String description;

    @Column(name = "TRANSACTION_AMOUNT")
    private int amount;

    @Column(name = "ACCOUNT_FROM_ID")
    private int accountFromId;

    @Column(name = "ACCOUNT_TO_ID")
    private int accountToId;

    public Transaction(int _transactionId, int _transactionType, Timestamp _transactionDate, String _description, int _amount, int _accountFromId, int _accountToId) {
        this.transactionId = _transactionId;
        this.transactionType = _transactionType;
        this.transactionDate = _transactionDate;
        this.description = _description;
        this.amount = _amount;
        this.accountFromId = _accountFromId;
        this.accountToId = _accountToId;
    }
}