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

    @Column(name = "transaction_Type")
    private int transactionType;

    @Column(name = "transaction_Date")
    private Timestamp transactionDate;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private float amount;

    @Column(name = "account_From_Id")
    private int accountFromId;

    @Column(name = "account_To_Id")
    private int accountToId;

    public Transaction(int _transactionId, int _transactionType, Timestamp _transactionDate, String _description, float _amount, int _accountFromId, int _accountToId) {
        this.transactionId = _transactionId;
        this.transactionType = _transactionType;
        this.transactionDate = _transactionDate;
        this.description = _description;
        this.amount = _amount;
        this.accountFromId = _accountFromId;
        this.accountToId = _accountToId;
    }
}