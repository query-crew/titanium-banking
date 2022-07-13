package com.titanium.user.exception;

public class UsernameExistsException extends BadRequestException {
    public UsernameExistsException() {
        super("Username already exists.");
    }
}
