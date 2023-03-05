package com.yigitcanyontem.library.Repository;

import com.yigitcanyontem.library.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    Optional<Book> findBookByBookId(Integer book_id);
    Optional<Book> findBookByBookLanguage_LanguageId(Integer bookLanguage_language_id);
    Optional<Book> findBookByIsbn13(String isbn13);
    Optional<Book> findBookByCustomer_CustomerId(Integer customer_customer_id);

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.customer.customerId = :customerId WHERE b.bookId = :bookId")
    Integer assignCustomer(Integer bookId, Integer customerId);


}
