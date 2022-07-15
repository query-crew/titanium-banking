package com.titanium.user.exception;

public class UserNotVerifiedException extends BadRequestException {
    public UserNotVerifiedException() {
        super("user_not_enabled");
    }
}
