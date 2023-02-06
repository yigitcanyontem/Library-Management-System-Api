package com.yigitcanyontem.library.Services;

import com.yigitcanyontem.library.Entities.Author;
import com.yigitcanyontem.library.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> allAuthors(){
        return authorRepository.findAll();
    }

    public Optional<Author> singleAuthor(Integer author_id){
        return authorRepository.findAuthorByAuthorId(author_id);
    }
}
