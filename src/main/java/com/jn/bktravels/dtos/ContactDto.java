package com.jn.bktravels.dtos;


import com.jn.bktravels.Model.Contact;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ContactDto {
    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

@NotBlank(message = "Email is required")
@Email(message = "Email is invalid")
    private String email;

    @NotBlank(message = "Country is required")
    private String country;

    @NotBlank(message = "Message is required")
    private String message;


    public Contact toEntity(){

        return Contact.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .country(country)
                .message(message)
                .build();
    }
}
