package com.decsoft.phonebook.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    boolean existsByEmail(String email);

    List<Contact> findContactsByFirstName(String firstName);

    List<Contact> findContactsByLastName(String lastName);

}
