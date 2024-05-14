package com.my.knowlodge.knowlodge01.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PersonNotRegisteredException extends RuntimeException {
    public PersonNotRegisteredException() {
        super("Error! Person not registered.");
    }
}
