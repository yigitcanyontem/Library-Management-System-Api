package com.yigitcanyontem.library.Controllers;

import com.yigitcanyontem.library.Entities.Author;
import com.yigitcanyontem.library.Entities.Book;
import com.yigitcanyontem.library.Entities.Book_Author;
import com.yigitcanyontem.library.Entities.Customer;
import com.yigitcanyontem.library.Repository.CustomerRepository;
import com.yigitcanyontem.library.Services.*;
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

    @Autowired
    private Book_AuthorService bookAuthorService;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/books/id/{bookId}")
    public ResponseEntity<List<DTO>> getSingleBookById(@PathVariable Integer bookId){
        Optional<Book> book = bookService.singleBookById(bookId);
        Optional<Author> author = authorService.singleAuthor(bookAuthorService.singleAuthor(bookId).get().get(0).getAuthorId());

        DTO dto = new DTO();
        dto.setBook(book);
        dto.setAuthor(author);

        return new ResponseEntity<List<DTO>>(List.of(dto),HttpStatus.OK);
    }

    @GetMapping("/books/isbn/{isbn13}")
    public ResponseEntity<List<DTO>> getSingleBookByISBN(@PathVariable String isbn13){
        Optional<Book> book = bookService.singleBookByISBN(isbn13);
        Optional<Author> author = authorService.singleAuthor(bookAuthorService.singleAuthor(bookService.singleBookByISBN(isbn13).get().getBookId()).get().get(0).getAuthorId());

        DTO dto = new DTO();
        dto.setBook(book);
        dto.setAuthor(author);

        return new ResponseEntity<List<DTO>>(List.of(dto),HttpStatus.OK);
    }

    public ResponseEntity<Optional<Author>> getSingleAuthor(@PathVariable Integer bookId){
        return new ResponseEntity<Optional<Author>>(authorService.singleAuthor(bookAuthorService.singleAuthor(bookId).get().get(0).getAuthorId()),HttpStatus.OK);
    }
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(){
        return new ResponseEntity<List<Book>>(bookService.allBooks(), HttpStatus.OK);
    }

    @PostMapping("/customer/new")
    public String newCustomer(@ModelAttribute Customer customer){
        System.out.println(customer.toString());

        customerRepository.save(customer);

        return "Customer Added";
    }

    @GetMapping("/customer/{customer_id}")
    public ResponseEntity<Optional<Customer>> getSingleCustomer(@PathVariable Integer customer_id){
        return new ResponseEntity<Optional<Customer>>(customerService.singleCustomerByID(customer_id), HttpStatus.OK);
    }

    @PutMapping("/books/bookid={bookId}/customerid={customerId}")
    public String assignCustomer(@PathVariable Integer bookId,@PathVariable Integer customerId){
        return bookService.assignCustomer(bookId,customerId)+" Customer Assigned";
    }
}
