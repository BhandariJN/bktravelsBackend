package com.jn.bktravels.dtos;

import com.jn.bktravels.Model.Booking;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {


    @NotBlank(message = "User Id is required")
        private Long userId;
    @NotBlank(message = "Destination Id is required")
    private Long destinationId;
    @NotBlank(message = "Number of Travellers is required")
    private Integer numberofTravellers;

    @NotNull(message = "Email is Required")
    private String email;

    @NotNull(message = "Phone No. is Required")
    private String phoneNumber;


    @NotBlank(message = "Total Amount is required")
    private double totalAmount;

    @NotBlank(message = "Date   is required")

    private LocalDateTime selectedDateTime;




    public Booking toEntity(){

        Booking build = Booking.builder()
                .numberofTravellers(this.numberofTravellers)
                .totalAmount(this.totalAmount)
                .id(this.userId)
                .id(this.destinationId)
                .selectedDate(this.selectedDateTime)
                .bookedDate(new Date())
                .build();
        return build;

    }


}
