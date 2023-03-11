package com.yigitcanyontem.library.Services;

import com.yigitcanyontem.library.Entities.Customer;
import com.yigitcanyontem.library.Entities.Publisher;
import com.yigitcanyontem.library.Repository.CustomerRepository;
import com.yigitcanyontem.library.Repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService{
    @Autowired
    private PublisherRepository publisherRepository;

    public List<Publisher> allPublishers(){
        return publisherRepository.findAll();
    }

    public Publisher singlePublisher(Integer publisher_id){
        return publisherRepository.findPublisherByPublisherId(publisher_id);
    }

}
