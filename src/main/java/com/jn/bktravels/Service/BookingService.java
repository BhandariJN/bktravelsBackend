package com.jn.bktravels.Service;


import com.jn.bktravels.Model.Booking;
import com.jn.bktravels.Repository.BookingRepo;
import com.jn.bktravels.dtos.BookingDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepo bookingRepo;

    public BookingService(BookingRepo bookingRepo) {
        this.bookingRepo = bookingRepo;
    }

    public ResponseEntity<?> book(@Valid BookingDto bookingDto) {
        try {
            return new ResponseEntity<>("Booking Successful! Please Proceed For Payment", HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    public List<Booking> getAllBooking() {

        return  bookingRepo.findAll();
    }
}
