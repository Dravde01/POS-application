package com.example.demo.api;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path="get")
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @PostMapping
    public void addNewCustomer(@RequestBody Customer customer){
        customerService.addNewCustomer(customer);
    }
}
