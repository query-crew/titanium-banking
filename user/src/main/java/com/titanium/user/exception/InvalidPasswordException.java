package com.titanium.user.exception;

public class InvalidPasswordException extends BadRequestException {
    public InvalidPasswordException() {
        super("Password is invalid.");
    }
}
