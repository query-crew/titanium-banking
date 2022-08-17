package com.titanium.user.exception;

public class FirstNameNotFoundException extends BadRequestException {
    public FirstNameNotFoundException() {
        super("First name could not be confirmed.");
    }
}
