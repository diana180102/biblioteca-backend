package com.example.library.dto.response;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Builder
public record BookResponse(
        UUID id,
        AuthorResponse author,
        String title,
        String isbn,
        String edition,
        LocalDate datePublished
) {
}
