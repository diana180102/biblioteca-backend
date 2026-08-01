package com.example.library.repository;

import com.example.library.entity.BookReplica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookReplicaRepository extends JpaRepository<BookReplica, UUID> {

    @Query("""
    SELECT br
    FROM BookReplica br
    JOIN FETCH br.book b
    JOIN FETCH b.author
    WHERE b.isbn = :isbn
      AND br.status = CopyStatus.AVAILABLE
""")
    List<BookReplica> findAvailableReplicas(@Param("isbn") String isbn);

}
