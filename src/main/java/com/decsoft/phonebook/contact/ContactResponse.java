package com.decsoft.phonebook.contact;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactResponse {

    @NotBlank(message = "Imię nie może być puste")
    @Size(min = 3)
    private String firstName;

    @NotBlank(message = "Nazwisko nie może być puste")
    @Size(min = 3)
    private String lastName;

    @NotBlank(message = "Numer telefonu domowego nie może być pusty")
    @Size(min = 9, max = 9)
    @Pattern(regexp = "\\d{9}", message = "Niepoprawny format numeru telefonu domowego")
    private String homePhoneNumber;

    @NotBlank(message = "Numer telefonu służbowego nie może być pusty")
    @Size(min = 9, max = 9)
    @Pattern(regexp = "\\d{9}", message = "Niepoprawny format numeru telefonu służbowego")
    private String businessPhoneNumber;

    @NotBlank
    @Email(message = "Niepoprawny email")
    private String email;
}
