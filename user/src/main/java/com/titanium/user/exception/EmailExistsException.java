package com.titanium.user.exception;

public class EmailExistsException extends BadRequestException {
    public EmailExistsException() {
        super("Email already exists.");
    }
}
