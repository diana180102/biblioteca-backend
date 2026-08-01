package com.example.library.entity;

import com.example.library.common.enums.CopyStatus;
import jakarta.persistence.*;
import lombok.*;


import java.util.UUID;

@Entity()
@Table(name = "book_replica")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookReplica {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String code_inventory;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CopyStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Books book;
}
