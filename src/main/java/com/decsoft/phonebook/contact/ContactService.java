package com.decsoft.phonebook.contact;


public interface ContactService {
    void createContact(ContactRequest contactRequest) throws ContactAlreadyExistsException;

}
