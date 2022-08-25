package com.titanium.transactions.exception;

import org.springframework.http.HttpStatus;

public class HttpException extends RuntimeException{
    private final String message;
    private final HttpStatus status;
    public HttpException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
