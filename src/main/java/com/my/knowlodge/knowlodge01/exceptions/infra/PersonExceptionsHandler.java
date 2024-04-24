package com.my.knowlodge.knowlodge01.exceptions.infra;

import com.my.knowlodge.knowlodge01.exceptions.model.PersonExceptionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class PersonExceptionsHandler extends ResponseEntityExceptionHandler {
    private PersonExceptionModel model;

    @ExceptionHandler(PersonNotFoundException.class)
    private ResponseEntity<PersonExceptionModel> notFound(PersonNotFoundException notFoundException) {
        this.model = new PersonExceptionModel(HttpStatus.NOT_FOUND, notFoundException.getMessage());
        return new ResponseEntity<>(model, HttpStatus.NOT_FOUND);
    }
}
