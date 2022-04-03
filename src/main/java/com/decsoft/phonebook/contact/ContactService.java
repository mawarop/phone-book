package com.decsoft.phonebook.contact;


import com.decsoft.phonebook.contact.exceptions.ContactAlreadyExistsException;

import java.util.List;

public interface ContactService {
    void createContact(ContactRequest contactRequest) throws ContactAlreadyExistsException;

    List<Contact> getAllContacts(int page);

    List<Contact> getContactsByInput(String input, int page);
}
