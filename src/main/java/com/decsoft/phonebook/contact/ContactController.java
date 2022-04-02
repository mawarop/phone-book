package com.decsoft.phonebook.contact;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/contacts")
public class ContactController {
    private final ContactService contactService;

    @PostMapping()
    public ResponseEntity<?> createContact(@RequestBody ContactRequest contactRequest) {
        contactService.createContact(contactRequest);
        return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
    }


}
