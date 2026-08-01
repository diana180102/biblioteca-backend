package com.example.library.controller;

import com.example.library.dto.request.book.RegisterBookRequest;
import com.example.library.dto.request.book.UpdateBookRequest;
import com.example.library.dto.response.ApiResponse;
import com.example.library.dto.response.BookResponse;

import com.example.library.dto.response.ListBookResponse;
import com.example.library.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BooksController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<ApiResponse<String>> createBook (@Valid @RequestBody RegisterBookRequest registerBookRequest) {
        bookService.createBook(registerBookRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("Libro registrado", null));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> updateBook (@Valid @RequestBody UpdateBookRequest updateBookRequest, @PathVariable UUID id) {
        bookService.updateBook(updateBookRequest, id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Libro actualizado", null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteBook (@PathVariable UUID id) {
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Libro eliminado", null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BookResponse>> findBookById (@PathVariable UUID id) {
        BookResponse bookResponse = bookService.findBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Libro", bookResponse));
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<Page<BookResponse>>> getBooks (
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size)  {

        Page<BookResponse> books = bookService.findAllBooks(page, size);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Lista de Libros", books));
    }

    @GetMapping("/listByIsbn")
    public ResponseEntity<ApiResponse<List<ListBookResponse>>> getBooksByAuthor(
            @RequestParam String isbn
    ) {
        List<ListBookResponse> books = bookService.findAvailableReplicas(isbn);
        return  ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Lista de libros",  books));
    }
}
