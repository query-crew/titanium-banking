package com.titanium.user.exception;

public class SocialSecurityNumberExistsException extends BadRequestException {
    public SocialSecurityNumberExistsException() {
        super("Social security number is associated with current member.");
    }
}
