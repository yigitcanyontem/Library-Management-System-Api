package com.yigitcanyontem.library.Controllers;

import com.yigitcanyontem.library.DTO.DTO;
import com.yigitcanyontem.library.Entities.*;
import com.yigitcanyontem.library.Repository.*;
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
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookLanguageRepository bookLanguageRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private Book_AuthorRepository bookAuthorRepository;


    //GetMappings
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
    @GetMapping("/publishers")
    public ResponseEntity<List<Publisher>> getAllPublishers(){
        return new ResponseEntity<List<Publisher>>(publisherService.allPublishers(), HttpStatus.OK);
    }
    @GetMapping("/languages")
    public ResponseEntity<List<Book_Language>> getAllLanguages(){
        return new ResponseEntity<List<Book_Language>>(bookLanguageRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/customer/{customer_id}")
    public ResponseEntity<List<Optional<Customer>>> getSingleCustomer(@PathVariable Integer customer_id){
        Optional<Customer> customer = customerService.singleCustomerByID(customer_id);
        return new ResponseEntity<>(List.of(customer), HttpStatus.OK);
    }
    @GetMapping("/books/max")
    public List<Integer> getNextId(){
        return List.of(bookRepository.maxBookId());
    }
    @GetMapping("/books/customer/{customer_id}")
    public List<Book> findBooksByCustomer(@PathVariable Integer customer_id){
        Customer customer = customerService.singleCustomerByID(customer_id).orElseThrow();
        return bookRepository.findBooksByCustomer(customer);
    }


    //Post Mappings
    @PostMapping("/customer/new")
    public String newCustomer(@RequestBody Customer customer){
        customer.setCustomerId(customerRepository.maxCustomerId()+1);
        customerRepository.save(customer);
        return "Customer Added";
    }
    @PostMapping("/books/new")
    public String newBook(@RequestBody BookModel bookModel){
        Book book = new Book();
        book.setBookId(bookRepository.maxBookId()+1);
        book.setTitle(bookModel.getTitle());
        book.setIsbn13(bookModel.getIsbn13());
        book.setNumPages(bookModel.getNumPages());
        book.setPublicationDate(bookModel.getPublicationDate());
        book.setBookLanguage(bookLanguageRepository.getReferenceById(Integer.valueOf(bookModel.getLanguage_id())));
        book.setPublisher(publisherService.singlePublisher(Integer.valueOf(bookModel.getPublisher_id())));
        bookRepository.save(book);

        Author author = new Author(authorRepository.maxAuthorId()+1, bookModel.getAuthorName());
        authorRepository.save(author);

        Book_Author book_author = new Book_Author(authorRepository.maxAuthorId(),book);
        bookAuthorRepository.save(book_author);

        return "Book Added";
    }


    //PutMappings
    @PutMapping("/books/assignbook")
    public String assignCustomer(@RequestBody AssignModel assignModel){
        System.out.println(assignModel);
        return bookService.assignCustomer(assignModel.getBookId(),assignModel.getCustomerId())  +" Customer Assigned";
    }

    @PutMapping("/books/return")
    public String returnBook(@RequestBody AssignModel assignModel){
        System.out.println(assignModel);
        return bookService.assignCustomer(assignModel.getBookId(),assignModel.getCustomerId())  +" Customer Assigned";
    }

    //DeleteMappings
    @DeleteMapping("/books/id/{bookId}")
    void deleteBook(@PathVariable Integer bookId){
        try {
            bookAuthorService.deleteBA(bookId);
            bookService.deleteBook(bookId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @DeleteMapping("/customer/delete/{customerId}")
    void deleteCustomer(@PathVariable Integer customerId){
        try {
            customerRepository.deleteById(customerId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public ResponseEntity<Optional<Author>> getSingleAuthor(@PathVariable Integer bookId){
        return new ResponseEntity<Optional<Author>>(authorService.singleAuthor(bookAuthorService.singleAuthor(bookId).get().get(0).getAuthorId()),HttpStatus.OK);
    }

}
