package com.titanium.user.exception;

public class CityNotFoundException extends BadRequestException {
    public CityNotFoundException() {
        super("City could not be confirmed.");
    }
}
