package com.yigitcanyontem.library.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Date;

@Entity
@Table(name = "book")
@Data
@AllArgsConstructor
@NoArgsConstructor
@OnDelete(action = OnDeleteAction.CASCADE)
public class Book {
    @Id
    private Integer bookId;
    private String title;
    private String isbn13;
    @Column(name = "num_pages")
    private Integer numPages;
    @Column(name = "publication_date")
    private Date publicationDate;

    @OneToOne()
    @JoinColumn(name = "customer_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Customer customer;

    @ManyToOne()
    @JoinColumn(name = "language_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Book_Language bookLanguage;

    @ManyToOne()
    @JoinColumn(name = "publisher_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Publisher publisher;

}
