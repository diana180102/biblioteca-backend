package com.example.library.service;

import com.example.library.common.enums.CopyStatus;
import com.example.library.common.enums.LoanStatus;
import com.example.library.common.mappers.LoanMapper;
import com.example.library.dto.request.loan.RegisterLoanRequest;
import com.example.library.dto.response.LoanResponse;
import com.example.library.entity.BookReplica;

import com.example.library.entity.Loan;
import com.example.library.entity.User;
import com.example.library.exception.ConflictException;
import com.example.library.exception.ResourceNotFoundException;
import com.example.library.repository.BookReplicaRepository;
import com.example.library.repository.LoanRepository;
import com.example.library.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final BookReplicaRepository bookReplicaRepository;
    private final LoanMapper loanMapper;

    @Transactional
   public void createLoan(RegisterLoanRequest registerLoanRequest){

        User user = userRepository.findById(registerLoanRequest.userId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        BookReplica copy = bookReplicaRepository.findById(registerLoanRequest.bookCopyId())
                .orElseThrow(() -> new ResourceNotFoundException("Ejemplar no encontrado"));

        if (copy.getStatus() != CopyStatus.AVAILABLE) {
            throw new ConflictException("El ejemplar no está disponible.");
        }


        if (loanRepository.existsActiveLoanByUser(
                registerLoanRequest.userId(),
                LoanStatus.ACTIVE)) {
            throw new ConflictException("El usuario ya tiene un préstamo activo.");
        }

        copy.setStatus(CopyStatus.LOANED);

        loanRepository.save(loanMapper.toLoan(user, copy));
    }


    @Transactional
    public List<LoanResponse> findLoans(UUID userId, String isbn) {

        List<Loan> loans = loanRepository.findLoans(userId, isbn);

        loans.forEach(this::updateLoanStatus);

        return loans.stream()
                .map(loanMapper::toLoanResponse)
                .toList();
    }

//    Evalua el status del prestamo
    private LoanStatus calculateStatus(Loan loan) {

        if (loan.getReturnDate() != null) {
            return LoanStatus.RETURNED;
        }

        if (LocalDate.now().isAfter(loan.getDueDate())) {
            return LoanStatus.OVERDUE;
        }

        return LoanStatus.ACTIVE;
    }

//    Actualiza el status SI su status cambia
    private void updateLoanStatus(Loan loan) {

        LoanStatus newStatus = calculateStatus(loan);

        if (loan.getStatus() != newStatus) {
            loan.setStatus(newStatus);
            loanRepository.save(loan);
        }
    }




}
