package com.the.collective.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DataNotFoundException extends RuntimeException {


    private static final long serialVersionUID = 7913231235839568169L;

    public DataNotFoundException(String message) {
        super(message);
    }
}
