package com.example.library.dto.request.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record RegisterBookRequest(

        RegisterAuthorRequest author,

        @NotBlank
        String title,

        @NotBlank
        String isbn,

        @NotBlank
        String edition,

        @NotNull
        Date datePublished
) {
}
