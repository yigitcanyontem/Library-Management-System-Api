package com.yigitcanyontem.library.Services;

import com.yigitcanyontem.library.Entities.Book;
import com.yigitcanyontem.library.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> allBooks(){
        return bookRepository.findAll();
    }

    public Optional<Book> singleBookById(Integer book_id){
        return bookRepository.findBookByBookId(book_id);
    }

    public Optional<Book> singleBookByLanguage(Integer language_id){
        return bookRepository.findBookByBookLanguage_LanguageId(language_id);
    }

    public Optional<Book> singleBookByISBN(String isbn13){
        return bookRepository.findBookByIsbn13(isbn13);
    }

    public Optional<Book> singleBookByCustomer(Integer customer_id){
        return bookRepository.findBookByCustomer_CustomerId(customer_id);
    }

    public Integer assignCustomer(Integer bookId, Integer customerId){
        return bookRepository.assignCustomer(bookId,customerId);
    }
}
