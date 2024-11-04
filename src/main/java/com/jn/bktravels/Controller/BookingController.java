package com.jn.bktravels.Controller;


import com.jn.bktravels.Model.Booking;
import com.jn.bktravels.Service.BookingService;
import com.jn.bktravels.dtos.BookingDto;
import com.jn.bktravels.dtos.ToBookingDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
         List<ToBookingDto> allBooking=  bookingService.getAllBooking();

            return new ResponseEntity<>(allBooking, HttpStatus.OK);
        }

    @GetMapping("/booking/{username}")
    public ResponseEntity<?> allBookings(@PathVariable String username) {
        List<ToBookingDto> allBookingOfUser=  bookingService.getAllBookingByUserId(username);

        return new ResponseEntity<>(allBookingOfUser, HttpStatus.OK);
    }
    @GetMapping("/payment-success")
    public String handlePaymentSuccess(
            @RequestParam("transaction_uuid") String transaction_uuid,
            @RequestParam("total_amount") String total_amount,
            @RequestParam("status") String status) {

        // Use the parameters for payment verification and update booking status
        System.out.println(transaction_uuid);
        System.out.println(total_amount);
        System.out.println(status);


        // Perform necessary actions to confirm the payment and update booking status
        // e.g., confirm booking, update the status in the database

       // return "Payment successful for Order ID: " + orderId;
        return status;
    }







}
