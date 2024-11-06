package com.jn.bktravels.Mapper;

import com.jn.bktravels.Model.Contact;
import com.jn.bktravels.dtos.ContactDto;
import org.springframework.stereotype.Component;

@Component
public class ToContactDto {

    public ContactDto toContactDto(Contact contact) {

        return ContactDto.builder()
                .country(contact.getCountry())
                .email(contact.getEmail())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .message(contact.getMessage())
                .userId(Math.toIntExact(contact.getUser().getId()))
                .build();
    }
}
