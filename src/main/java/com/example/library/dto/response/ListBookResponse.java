package com.example.library.dto.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ListBookResponse(
        BookResponse bookResponse,
        AuthorResponse author,
        UUID replicaId,
        String codeInventory,
        String status
) {

}
