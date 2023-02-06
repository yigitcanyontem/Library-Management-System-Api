package com.yigitcanyontem.library.Services;

import com.yigitcanyontem.library.Entities.Customer;
import com.yigitcanyontem.library.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> allCustomers(){
        return customerRepository.findAll();
    }

    public Optional<Customer> singleCustomerByID(Integer customer_id){
        return customerRepository.findCustomerByCustomerId(customer_id);
    }
    public Optional<Customer> singleCustomerByEmail(String email){
        return customerRepository.findCustomerByEmail(email);
    }
}
