package com.decsoft.phonebook.contact;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        long numberOfContacts;
        long numberOfContactsPages;
        if (input.isPresent()) {
            contacts = contactService.getContactsByInput(input.get(), page);
            numberOfContacts = contactService.getNumberOfContactsByInput(input.get());
            numberOfContactsPages = contactService.getNumberOfContactsPagesByInput(numberOfContacts);
        } else {
            contacts = contactService.getAllContacts(page);
            numberOfContacts = contactService.getNumberOfAllContacts();
            numberOfContactsPages = contactService.getNumberOfAllContactsPages(numberOfContacts);
        }

        List<ContactResponse> contactResponses = contactMapper.contactsToContactResponses(contacts);

        Map<String, Object> response = new HashMap<>();
        response.put("contacts", contactResponses);
        response.put("totalContacts", numberOfContacts);
        response.put("totalPages", numberOfContactsPages);
        response.put("currentPage", page);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<HttpStatus> updateContact(@PathVariable("id") long id, @RequestBody @Valid ContactRequest contactRequest) {
        boolean created = contactService.updateContact(id, contactRequest);
        if (created)
            return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteContact(@PathVariable int id) {
        contactService.deleteContact(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }
}
