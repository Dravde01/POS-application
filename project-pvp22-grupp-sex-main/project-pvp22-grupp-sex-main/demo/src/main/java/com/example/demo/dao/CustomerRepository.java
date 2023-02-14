package com.example.demo.dao;

import com.example.demo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("SELECT c FROM Customer c WHERE c.customerNo = ?1")
    Optional<Customer> findCustomerByCustomerNo(Integer customerNo);

    @Query("SELECT c FROM Customer c WHERE c.lastName = ?1")
    Optional<Customer> findCustomerByLastName(String lastName);
}
