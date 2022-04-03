package com.decsoft.phonebook.contact;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
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
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }


}
