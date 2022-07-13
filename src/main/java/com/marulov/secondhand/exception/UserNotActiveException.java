package com.marulov.secondhand.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserNotActiveException extends RuntimeException {

    public UserNotActiveException(String message) {
        super(message);
    }
}