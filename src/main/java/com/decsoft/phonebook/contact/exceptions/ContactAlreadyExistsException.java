package com.decsoft.phonebook.contact.exceptions;

public class ContactAlreadyExistsException extends RuntimeException {
    public ContactAlreadyExistsException(String message) {
        super(message);
    }
}
