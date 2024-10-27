package com.jn.bktravels.dtos;

import com.jn.bktravels.Model.Booking;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @NotBlank(message = "Price is required")

    @NotBlank(message = "Total Amount is required")
    private double totalAmount;
    private String status;




    public Booking toEntity(){

            return Booking.builder()
                    .numberofTravellers(this.numberofTravellers)
                    .totalAmount(this.totalAmount)
                    .id(this.userId)
                    .id(this.destinationId)

                    .build();

    }


}
