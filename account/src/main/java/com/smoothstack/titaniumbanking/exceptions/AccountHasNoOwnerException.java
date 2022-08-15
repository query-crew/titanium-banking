package com.smoothstack.titaniumbanking.exceptions;

public class AccountHasNoOwnerException extends BadRequestException {
    public AccountHasNoOwnerException() {
        super("Account does not have an owner");
    }
}
