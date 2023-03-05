package com.yigitcanyontem.library.Services;

import com.yigitcanyontem.library.Entities.Author;
import com.yigitcanyontem.library.Entities.Book_Author;
import com.yigitcanyontem.library.Repository.AuthorRepository;
import com.yigitcanyontem.library.Repository.Book_AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Book_AuthorService {
    @Autowired
    private Book_AuthorRepository bookAuthorRepository;

    public Optional<Book_Author> singleAuthor(Integer bookId){
        return bookAuthorRepository.findAuthorByBook_BookId(bookId);
    }
}
