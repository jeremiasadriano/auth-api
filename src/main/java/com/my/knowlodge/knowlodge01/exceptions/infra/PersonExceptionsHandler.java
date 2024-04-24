package com.my.knowlodge.knowlodge01.exceptions.infra;

import com.my.knowlodge.knowlodge01.exceptions.model.PersonExceptionModel;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class PersonExceptionsHandler extends ResponseEntityExceptionHandler {
    private PersonExceptionModel model;
}
