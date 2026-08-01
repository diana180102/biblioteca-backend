package com.example.library.dto.request.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record UpdateBookRequest(

        String title,


        String isbn,


        String edition,

        Date datePublished
) {
}
