package com.jn.bktravels.Mapper;

import com.jn.bktravels.Model.Booking;
import com.jn.bktravels.dtos.OwnBookingResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ToOwnBookingResponseDto {

    public OwnBookingResponseDto responseDto(Booking booking) {

        return OwnBookingResponseDto.builder()
                .id(booking.getId())
                .destinationName(booking.getDestination().getDestinationName())
                .selectedDate(booking.getSelectedDate())
                .totalAmount(booking.getTotalAmount())
                .numberOfTravellers(booking.getNumberofTravellers())
                .status(booking.getStatus())
                .build();
    }
}
