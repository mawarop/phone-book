package com.decsoft.phonebook.contact;


import java.util.List;

public interface ContactService {
    void createContact(ContactRequest contactRequest) throws ContactAlreadyExistsException;

    List<Contact> getAllContacts(int page);

    List<Contact> getContactsByInput(String input, int page);

    boolean updateContact(long id, ContactRequest contactRequest);

    void deleteContact(long id);

    long getNumberOfAllContacts();

    long getNumberOfAllContactsPages(long numberOfContacts);

    long getNumberOfContactsByInput(String input);

    long getNumberOfContactsPagesByInput(long numberOfContactsByInput);
}
