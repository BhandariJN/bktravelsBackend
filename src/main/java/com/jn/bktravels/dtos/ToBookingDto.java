package com.jn.bktravels.dtos;

import com.jn.bktravels.Model.Booking;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ToBookingDto {

    private String bookingId;
    private LocalDate bookingDate;
    private LocalDate selectedDate;
    private int numberOfTravellers;
    private float totalPrice;
    private String destinationName;
    private String bookingStatus;

    // Instance method for converting a Booking entity to BookingDto
    public static ToBookingDto fromBooking(Booking booking) {
        return ToBookingDto.builder()
                .bookingId(String.valueOf(booking.getId()))
                .bookingDate(booking.getBookedDate())
                .selectedDate(booking.getSelectedDate())
                .numberOfTravellers(booking.getNumberofTravellers())
                .totalPrice((float) booking.getTotalAmount())
                .destinationName(booking.getDestination().getDestinationName())
                .bookingStatus(booking.getStatus().name())
                .build();
    }


}
