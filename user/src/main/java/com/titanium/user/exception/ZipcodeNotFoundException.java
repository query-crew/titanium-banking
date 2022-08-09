package com.titanium.user.exception;

public class ZipcodeNotFoundException extends BadRequestException {
    public ZipcodeNotFoundException() {
        super("Zipcode could not be confirmed.");
    }
}
