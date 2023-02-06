package com.yigitcanyontem.library.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book_language")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book_Language {
    @Id
    @Column(name = "language_id")
    private Integer languageId;
    @Column(name = "language_code")
    private String languageCode;
    @Column(name = "language_name")
    private String languageName;
}
