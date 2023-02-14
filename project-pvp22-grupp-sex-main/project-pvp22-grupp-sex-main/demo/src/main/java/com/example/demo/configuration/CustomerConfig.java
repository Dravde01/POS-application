package com.example.demo.configuration;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.model.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.JANUARY;
import static java.time.Month.MAY;

@Configuration
public class CustomerConfig {

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository repository){
        return args -> {
            Customer customer1 = new Customer(
                    "Matt",
                    "Stonie",
                    LocalDate.of(1992, MAY, 24)
            );

            Customer customer2 = new Customer(
                    "Stephen",
                    "Hawking",
                    LocalDate.of(1942, JANUARY, 8)
            );

            repository.saveAll(
                    List.of(customer1, customer2)
            );
        };
    }
}
