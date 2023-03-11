package com.yigitcanyontem.library.Repository;


import com.yigitcanyontem.library.Entities.Book_Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface Book_AuthorRepository extends JpaRepository<Book_Author,Integer> {
    Optional<List<Book_Author>> findAuthorByBook_BookId(Integer book_bookId);
    @Modifying
    @Transactional
    @Query("DELETE FROM Book_Author b WHERE b.book.bookId = :bookId")
    void deleteBook(Integer bookId);
}

