package com.smoothstack.titaniumbanking.exceptions;

public class AccountExistsException extends BadRequestException{
    
    public AccountExistsException() {
        super("Account already exists");
    }
    
}
