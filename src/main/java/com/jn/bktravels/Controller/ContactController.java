package com.jn.bktravels.Controller;


import com.jn.bktravels.Service.ContactService;
import com.jn.bktravels.dtos.ContactDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin

public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/contact")
    public ResponseEntity<?> Contact(@RequestBody @Valid ContactDto contactDto) {
        System.out.println("Contact: " + contactDto);
        try {

            return new ResponseEntity<>(contactService.saveContact(contactDto.toEntity()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping("/contact")
    public ResponseEntity<?> getAllContacts() {
        return new ResponseEntity<>(contactService.getAllContact(),HttpStatus.OK);
    }


}
