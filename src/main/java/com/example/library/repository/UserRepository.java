package com.example.library.repository;

import com.example.library.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByDocumentNumber(String documentNumber);

    User findByDocumentNumber(String documentNumber);

    Page <User> findByDocumentNumberContaining(String document, Pageable pageable);
}
