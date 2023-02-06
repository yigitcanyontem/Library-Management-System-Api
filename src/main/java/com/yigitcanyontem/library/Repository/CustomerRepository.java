package com.yigitcanyontem.library.Repository;

import com.yigitcanyontem.library.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Optional<Customer> findCustomerByCustomerId(Integer customer_id);
    Optional<Customer> findCustomerByEmail(String email);
}
