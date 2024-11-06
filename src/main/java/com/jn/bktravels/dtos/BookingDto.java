package com.jn.bktravels.dtos;

import com.jn.bktravels.Model.Booking;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDto {

    @NotNull(message = "UserName is required")
    private String userName;

    @NotNull(message = "Destination ID is required")
    private Long destinationId;

    //@NotNull(message = "Number of Travellers is required")
    private Integer numberOfTravellers;




  //  @NotNull(message = "Total Amount is required")
    private Double totalAmount;

    @NotNull(message = "Selected Date is required")
    private LocalDate selectedDateTime;

    private Booking.Status status = Booking.Status.PENDING;

    public Booking toEntity() {
        return Booking.builder()
                .numberofTravellers(this.numberOfTravellers)
                .totalAmount(this.totalAmount)
                .selectedDate(this.selectedDateTime)
                .bookedDate(LocalDate.now())
                .status(this.status)
                .build();
    }
}