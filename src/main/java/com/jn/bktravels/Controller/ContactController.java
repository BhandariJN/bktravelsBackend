package com.jn.bktravels.Controller;


import com.jn.bktravels.Service.ContactService;
import com.jn.bktravels.dtos.ContactDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin

public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/contact")
    public ResponseEntity<?> Contact(@RequestBody @Valid ContactDto contactDto) {
        System.out.println("Contact: " + contactDto);
        try {

            return new ResponseEntity<>(contactService.saveContact(contactDto.toEntity()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


}
