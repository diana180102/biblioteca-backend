package com.example.library.dto.request.loan;

import java.util.UUID;

public record RegisterLoanRequest(
        UUID userId,
        UUID bookCopyId
) {
}
