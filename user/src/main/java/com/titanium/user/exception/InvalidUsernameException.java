package com.titanium.user.exception;

public class InvalidUsernameException extends BadRequestException {
    public InvalidUsernameException() {
            super("Username is invalid.");
    }
}
