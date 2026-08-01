package com.example.library.common.mappers;

import com.example.library.dto.request.book.RegisterBookRequest;
import com.example.library.dto.response.AuthorResponse;
import com.example.library.dto.response.BookResponse;
import com.example.library.entity.Authors;
import com.example.library.entity.Books;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class BookMapper {

    private final AuthorMapper authorMapper;

    public BookMapper(AuthorMapper authorMapper) {
        this.authorMapper = authorMapper;
    }

    public Books toBooks(RegisterBookRequest registerBookRequest, Authors author) {
        return Books.builder()
                .title(registerBookRequest.title())
                .isbn(registerBookRequest.isbn())
                .edition(registerBookRequest.edition())
                .datePublished(registerBookRequest.datePublished())
                .author(author)
                .build();
    }

    public BookResponse toBookResponse(Books books) {
        return BookResponse.builder()
                .id(books.getId())
                .title(books.getTitle())
                .isbn(books.getIsbn())
                .edition(books.getEdition())
                .datePublished(LocalDate.parse(String.valueOf(books.getDatePublished())))
                .author(authorMapper.toAuthorResponse(books.getAuthor()))
                .build();
    }
}
