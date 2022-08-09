package com.titanium.user.exception;

public class DateOfBirthNotFoundException extends BadRequestException {
    public DateOfBirthNotFoundException() {
        super("Date of birth could not be confirmed.");
    }
}
