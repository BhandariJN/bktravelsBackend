package com.jn.bktravels.Service;


import com.jn.bktravels.Model.Booking;
import com.jn.bktravels.Model.Destination;
import com.jn.bktravels.Model.User;
import com.jn.bktravels.Repository.BookingRepo;
import com.jn.bktravels.Repository.DestinationRepo;
import com.jn.bktravels.Repository.UserRepo;
import com.jn.bktravels.dtos.BookingDto;
import com.jn.bktravels.dtos.ToBookingDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final BookingRepo bookingRepo;
    private final UserRepo userRepo;
    private final DestinationRepo destinationRepo;


    public BookingService(BookingRepo bookingRepo, UserRepo userRepo, DestinationRepo destinationRepo) {
        this.bookingRepo = bookingRepo;
        this.userRepo = userRepo;
        this.destinationRepo = destinationRepo;
    }

    public ResponseEntity<?> createBooking(BookingDto bookingDto) {
        User user = userRepo.findByUsername(bookingDto.getUserName());
        if(user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        Destination destination = destinationRepo.findById(Math.toIntExact(bookingDto.getDestinationId()))
                .orElseThrow(() -> new EntityNotFoundException("Destination not found"));

        Booking booking = bookingDto.toEntity();
        booking.setUser(user);
        booking.setDestination(destination);


        return new ResponseEntity<>(bookingRepo.save(booking), HttpStatus.CREATED);
    }

    public List<ToBookingDto> getAllBooking() {
        List<Booking> allBookings = bookingRepo.findAll();

        // Convert list of Booking entities to list of ToBookingDto objects
        return allBookings.stream()
                .map(ToBookingDto::fromBooking)
                .collect(Collectors.toList());
    }

    public List<ToBookingDto> getAllBookingByUserId(String username) {

        List<Booking> allBookingOfUser = bookingRepo.findBookingByUserUsername(username);

        return allBookingOfUser.stream()
                .map(ToBookingDto::fromBooking)
                .collect(Collectors.toList());


    }
}
