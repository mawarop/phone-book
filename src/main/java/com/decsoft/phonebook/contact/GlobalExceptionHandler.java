package com.decsoft.phonebook.contact;

import com.decsoft.phonebook.contact.exceptions.ContactAlreadyExistsException;
import com.decsoft.phonebook.contact.exceptions.ContactNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ContactAlreadyExistsException.class)
    public void handleConflict(ContactAlreadyExistsException alreadyExistsException) {
        log.error(alreadyExistsException.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ContactNotFoundException.class)
    public void handleNotFound(ContactNotFoundException notFoundException) {
        log.error(notFoundException.getMessage());
    }
}
