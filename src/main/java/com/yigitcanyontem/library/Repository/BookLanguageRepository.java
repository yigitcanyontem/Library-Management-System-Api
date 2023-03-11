package com.yigitcanyontem.library.Repository;

import com.yigitcanyontem.library.Entities.Book_Author;
import com.yigitcanyontem.library.Entities.Book_Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BookLanguageRepository extends JpaRepository<Book_Language,Integer> {


}

