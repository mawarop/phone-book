package com.decsoft.phonebook.contact;


import java.util.List;

public interface ContactService {
    void createContact(ContactRequest contactRequest) throws ContactAlreadyExistsException;

    List<Contact> getAllContacts();
}
