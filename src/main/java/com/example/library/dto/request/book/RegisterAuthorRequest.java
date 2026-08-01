package com.example.library.dto.request.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public record RegisterAuthorRequest(

        @NotBlank
        @Pattern(
                regexp = "^[0-9X]{16}$",
                message = "El ISNI debe contener 16 caracteres numéricos o terminar en X"
        )
        String isni,

        @NotBlank
        String firstName,

        @NotBlank
        String lastName
) {
}
