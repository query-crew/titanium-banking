package com.titanium.user.exception;

public class StateNotFoundException extends BadRequestException {
    public StateNotFoundException() {
        super("State could not be confirmed.");
    }
}
