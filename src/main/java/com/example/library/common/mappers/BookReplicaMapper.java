package com.example.library.common.mappers;

import com.example.library.dto.response.BookReplicaResponse;
import com.example.library.dto.response.ListBookResponse;
import com.example.library.entity.BookReplica;
import com.example.library.entity.Books;
import org.springframework.stereotype.Service;




@Service
public class BookReplicaMapper {

    private final BookMapper bookMapper;
    private final AuthorMapper authorMapper;

    public BookReplicaMapper(BookMapper bookMapper, AuthorMapper authorMapper) {
        this.bookMapper = bookMapper;
        this.authorMapper = authorMapper;
    }



    public  BookReplicaResponse toBookReplicaResponse(BookReplica bookReplica) {
        return  BookReplicaResponse.builder()
                .codeInventory(bookReplica.getCode_inventory())
                .build();
    }

    public ListBookResponse toListBookResponse(BookReplica replica) {
        return ListBookResponse.builder()
                .bookResponse(bookMapper.toBookResponse(replica.getBook()))
                .author(authorMapper.toAuthorResponse(replica.getBook().getAuthor()))
                .replicaId(replica.getId())
                .codeInventory(replica.getCode_inventory())
                .status(replica.getStatus().name())
                .build();
    }
}
