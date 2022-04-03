package com.decsoft.phonebook.contact;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Imię nie może być puste")
    @Size(min = 3)
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotBlank(message = "Nazwisko nie może być puste")
    @Size(min = 3)
    @Column(name = "LAST_NAME")
    private String lastName;

    @NotBlank(message = "Numer telefonu domowego nie może być pusty")
    @Size(min = 9, max = 9)
    @Pattern(regexp = "\\d{9}", message = "Niepoprawny format numeru telefonu domowego")
    @Column(name = "HOME_PHONE_NUMBER")
    private String homePhoneNumber;

    @NotBlank(message = "Numer telefonu służbowego nie może być pusty")
    @Size(min = 9, max = 9)
    @Pattern(regexp = "\\d{9}", message = "Niepoprawny format numeru telefonu służbowego")
    @Column(name = "BUSINESS_PHONE_NUMBER")
    private String businessPhoneNumber;

    @NotBlank
    @Email(message = "Niepoprawny email")
    @Column(name = "EMAIL", unique = true)
    private String email;

    public Contact(String firstName, String lastName, String homePhoneNumber, String businessPhoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.homePhoneNumber = homePhoneNumber;
        this.businessPhoneNumber = businessPhoneNumber;
        this.email = email;
    }
}
