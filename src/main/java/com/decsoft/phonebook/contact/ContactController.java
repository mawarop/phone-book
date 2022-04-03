package com.decsoft.phonebook.contact;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/contacts")
public class ContactController {
    private final ContactService contactService;
    private final ContactMapper contactMapper;

    @PostMapping()
    public ResponseEntity<HttpStatus> createContact(@RequestBody @Valid ContactRequest contactRequest) {
        contactService.createContact(contactRequest);
        return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<ContactResponse>> getContacts() {
        List<Contact> contacts = contactService.getAllContacts();
        List<ContactResponse> contactResponses = contactMapper.contactsToContactResponses(contacts);
        return ResponseEntity.ok(contactResponses);
    }

}
