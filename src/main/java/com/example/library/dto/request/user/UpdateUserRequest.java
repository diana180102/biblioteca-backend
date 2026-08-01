package com.example.library.dto.request.user;

import jakarta.validation.constraints.Email;

import java.util.Date;


public record UpdateUserRequest(
        String firstName,
        String lastName,
        @Email
        String email,
        String documentNumber,
        Date birthDate
) {

}
