package com.titanium.transactions.exception;

public class InvalidSortPropException extends BadRequestException{
    public InvalidSortPropException() {
        super("Invalid sort prop.");
    }
}
