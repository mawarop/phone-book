package com.decsoft.phonebook.contact;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    boolean existsByEmail(String email);

    @Query("Select c from Contact c")
    List<Contact> findAllContacts(Pageable pageable);

    @Query("SELECT  c from Contact c WHERE " +
            "(LOWER(c.firstName) LIKE LOWER(CONCAT('%', :input, '%'))) " +
            "OR (LOWER(c.lastName) LIKE LOWER(CONCAT('%', :input, '%'))) " +
            "OR (LOWER(c.homePhoneNumber) LIKE LOWER(CONCAT('%', :input, '%'))) " +
            "OR (LOWER(c.businessPhoneNumber) LIKE LOWER(CONCAT('%', :input, '%'))) " +
            "OR (LOWER(c.email) LIKE LOWER(CONCAT('%', :input, '%')))"
    )
    List<Contact> findContactsByInput(@Param("input") String input, Pageable pageable);


    @Query("SELECT  c from Contact c WHERE " +
            "((LOWER(c.firstName) LIKE LOWER(CONCAT('%', :nameNumberOne, '%'))) " +
            "AND (LOWER(c.lastName) LIKE LOWER(CONCAT('%', :nameNumberTwo, '%'))))" +
            "OR ((LOWER(c.firstName) LIKE LOWER(CONCAT('%', :nameNumberTwo, '%'))) " +
            "AND (LOWER(c.lastName) LIKE LOWER(CONCAT('%', :nameNumberOne, '%')))) "

    )
    List<Contact> findContactsByInput(@Param("nameNumberOne") String nameNumberOne, @Param("nameNumberTwo") String nameNumberTwo, Pageable pageable);

    @Query("SELECT count(c) from Contact c WHERE " +
            "(LOWER(c.firstName) LIKE LOWER(CONCAT('%', :input, '%'))) " +
            "OR (LOWER(c.lastName) LIKE LOWER(CONCAT('%', :input, '%'))) " +
            "OR (LOWER(c.homePhoneNumber) LIKE LOWER(CONCAT('%', :input, '%'))) " +
            "OR (LOWER(c.businessPhoneNumber) LIKE LOWER(CONCAT('%', :input, '%'))) " +
            "OR (LOWER(c.email) LIKE LOWER(CONCAT('%', :input, '%')))"
    )
    long countContactsByInput(@Param("input") String input);

    @Query("SELECT count(c) from Contact c WHERE " +
            "((LOWER(c.firstName) LIKE LOWER(CONCAT('%', :nameNumberOne, '%'))) " +
            "AND (LOWER(c.lastName) LIKE LOWER(CONCAT('%', :nameNumberTwo, '%'))))" +
            "OR ((LOWER(c.firstName) LIKE LOWER(CONCAT('%', :nameNumberTwo, '%'))) " +
            "AND (LOWER(c.lastName) LIKE LOWER(CONCAT('%', :nameNumberOne, '%')))) "
    )
    long countContactsByInput(@Param("nameNumberOne") String nameNumberOne, @Param("nameNumberTwo") String nameNumberTwo);
}
