package com.jn.bktravels.dtos;

import com.jn.bktravels.Model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    @NotEmpty(message = "Username cannot be blank")
    @NotNull(message = "Username Cannot be Null")
    private String username;

    @NotEmpty(message = "Password cannot be blank")
    @NotNull(message = "Password Cannot be Null")
    private String password;

    @NotEmpty(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    @NotNull(message = "Email Can NOt Be Null")
    private String email;

    // Proper toEntity conversion method
    public User toEntity() {
        return User.builder()
                .username(this.username)
                .password(this.password)
                .email(this.email)
                .build();
    }
}
