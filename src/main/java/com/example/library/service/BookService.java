package com.example.library.service;

import com.example.library.common.mappers.AuthorMapper;
import com.example.library.common.mappers.BookMapper;
import com.example.library.common.mappers.BookReplicaMapper;
import com.example.library.dto.request.book.RegisterBookRequest;
import com.example.library.dto.request.book.UpdateBookRequest;
import com.example.library.dto.response.BookResponse;
import com.example.library.dto.response.ListBookResponse;
import com.example.library.entity.Authors;
import com.example.library.entity.BookReplica;
import com.example.library.entity.Books;
import com.example.library.exception.ConflictException;

import com.example.library.exception.ResourceNotFoundException;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookReplicaRepository;
import com.example.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class BookService {

    private final AuthorMapper authorMapper;
    private final BookMapper bookMapper;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookReplicaRepository bookReplicaRepository;
    private final BookReplicaMapper bookReplicaMapper;


    @Transactional
    public void createBook(RegisterBookRequest registerBookRequest) {

        if (bookRepository.existsByIsbn(registerBookRequest.isbn())) {
            throw new ConflictException("Book con ISBN " + registerBookRequest.isbn() + " ya existe");
        }


        Authors author = authorRepository
                .findByIsni(registerBookRequest.author().isni())
                .orElseGet(() ->
                        authorRepository.save(
                                authorMapper.toAuthor(registerBookRequest.author())
                        )
                );

        bookRepository.save(bookMapper.toBooks(registerBookRequest, author));

    }

    @Transactional
    public void updateBook(UpdateBookRequest updateBookRequest, UUID id) {
       Books book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        Optional.ofNullable(updateBookRequest.isbn()).ifPresent(book::setIsbn);
        Optional.ofNullable(updateBookRequest.title()).ifPresent(book::setTitle);
        Optional.ofNullable(updateBookRequest.edition()).ifPresent(book::setEdition);
        Optional.ofNullable(updateBookRequest.datePublished()).ifPresent(book::setDatePublished);

    }

    @Transactional
    public void deleteBook(UUID id) {
        Books book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        bookRepository.delete(book);
    }

    @Transactional(readOnly = true)
    public Page<BookResponse> findAllBooks(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

       return bookRepository
               .findAll(pageable)
               .map(bookMapper::toBookResponse);
    }

    @Transactional(readOnly = true)
    public List<ListBookResponse> findAvailableReplicas(String isbn) {

        return bookReplicaRepository.findAvailableReplicas(isbn)
                .stream()
                .map(bookReplicaMapper::toListBookResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public BookResponse findBookById(UUID id) {
        Books book = bookRepository.findByIdWithAuthor(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        return bookMapper.toBookResponse(book);
    }


}
