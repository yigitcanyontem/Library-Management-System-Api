package com.yigitcanyontem.library.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book_author")
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(Book_Author_ID.class)
public class Book_Author {

    @Id
    @Column(name = "author_id")
    private Integer authorId;
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;
}
