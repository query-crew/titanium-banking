package com.titanium.user.exception;

public class AddressLineTwoNotFoundException extends BadRequestException {
    public AddressLineTwoNotFoundException() {
        super("Address line 2 could not be confirmed.");
    }
}
