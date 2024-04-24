package com.my.knowlodge.knowlodge01.exceptions.model;

import org.springframework.http.HttpStatus;

public record PersonExceptionModel(HttpStatus status, String message) {
}
