package com.decsoft.phonebook.contact;

import com.decsoft.phonebook.contact.exceptions.ContactAlreadyExistsException;
import com.decsoft.phonebook.contact.exceptions.ContactNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    public static final int PAGE_SIZE = 5;
    private final ContactMapper contactMapper;
    private final ContactRepository contactRepository;


    @Override
    public void createContact(ContactRequest contactRequest) {
        Contact newContact = contactMapper.contactRequestToContact(contactRequest);
        boolean existsContactWithSameEmail = contactRepository.existsByEmail(newContact.getEmail());
        if (existsContactWithSameEmail) {
            throw new ContactAlreadyExistsException("Contact already exists in database");
        }
        contactRepository.save(newContact);
    }

    @Override
    public List<Contact> getAllContacts(int page) {
        List<Contact> contacts = contactRepository.findAllContacts(PageRequest.of(page, PAGE_SIZE));
        if (contacts.isEmpty())
            throw new ContactNotFoundException("Contact not found in database");
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
            throw new ContactNotFoundException("Contact not found in database");
        return contacts;
    }

}
