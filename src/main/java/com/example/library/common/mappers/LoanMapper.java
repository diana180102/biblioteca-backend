package com.example.library.common.mappers;


import com.example.library.common.enums.LoanStatus;
import com.example.library.dto.response.LoanResponse;
import com.example.library.entity.BookReplica;
import com.example.library.entity.Loan;
import com.example.library.entity.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class LoanMapper {

    private final UserMapper userMapper;
    private final BookMapper bookMapper;
    private final BookReplicaMapper bookReplicaMapper;

    public LoanMapper(UserMapper userMapper, BookMapper bookMapper, BookReplicaMapper bookReplicaMapper) {
        this.userMapper = userMapper;
        this.bookMapper = bookMapper;
        this.bookReplicaMapper = bookReplicaMapper;
    }

    public Loan toLoan(User user, BookReplica bookCopy) {
        return Loan.builder()
                .user(user)
                .bookCopy(bookCopy)
                .loanDate(LocalDate.now())
                .dueDate(LocalDate.now().plusDays(14))
                .status(LoanStatus.ACTIVE)
                .build();
    }

    public LoanResponse toLoanResponse(Loan loan) {
        return LoanResponse.builder()
                .id(loan.getId())
                .user(userMapper.toUserResponse(loan.getUser()))
                .book(bookMapper.toBookResponse(loan.getBookCopy().getBook()))
                .bookReplica(bookReplicaMapper.toBookReplicaResponse(loan.getBookCopy()))
                .loanDate(loan.getLoanDate())
                .dueDate(loan.getDueDate())
                .dateReturn(loan.getReturnDate())
                .status(loan.getStatus().name())
                .build();
    }
}
