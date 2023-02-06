package com.yigitcanyontem.library.Repository;

import com.yigitcanyontem.library.Entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PublisherRepository extends JpaRepository<Publisher,Integer> {
   Optional<Publisher> findPublisherByPublisherId(Integer publisher_id);
}
