package com.titanium.user.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class HttpException extends RuntimeException {
    private final HttpStatus status;
    private final String message;

    public HttpException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }
}
