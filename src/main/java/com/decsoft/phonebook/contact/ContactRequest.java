package com.decsoft.phonebook.contact;

import lombok.AllArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
public class ContactRequest {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String homePhoneNumber;

    @NotBlank
    private String businessPhoneNumber;

    @NotBlank
    @Email
    private String email;

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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    public void setBusinessPhoneNumber(String businessPhoneNumber) {
        this.businessPhoneNumber = businessPhoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
