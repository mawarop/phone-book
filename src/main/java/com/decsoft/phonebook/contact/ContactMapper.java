package com.decsoft.phonebook.contact;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ContactMapper {
    Contact contactRequestToContact(ContactRequest contactRequest);

    void updateContactFromContactRequest(ContactRequest contactRequest, @MappingTarget Contact contact);
}
