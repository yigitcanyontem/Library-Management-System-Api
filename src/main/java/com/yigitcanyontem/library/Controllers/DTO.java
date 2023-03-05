package com.yigitcanyontem.library.Controllers;

import com.yigitcanyontem.library.Entities.Author;
import com.yigitcanyontem.library.Entities.Book;

import java.util.Optional;

public class DTO {
    private Optional<Book> book;
    private Optional<Author> author;

    public Optional<Book> getBook() {
        return book;
    }

    public void setBook(Optional<Book> book) {
        this.book = book;
    }

    public Optional<Author> getAuthor() {
        return author;
    }

    public void setAuthor(Optional<Author> author) {
        this.author = author;
    }





}
