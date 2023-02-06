package com.yigitcanyontem.library.Repository;

import com.yigitcanyontem.library.Entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {
    Optional<Author> findAuthorByAuthorId(Integer author_id);
}
