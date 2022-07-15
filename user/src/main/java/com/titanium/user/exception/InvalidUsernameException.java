package com.titanium.user.exception;

public class InvalidUsernameException extends BadRequestException {
    public InvalidUsernameException() {
            super("invalid_username_or_user");
    }
}
