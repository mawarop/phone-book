package com.decsoft.phonebook.contact;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotBlank
    @Column(name = "LAST_NAME")
    private String lastName;

    @NotBlank
    @Column(name = "HOME_PHONE_NUMBER")
    private String homePhoneNumber;

    @NotBlank
    @Column(name = "BUSINESS_PHONE_NUMBER")
    private String businessPhoneNumber;

    @NotBlank
    @Email
    @Column(name = "EMAIL")
    private String email;

    public Contact(String firstName, String lastName, String homePhoneNumber, String businessPhoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.homePhoneNumber = homePhoneNumber;
        this.businessPhoneNumber = businessPhoneNumber;
        this.email = email;
    }

    public Contact() {

    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public String getBusinessPhoneNumber() {
        return businessPhoneNumber;
    }

    public String getEmail() {
        return email;
    }
}
