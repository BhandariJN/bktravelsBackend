package com.jn.bktravels.Service;


import com.jn.bktravels.Model.Contact;
import com.jn.bktravels.Repository.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    @Autowired
    ContactRepo contactRepo;

    public String saveContact(Contact contact) {
        try {
            contactRepo.save(contact);
            return "Contact saved successfully";
        } catch (Exception e) {
            return "Error in saving contact";
        }
        }
}
