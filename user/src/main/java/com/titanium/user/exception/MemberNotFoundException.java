package com.titanium.user.exception;

public class MemberNotFoundException extends BadRequestException {
    public MemberNotFoundException() {
        super("Member doesn't exist.");
    }
}
