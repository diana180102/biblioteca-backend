package com.example.library.repository;

import com.example.library.entity.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Authors, UUID> {
    boolean existsByIsni(String isni);

    Optional<Authors> findByIsni(String isni);
}
