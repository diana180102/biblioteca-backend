package com.example.library.controller;

import com.example.library.dto.request.loan.RegisterLoanRequest;
import com.example.library.dto.response.ApiResponse;
import com.example.library.dto.response.LoanResponse;
import com.example.library.service.LoanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping
    public ResponseEntity<ApiResponse<String>> createLoan (@Valid @RequestBody RegisterLoanRequest registerLoanRequest ) {
        loanService.createLoan(registerLoanRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<String>("Prestamo creado", null));
    }



    @GetMapping
    public ResponseEntity<ApiResponse<List<LoanResponse>>> getLoans (
            @RequestParam UUID user,
            @RequestParam String isbn
            ) {

        List<LoanResponse> loan = loanService.findLoans(user, isbn);
        return  ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Lista de prestamos", loan));
    }

}
