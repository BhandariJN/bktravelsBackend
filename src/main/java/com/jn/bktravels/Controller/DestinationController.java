package com.jn.bktravels.Controller;

import com.jn.bktravels.Model.Destination;
import com.jn.bktravels.Service.DestinationService;
import com.jn.bktravels.dtos.DestinationDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController()
public class DestinationController {


    private final DestinationService destinationService;

    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }


    @GetMapping("/destination")
    public ResponseEntity<List<Destination>> getAllDestinations()  {
        List<Destination> destinations = destinationService.getAllDestinations();
        return new ResponseEntity<>(destinations, HttpStatus.OK);
    }

    @PostMapping("/destination")
    public ResponseEntity<?> addDestination(@Validated @ModelAttribute DestinationDto destination) {
        System.out.println(destination);
       try {
              ResponseEntity<?> receiveDestination = destinationService.addDestination(destination.toEntity());
              return new ResponseEntity<>(receiveDestination, HttpStatus.CREATED);
       }
         catch (Exception e) {
             return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

         }
    }

    @GetMapping("/destination/{id}")
    public ResponseEntity<?> getDestinationById(@PathVariable Integer id) {
        Destination destination = destinationService.getDestinationById(id);
        return new ResponseEntity<>(destination, HttpStatus.OK);
    }

    @PutMapping("/destination/{id}")
    public ResponseEntity<?> updateDestination(@PathVariable int id, DestinationDto destinationDto) {
       return new ResponseEntity<>(destinationService.updateDestination(id, destinationDto.toEntity()), HttpStatus.OK);
    }

    @DeleteMapping("/destination/{id}")
    public ResponseEntity<?> deleteDestination(@PathVariable int id) {
        destinationService.deleteDestination(id);
        return new ResponseEntity<>("Destination Deleted Successfully", HttpStatus.OK);
    }

    @GetMapping("/destination/search")
    public ResponseEntity<?> searchDestination(@RequestParam String query) {
        return new ResponseEntity<>(destinationService.getAllDestinationBySearch(query),HttpStatus.OK);
    }
}
