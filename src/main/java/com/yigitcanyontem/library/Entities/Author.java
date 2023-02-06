package com.yigitcanyontem.library.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "author")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Author {
    @Id
    @Column(name = "author_id")
    private Integer authorId;
    @Column(name = "author_name")
    private String authorName;
}
