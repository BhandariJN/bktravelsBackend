package com.jn.bktravels.dtos;

import com.jn.bktravels.Model.Destination;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class DestinationDto {
    private Integer id;
    @NotNull(message = "Destination Name cannot be null")
    @NotEmpty(message = "Destination Name cannot be empty")
//    @Min(value = 3, message = "Destination Name should have at least 3 characters")
    private String destinationName;
    @NotNull(message = "Destination Description cannot be null")
    @NotEmpty(message = "Destination Description cannot be empty")
    private String destinationDescription;
    @NotNull(message = "Destination Price cannot be null")
    private Double destinationPrice;
    @NotNull(message = "Image File cannot be null")
    private MultipartFile imageFile;

    public Destination toEntity() {
        return Destination.builder()
                .destinationName(destinationName)
                .destinationDescription(destinationDescription)
                .destinationPrice(destinationPrice)
                .build();
    }
}
