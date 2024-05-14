package com.my.knowlodge.knowlodge01.exceptions;

import org.springframework.http.HttpStatus;

public record PersonExceptionModel(HttpStatus status, String message) {
}
