package com.example.library.repository;

import com.example.library.common.enums.LoanStatus;
import com.example.library.entity.BookReplica;
import com.example.library.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface LoanRepository extends JpaRepository<Loan, UUID> {

    @Query("""
        SELECT COUNT(l) > 0
        FROM Loan l
        WHERE l.user.id = :userId
          AND l.status = :status
    """)
    boolean existsActiveLoanByUser(
            @Param("userId") UUID userId,
            @Param("status") LoanStatus status
    );


    @Query("""
    SELECT l
    FROM Loan l
    JOIN FETCH l.user u
    JOIN FETCH l.bookCopy br
    JOIN FETCH br.book b
    JOIN FETCH b.author
    WHERE (:userId IS NULL OR u.id = :userId)
      AND (:isbn IS NULL OR b.isbn = :isbn)
""")
    List<Loan> findLoans(
            @Param("userId") UUID userId,
            @Param("isbn") String isbn
    );

}
