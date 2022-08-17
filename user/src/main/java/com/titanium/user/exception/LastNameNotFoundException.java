package com.titanium.user.exception;

public class LastNameNotFoundException extends BadRequestException {
    public LastNameNotFoundException() {
        super("Last name could not be confirmed.");
    }
}
