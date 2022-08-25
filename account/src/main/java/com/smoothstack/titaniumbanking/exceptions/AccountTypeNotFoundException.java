package com.smoothstack.titaniumbanking.exceptions;

public class AccountTypeNotFoundException extends BadRequestException {
    public AccountTypeNotFoundException() {
        super("Account type doesn't exist");
    }
}
