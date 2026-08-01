package com.example.library.dto.response;

import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record UserResponse(
        UUID id,
        String documentNumber,
        String firstName,
        String lastName,
        String email,
        LocalDate birthDate
) {

}
