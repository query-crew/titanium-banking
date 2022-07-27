package com.titanium.user.exception;

public class UserNotVerifiedException extends BadRequestException {
    public UserNotVerifiedException() {
        super("User with this username doesn't exist.");
    }
}
