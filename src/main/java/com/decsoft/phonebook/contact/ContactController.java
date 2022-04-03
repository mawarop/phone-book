package com.decsoft.phonebook.contact;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<ContactResponse>> getContacts
            (@RequestParam int page, @RequestParam Optional<String> input) {

        List<Contact> contacts;
        if (input.isPresent()) {
            contacts = contactService.getContactsByInput(input.get(), page);
        } else {
            contacts = contactService.getAllContacts(page);
        }

        List<ContactResponse> contactResponses = contactMapper.contactsToContactResponses(contacts);
        return ResponseEntity.ok(contactResponses);
    }

    @PutMapping("{id}")
    public ResponseEntity<HttpStatus> updateContact(@PathVariable("id") long id, @RequestBody @Valid ContactRequest contactRequest) {
        boolean created = contactService.updateContact(id, contactRequest);
        if (created)
            return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }
}
