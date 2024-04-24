package com.my.knowlodge.knowlodge01.exceptions.infra;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException() {
        super("Person not found with such data!");
    }
}
