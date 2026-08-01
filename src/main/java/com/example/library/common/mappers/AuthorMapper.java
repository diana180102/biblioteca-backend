package com.example.library.common.mappers;

import com.example.library.dto.request.book.RegisterAuthorRequest;

import com.example.library.dto.response.AuthorResponse;
import com.example.library.entity.Authors;
import org.springframework.stereotype.Service;

@Service
public class AuthorMapper {

    public Authors toAuthor(RegisterAuthorRequest registerAuthorRequest) {
        return Authors.builder()
                .lastName(registerAuthorRequest.lastName())
                .firstName(registerAuthorRequest.firstName())
                .isni(registerAuthorRequest.isni())
                .build();
    }

    public AuthorResponse toAuthorResponse(Authors authors) {
        return AuthorResponse.builder()
                .id(authors.getId())
                .isni(authors.getIsni())
                .firstName(authors.getFirstName())
                .lastName(authors.getLastName())
                .build();
    }
}
