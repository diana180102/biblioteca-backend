package com.example.library.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

import java.util.UUID;

@Builder
public record AuthorResponse(
        UUID id,
        String isni,
        String firstName,
        String lastName
) {


}
