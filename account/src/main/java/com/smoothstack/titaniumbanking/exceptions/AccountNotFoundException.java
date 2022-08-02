package com.smoothstack.titaniumbanking.exceptions;

public class AccountNotFoundException extends BadRequestException {
    public AccountNotFoundException() {
        super("Account doesn't exist");
    }
}
