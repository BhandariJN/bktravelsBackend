package com.jn.bktravels.Controller;


import com.jn.bktravels.Model.Booking;
import com.jn.bktravels.Service.BookingService;
import com.jn.bktravels.dtos.BookingDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/booking")
    public ResponseEntity<?> book(@RequestBody @Valid BookingDto bookingDto) {
        System.out.println("Booking: " + bookingDto);
        return bookingService.book(bookingDto);

    }
        @GetMapping("/booking")
                public ResponseEntity<?> allBookings(){
         List<Booking> allBooking=  bookingService.getAllBooking();

            return new ResponseEntity<>(allBooking, HttpStatus.OK);
        }




}
