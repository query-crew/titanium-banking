package com.titanium.user.exception;

public class PhoneNotFoundException extends BadRequestException {
    public PhoneNotFoundException() {
        super("Phone could not be confirmed.");
    }
}
