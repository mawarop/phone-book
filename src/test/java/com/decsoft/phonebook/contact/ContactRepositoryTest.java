package com.decsoft.phonebook.contact;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class ContactRepositoryTest {


    @Autowired
    private ContactRepository contactRepository;

    @AfterEach
    void tearDown() {
        contactRepository.deleteAll();
    }

    @Test
    void Should_ExistsByEmail_When_ContactWithEmailExist() {
        // given
        Contact contact = new Contact("Ryszard", "Zalewski",
                "642950356", "738940527", "ryszard@gmail.com");
        contactRepository.save(contact);
        String email = contact.getEmail();
        // when
        boolean expected = contactRepository.existsByEmail(email);
        // then
        assertThat(expected).isTrue();
    }

    @Test
    void Should_NotExistsByEmail_When_ContactWithEmailNotExist() {
        // given
        String email = "ryszard@gmail.com";
        // when
        boolean expected = contactRepository.existsByEmail(email);
        // then
        assertThat(expected).isFalse();
    }
}