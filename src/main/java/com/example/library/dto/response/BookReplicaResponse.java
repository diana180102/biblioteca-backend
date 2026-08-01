package com.example.library.dto.response;

import lombok.Builder;

@Builder
public record BookReplicaResponse(
        String codeInventory
) {
}
