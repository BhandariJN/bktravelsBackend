package com.jn.bktravels.Service;


import com.jn.bktravels.Model.Destination;
import com.jn.bktravels.Repository.DestinationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Service
public class DestinationService {

    @Autowired
    private DestinationRepo destinationRepo;


    // Get All Destinations
    public ArrayList<Destination> getAllDestinations()  {

        return (ArrayList<Destination>) destinationRepo.findAll();
    }


    // Add Destination
    public ResponseEntity<?> addDestination(Destination destination, MultipartFile imageFile) {
        destination.setImageName(imageFile.getOriginalFilename());
        destination.setImageType(imageFile.getContentType());
        try {
            destination.setImageData(imageFile.getBytes());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error in Image Upload");
        }
        destinationRepo.save(destination);
        return ResponseEntity.ok("Destination Added Successfully");
    }

    // Get Destination By Id
    public Destination getDestinationById(int id) {
        return destinationRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Destination Not Found")
        );
    }


    // Update Destination
    public void updateDestination(int id, Destination destination, MultipartFile imageFile) {
        Destination destinationToUpdate = destinationRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Destination Not Found")
        );
        destinationToUpdate.setImageName(imageFile.getOriginalFilename());
        destinationToUpdate.setImageType(imageFile.getContentType());
        try {
            destinationToUpdate.setImageData(imageFile.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("Error in Image Upload");
        }

        destinationRepo.save(destinationToUpdate);

    }
// Delete Destination
    public void deleteDestination(int id) {
        destinationRepo.deleteById(id);
    }
}

