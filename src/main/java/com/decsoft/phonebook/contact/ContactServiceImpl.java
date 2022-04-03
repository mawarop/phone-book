package com.decsoft.phonebook.contact;

import com.decsoft.phonebook.contact.exceptions.ContactAlreadyExistsException;
import com.decsoft.phonebook.contact.exceptions.ContactNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    public static final int PAGE_SIZE = 5;
    public static final String CONTACT_NOT_FOUND_MESSAGE = "Contact not found in database";
    public static final String CONTACT_ALREADY_EXISTS_MESSAGE = "Contact already exists in database";
    private final ContactMapper contactMapper;
    private final ContactRepository contactRepository;


    @Override
    public void createContact(ContactRequest contactRequest) {
        Contact newContact = contactMapper.contactRequestToContact(contactRequest);
        boolean existsContactWithSameEmail = contactRepository.existsByEmail(newContact.getEmail());
        if (existsContactWithSameEmail) {
            throw new ContactAlreadyExistsException(CONTACT_ALREADY_EXISTS_MESSAGE);
        }
        contactRepository.save(newContact);
    }

    @Override
    public List<Contact> getAllContacts(int page) {
        List<Contact> contacts = contactRepository.findAllContacts(PageRequest.of(page, PAGE_SIZE));
        if (contacts.isEmpty())
            throw new ContactNotFoundException(CONTACT_NOT_FOUND_MESSAGE);
        return contacts;
    }

    @Override
    public List<Contact> getContactsByInput(String input, int page) {
        List<String> splited;
        List<Contact> contacts;
        if (input.contains(" ")) {
            splited = Arrays.stream(input.split("\\s+")).toList();
            String nameNumbOne = splited.get(0);
            String nameNumbTwo = splited.get(1);
            contacts = contactRepository.findContactsByInput(nameNumbOne, nameNumbTwo, PageRequest.of(page, PAGE_SIZE));
        } else {
            contacts = contactRepository.findContactsByInput(input, PageRequest.of(page, PAGE_SIZE));
        }

        if (contacts.isEmpty())
            throw new ContactNotFoundException(CONTACT_NOT_FOUND_MESSAGE);
        return contacts;
    }

    @Override
    public boolean updateContact(long id, ContactRequest contactRequest) {
        Optional<Contact> contact = contactRepository.findById(id);
        if (contact.isPresent()) {
            contactMapper.updateContactFromContactRequest(contactRequest, contact.get());
            contactRepository.save(contact.get());
            return false;
        } else {
            Contact newContact = contactMapper.contactRequestToContact(contactRequest);
            contactRepository.save(newContact);
            return true;
        }
    }

    @Override
    public void deleteContact(long id) {
        Optional<Contact> contact = contactRepository.findById(id);
        if (contact.isPresent()) {
            contactRepository.delete(contact.get());
        } else throw new ContactNotFoundException(CONTACT_NOT_FOUND_MESSAGE);
    }

    @Override
    public long getNumberOfAllContacts() {
        return contactRepository.count();
    }

    @Override
    public long getNumberOfAllContactsPages(long numberOfContacts) {
        return (int) Math.ceil((double) numberOfContacts / (double) PAGE_SIZE);
    }


    @Override
    public long getNumberOfContactsByInput(String input) {
        List<String> splited;
        long numbOfContacts;
        if (input.contains(" ")) {
            splited = Arrays.stream(input.split("\\s+")).toList();
            String nameNumbOne = splited.get(0);
            String nameNumbTwo = splited.get(1);
            numbOfContacts = contactRepository.countContactsByInput(nameNumbOne, nameNumbTwo);
        } else {
            numbOfContacts = contactRepository.countContactsByInput(input);
        }

        if (numbOfContacts == 0)
            throw new ContactNotFoundException(CONTACT_NOT_FOUND_MESSAGE);
        return numbOfContacts;
    }

    @Override
    public long getNumberOfContactsPagesByInput(long numberOfContactsByInput) {
        return (long) Math.ceil((double) numberOfContactsByInput / (double) PAGE_SIZE);
    }
}
