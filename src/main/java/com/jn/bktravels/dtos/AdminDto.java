package com.jn.bktravels.dtos;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AdminDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
