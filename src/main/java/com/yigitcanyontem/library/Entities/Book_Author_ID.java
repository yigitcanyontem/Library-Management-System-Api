package com.yigitcanyontem.library.Entities;

import java.io.Serializable;
import java.util.Objects;

public class Book_Author_ID implements Serializable {
    private Book book;
    private Integer authorId;

    public Book_Author_ID(Book book, Integer authorId) {
        this.book = book;
        this.authorId = authorId;
    }

    public Book_Author_ID() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book_Author_ID that = (Book_Author_ID) o;
        return Objects.equals(book, that.book) && Objects.equals(authorId, that.authorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, authorId);
    }
}
