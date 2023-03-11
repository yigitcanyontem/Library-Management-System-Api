package com.yigitcanyontem.library.Entities;

import jakarta.persistence.Column;

import java.sql.Date;

public class BookModel {
    private Integer bookId;
    private String title;
    private String isbn13;
    private Integer numPages;
    private Date publicationDate;
    private String  language_id;
    private String publisher_id;
    private String authorName;


    @Override
    public String toString() {
        return "BookModel{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", isbn13='" + isbn13 + '\'' +
                ", numPages=" + numPages +
                ", publicationDate=" + publicationDate +
                ", language_id='" + language_id + '\'' +
                ", publisher_id='" + publisher_id + '\'' +
                ", authorName='" + authorName + '\'' +
                '}';
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public Integer getNumPages() {
        return numPages;
    }

    public void setNumPages(Integer numPages) {
        this.numPages = numPages;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(String language_id) {
        this.language_id = language_id;
    }

    public String getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(String publisher_id) {
        this.publisher_id = publisher_id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public BookModel(Integer bookId, String title, String isbn13, Integer numPages, Date publicationDate, String language_id, String publisher_id, String authorName) {
        this.bookId = bookId;
        this.title = title;
        this.isbn13 = isbn13;
        this.numPages = numPages;
        this.publicationDate = publicationDate;
        this.language_id = language_id;
        this.publisher_id = publisher_id;
        this.authorName = authorName;
    }
}
