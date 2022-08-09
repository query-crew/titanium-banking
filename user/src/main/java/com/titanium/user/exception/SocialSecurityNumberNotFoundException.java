package com.titanium.user.exception;

public class SocialSecurityNumberNotFoundException extends BadRequestException {
    public SocialSecurityNumberNotFoundException() {
        super("Social security number could not be confirmed.");
    }
}