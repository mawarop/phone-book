package com.decsoft.phonebook.contact;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ContactMapper {
    Contact contactRequestToContact(ContactRequest contactRequest);

    ContactResponse contactToContactResponse(Contact contact);

    List<ContactResponse> contactsToContactResponses(List<Contact> contacts);

    void updateContactFromContactRequest(ContactRequest contactRequest, @MappingTarget Contact contact);

}
