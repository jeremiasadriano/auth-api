package com.my.knowlodge.knowlodge01.exceptions.handler;

import com.my.knowlodge.knowlodge01.exceptions.PersonExceptionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class PersonExceptionsHandler extends ResponseEntityExceptionHandler {
    private PersonExceptionModel model;

    @ExceptionHandler(PersonNotFoundException.class)
    private ResponseEntity<PersonExceptionModel> notFound(PersonNotFoundException exception) {
        this.model = new PersonExceptionModel(HttpStatus.NOT_FOUND, exception.getMessage());
        return new ResponseEntity<>(model, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PersonNotNullException.class)
    private ResponseEntity<PersonExceptionModel> notNull(PersonNotNullException exception) {
        this.model = new PersonExceptionModel(HttpStatus.BAD_REQUEST, exception.getMessage());
        return new ResponseEntity<>(model, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PersonExistException.class)
    private ResponseEntity<PersonExceptionModel> exist(PersonExistException exception) {
        this.model = new PersonExceptionModel(HttpStatus.NOT_ACCEPTABLE, exception.getMessage());
        return new ResponseEntity<>(model, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(PersonNotRegisteredException.class)
    private ResponseEntity<PersonExceptionModel> notRegistered(PersonNotRegisteredException exception) {
        this.model = new PersonExceptionModel(HttpStatus.BAD_REQUEST, exception.getMessage());
        return new ResponseEntity<>(model, HttpStatus.BAD_REQUEST);
    }
}
