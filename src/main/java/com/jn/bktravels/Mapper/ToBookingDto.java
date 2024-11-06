package com.jn.bktravels.Mapper;

import com.jn.bktravels.Model.Booking;
import com.jn.bktravels.dtos.BookingDto;
import org.springframework.stereotype.Component;

@Component

public class ToBookingDto {

    // Static method for converting a Booking entity to BookingDto
    public BookingDto toBookingDto(Booking booking) {
        return BookingDto.builder()
                .userName(booking.getUser().getUsername()) // Assuming there's a User object in Booking
                .destinationId(Long.valueOf(booking.getDestination().getId())) // Assuming there's a Destination object in Booking
                .numberOfTravellers(booking.getNumberofTravellers())
                .totalAmount(booking.getTotalAmount())
                .selectedDateTime(booking.getSelectedDate())
                .status(booking.getStatus())
                .build();
    }
}
