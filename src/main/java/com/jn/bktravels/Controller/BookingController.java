package com.jn.bktravels.Controller;


import com.jn.bktravels.Service.BookingService;
import com.jn.bktravels.dtos.BookingDto;
import com.jn.bktravels.Mapper.ToBookingDto;
import com.jn.bktravels.dtos.OwnBookingResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class BookingController {


    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/booking")
    public ResponseEntity<?> book(@RequestBody @Valid BookingDto bookingDto) {
        System.out.println("Booking: " + bookingDto);
        return bookingService.createBooking(bookingDto);

    }

        @GetMapping("/booking")
                public ResponseEntity<?> allBookings(){
         List<BookingDto> allBooking=  bookingService.getAllBooking();

            return new ResponseEntity<>(allBooking, HttpStatus.OK);
        }

    @GetMapping("/booking/{username}")
    public ResponseEntity<?> allBookings(@PathVariable String username) {
        List<OwnBookingResponseDto> allBookingOfUser=  bookingService.getAllBookingByUserId(username);

        return new ResponseEntity<>(allBookingOfUser, HttpStatus.OK);
    }


    @GetMapping("/booking/{id}/cancel")
    @ResponseStatus(HttpStatus.OK)
    public void cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
    }

    }

