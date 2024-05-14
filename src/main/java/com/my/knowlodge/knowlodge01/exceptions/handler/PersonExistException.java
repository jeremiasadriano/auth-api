package com.my.knowlodge.knowlodge01.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class PersonExistException extends RuntimeException {
    public PersonExistException() {
        super("Email already registered");
    }
}
