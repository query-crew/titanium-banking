package com.smoothstack.titaniumbanking.exceptions;

import org.springframework.http.HttpStatus;

public class AccountNotFoundException extends BadRequestException {
    public AccountNotFoundException() {
        super("Account doesn't exist");
    }
}
