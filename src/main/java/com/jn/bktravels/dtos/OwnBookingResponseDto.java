package com.jn.bktravels.dtos;

import com.jn.bktravels.Model.Booking;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder


public class OwnBookingResponseDto {


        private Long id;
        private String destinationName;
        private Integer numberOfTravellers;
        private Double totalAmount;
        private LocalDate selectedDate; // Representing the start date or selected time
        private Booking.Status status; // Enum type for status (PENDING, CONFIRMED, CANCELLED)
    }

