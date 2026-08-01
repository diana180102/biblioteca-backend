package com.example.library.dto.response;

import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record LoanResponse(
        UUID id,
        UserResponse user,
        BookResponse book,
        BookReplicaResponse bookReplica,
        LocalDate loanDate,
        LocalDate dueDate,
        LocalDate dateReturn,
        String status
) {
}
