package com.decsoft.phonebook.contact;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ContactAlreadyExistsException.class)
    public void handleConflict() {

    }
}
