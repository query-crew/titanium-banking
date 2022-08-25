package com.titanium.transactions.exception;

public class TransactionNotFoundException extends NotFoundException{
    public TransactionNotFoundException () {
        super("Transaction not found.");
    }
}
