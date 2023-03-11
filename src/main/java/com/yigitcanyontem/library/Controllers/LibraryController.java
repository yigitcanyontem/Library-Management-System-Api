package com.yigitcanyontem.library.Controllers;

import com.yigitcanyontem.library.DTO.DTO;
import com.yigitcanyontem.library.Entities.Author;
import com.yigitcanyontem.library.Entities.Book;
import com.yigitcanyontem.library.Entities.Customer;
import com.yigitcanyontem.library.Repository.CustomerRepository;
import com.yigitcanyontem.library.Services.*;
import org.hibernate.annotations.OrderBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/library")
@CrossOrigin("http://localhost:3000/")
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
        DTO dto = new DTO();
        Optional<Book> book = bookService.singleBookById(bookId);
        if (book.isPresent()){
            if (book.get().getCustomer() == null){
                book.get().setCustomer(new Customer(0,"","",""));
            }
            try {
                Optional<Author> author = authorService.singleAuthor(bookAuthorService.singleAuthor(bookId).get().get(0).getAuthorId());
                dto.setBook(book);
                dto.setAuthor(author);
                return new ResponseEntity<List<DTO>>(List.of(dto),HttpStatus.OK);
            }catch (Exception IndexOutOfBoundsException){
                Author authorNull = new Author(1,"Not Available");
                dto.setBook(book);
                dto.setAuthor(Optional.of(authorNull));
                return new ResponseEntity<List<DTO>>(List.of(dto),HttpStatus.OK);
            }
        }
        return null;
    }

    @GetMapping("/books/isbn/{isbn13}")
    public ResponseEntity<List<DTO>> getSingleBookByISBN(@PathVariable String isbn13){
        DTO dto = new DTO();
        Optional<Book> book = bookService.singleBookByISBN(isbn13);
        try {
            Optional<Author> author = authorService.singleAuthor(bookAuthorService.singleAuthor(bookService.singleBookByISBN(isbn13).get().getBookId()).get().get(0).getAuthorId());
            dto.setBook(book);
            dto.setAuthor(author);
            return new ResponseEntity<List<DTO>>(List.of(dto),HttpStatus.OK);
        }catch (Exception IndexOutOfBoundsException){
            Author authorNull = new Author(1,"Not Available");
            dto.setBook(book);
            dto.setAuthor(Optional.of(authorNull));
            return new ResponseEntity<List<DTO>>(List.of(dto),HttpStatus.OK);
        }


    }

    public ResponseEntity<Optional<Author>> getSingleAuthor(@PathVariable Integer bookId){
        return new ResponseEntity<Optional<Author>>(authorService.singleAuthor(bookAuthorService.singleAuthor(bookId).get().get(0).getAuthorId()),HttpStatus.OK);
    }

    @GetMapping("/books")
    public ResponseEntity<List<DTO>> getAllBooks(){
        List<DTO> dtoList = new ArrayList<>();
        List<Book> books = bookService.allBooks();
        books.sort(Comparator.comparing(Book::getBookId));
        int i = 0;
        Author authorNull = new Author(1,"Not Available");
        for (Book book:books){
            if (book.getCustomer() == null){
                book.setCustomer(new Customer(0,"","",""));
            }
            i++;
            try {
                Optional<Author> author = authorService.singleAuthor(bookAuthorService.singleAuthor(book.getBookId()).get().get(0).getAuthorId());
                DTO dto = new DTO();
                dto.setBook(Optional.of(book));
                dto.setAuthor(author);
                dtoList.add(dto);
            }catch (Exception IndexOutOfBoundsException){
                DTO dto = new DTO();
                dto.setBook(Optional.of(book));
                dto.setAuthor(Optional.of(authorNull));
                dtoList.add(dto);
            }
            if (i == 100){
                return new ResponseEntity<List<DTO>>(dtoList, HttpStatus.OK);

            }

        }
        return new ResponseEntity<List<DTO>>(dtoList, HttpStatus.OK);
    }


    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return new ResponseEntity<List<Customer>>(customerRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/customer/new")
    public String newCustomer(@RequestBody Customer customer){
        customer.setCustomerId(customerRepository.maxCustomerId());
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

    @DeleteMapping("/books/id/{bookId}")
    void deleteBook(@PathVariable Integer bookId){
        try {
            bookAuthorService.deleteBA(bookId);
            bookService.deleteBook(bookId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
