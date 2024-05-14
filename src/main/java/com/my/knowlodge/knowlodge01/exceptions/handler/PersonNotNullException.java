package com.my.knowlodge.knowlodge01.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_GATEWAY)
public class PersonNotNullException extends RuntimeException {
    public PersonNotNullException() {
        super("Null values not allowed");
    }
}
