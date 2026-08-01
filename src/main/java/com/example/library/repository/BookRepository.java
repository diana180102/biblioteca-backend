package com.example.library.repository;

import com.example.library.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Books, UUID> {
   boolean existsByIsbn(String isbn);

   @Query("""
    SELECT b
    FROM Books b
    JOIN FETCH b.author
    WHERE b.id = :id
""")
   Optional<Books> findByIdWithAuthor(UUID id);

}
