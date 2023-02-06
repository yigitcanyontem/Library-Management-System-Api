package com.yigitcanyontem.library.Controllers;

import com.yigitcanyontem.library.Entities.Book;
import com.yigitcanyontem.library.Entities.Customer;
import com.yigitcanyontem.library.Services.AuthorService;
import com.yigitcanyontem.library.Services.BookService;
import com.yigitcanyontem.library.Services.CustomerService;
import com.yigitcanyontem.library.Services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/library")
public class LibraryController {
    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookService bookService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PublisherService publisherService;

    @GetMapping("/books/id/{bookId}")
    public ResponseEntity<Optional<Book>> getSingleBookById(@PathVariable Integer bookId){
        return new ResponseEntity<Optional<Book>>(bookService.singleBookById(bookId), HttpStatus.OK);
    }
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getSingleBookById(){
        return new ResponseEntity<List<Book>>(bookService.allBooks(), HttpStatus.OK);
    }
    @GetMapping("/books/isbn/{isbn13}")
    public ResponseEntity<Optional<Book>> getSingleBookByISBN(@PathVariable String isbn13){
        return new ResponseEntity<Optional<Book>>(bookService.singleBookByISBN(isbn13), HttpStatus.OK);
    }
    @GetMapping("/customer/{customer_id}")
    public ResponseEntity<Optional<Customer>> getSingleBook(@PathVariable Integer customer_id){
        return new ResponseEntity<Optional<Customer>>(customerService.singleCustomerByID(customer_id), HttpStatus.OK);
    }
}
