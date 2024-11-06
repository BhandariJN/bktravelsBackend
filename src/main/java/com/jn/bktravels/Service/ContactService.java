package com.jn.bktravels.Service;


import com.jn.bktravels.Mapper.ToContactDto;
import com.jn.bktravels.Model.Contact;
import com.jn.bktravels.Model.User;
import com.jn.bktravels.Repository.ContactRepo;
import com.jn.bktravels.Repository.UserRepo;
import com.jn.bktravels.dtos.ContactDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactService {
private final  ContactRepo contactRepo;
private final ToContactDto toContactDto;
private final UserRepo userRepo;

    public ContactService(ContactRepo contactRepo, ToContactDto toContactDto, UserRepo userRepo) {
        this.contactRepo = contactRepo;
        this.toContactDto = toContactDto;
        this.userRepo = userRepo;
    }

    public ResponseEntity<?> saveContact(ContactDto contactDto) {
        try {


            Integer userId = contactDto.getUserId();
            Optional<User> user = userRepo.findById(userId);
            if (user.isPresent()) {
                Contact contact = contactDto.toEntity(user.get());
                contactRepo.save(contact);
                return new ResponseEntity<>("Saved Sucessfully", HttpStatus.OK);
        }
        } catch (Exception e) {
            return new ResponseEntity<>("Error in saving contact: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Error in saving contact", HttpStatus.BAD_REQUEST);
    }


    public List<ContactDto> getAllContact() {
        List<Contact> contacts =contactRepo.findAll();
        return contacts.stream().map(toContactDto::toContactDto).collect(Collectors.toList());

    }
}
