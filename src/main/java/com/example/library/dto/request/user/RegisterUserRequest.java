package com.example.library.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Date;


public record RegisterUserRequest(

        @NotBlank
        @Pattern(regexp = "\\d+", message = "El documento solo debe contener números")
        String documentNumber,

        @NotBlank
        String firstName,

        @NotBlank
        String lastName,

        @Email
        @NotBlank
        String email,

        @NotNull
        Date birthDate

) {
}
