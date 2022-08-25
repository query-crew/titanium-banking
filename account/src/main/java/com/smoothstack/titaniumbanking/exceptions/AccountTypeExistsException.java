package com.smoothstack.titaniumbanking.exceptions;

public class AccountTypeExistsException extends BadRequestException {

    public AccountTypeExistsException() {
        super("Account type already exists");
    }

}