package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.dao.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void addNewCustomer(Customer customer) {
        System.out.println(customer);
        Optional<Customer> customerOptional = customerRepository.findCustomerByCustomerNo(customer.getCustomerNo());
        if (customerOptional.isPresent()){
            throw new IllegalStateException("customerNo already taken");
        }
        customerRepository.save(customer);
    }

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }
}
