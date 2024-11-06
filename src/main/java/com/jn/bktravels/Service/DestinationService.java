package com.jn.bktravels.Service;

import com.jn.bktravels.Config.FileUploadProperties;
import com.jn.bktravels.Model.Destination;
import com.jn.bktravels.Repository.DestinationRepo;
import com.jn.bktravels.dtos.DestinationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DestinationService {

    @Autowired
    private DestinationRepo destinationRepo;

    @Autowired
    private FileUploadProperties fileUploadProperties;

    // Get All Destinations
    public ArrayList<Destination> getAllDestinations() {
        return (ArrayList<Destination>) destinationRepo.findAll();
    }

    // Add Destination
    public ResponseEntity<String> addDestination(Destination destination) {

            destinationRepo.save(destination); // Save only once

            return ResponseEntity.ok("Destination Added Successfully");
    }

    // Get Destination By Id
    public Destination getDestinationById(int id) {
        return destinationRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Destination Not Found with ID: " + id)
        );
    }

    // Update Destination
    public ResponseEntity<String> updateDestination(int id, Destination destination) {
        boolean destinationExists = destinationRepo.existsById(id);

        if (destinationExists) {
            destination.setId(id); // Ensure the ID is set on the destination entity
            destinationRepo.save(destination);
            return ResponseEntity.ok("Destination Updated Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Destination not found with ID: " + id);
        }
    }

    // Delete Destination
    public ResponseEntity<String> deleteDestination(int id) {
        if (!destinationRepo.existsById(id)) {
            return ResponseEntity.badRequest().body("Destination Not Found with ID: " + id);
        }
        destinationRepo.deleteById(id);
        return ResponseEntity.ok("Destination Deleted Successfully");
    }

            public ArrayList<Destination> getAllDestinationBySearch(String query) {
            return (ArrayList<Destination>) destinationRepo.searchByQuery(query);
        }

}
