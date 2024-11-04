package com.jn.bktravels.dtos;

import com.jn.bktravels.Model.Destination;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DestinationDto {


    @NotNull(message = "Destination Name cannot be null")
    @NotEmpty(message = "Destination Name cannot be empty")
    private String destinationName;

    @NotNull(message = "Destination Description cannot be null")
    @NotEmpty(message = "Destination Description cannot be empty")
    private String destinationDescription;

    @NotNull(message = "Destination Price cannot be null")
    private Double destinationPrice;

    @NotNull(message = "Image Url cannot be null")
    private String imageUrl;

    @NotNull(message = "Availability Can Not be Null")
    private List<LocalDateTime> availability;

    // Custom setter for availability
    public void setAvailability(List<String> availabilityStrings) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME; // Adjust format if needed
        this.availability = availabilityStrings.stream()
                .map(dateStr -> LocalDateTime.parse(dateStr, formatter))
                .collect(Collectors.toList());
    }

    public Destination toEntity() {
        return Destination.builder()
                .destinationName(destinationName)
                .destinationDescription(destinationDescription)
                .destinationPrice(destinationPrice)
                .imageUrl(imageUrl)
                .availability(availability)
                .build();
    }
}
