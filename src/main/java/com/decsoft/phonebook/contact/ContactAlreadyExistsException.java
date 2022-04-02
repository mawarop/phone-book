package com.decsoft.phonebook.contact;

//@ResponseStatus(HttpStatus.CONFLICT)
public class ContactAlreadyExistsException extends RuntimeException {
    public ContactAlreadyExistsException(String message) {
        super(message);
    }
}
