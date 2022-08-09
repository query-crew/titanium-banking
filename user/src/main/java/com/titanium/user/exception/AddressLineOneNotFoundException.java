package com.titanium.user.exception;

public class AddressLineOneNotFoundException extends BadRequestException {
    public AddressLineOneNotFoundException() {
        super("Address line 1 could not be confirmed.");
    }
}
