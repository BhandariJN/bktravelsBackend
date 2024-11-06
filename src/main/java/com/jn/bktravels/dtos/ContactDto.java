package com.jn.bktravels.dtos;

import com.jn.bktravels.Model.Contact;
import com.jn.bktravels.Model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactDto {

    @NotNull(message = "First name is required")
    private String firstName;

    @NotNull(message = "Last name is required")
    private String lastName;

    @Email(message = "Email is invalid")
    @NotNull(message = "Email is required")
    private String email;

    @NotNull(message = "Country is required")
    private String country;

    @NotNull(message = "Message is required")
    private String message;

    @NotNull(message = "User ID is required")
    private Integer userId;

    public Contact toEntity(User user) {
        return Contact.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .country(country)
                .message(message)
                .user(user)
                .build();
    }
}
