package com.example.demo.configuration;

import com.example.demo.dao.ProductRepository;
import com.example.demo.model.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ProductConfig {

    @Bean
    CommandLineRunner commandLineRunnerProduct(ProductRepository repository) {
        return args -> {
            Product product1 = new Product(
                    "1234",
                    "12,00"
            );

            Product product2 = new Product(
                    "5678",
                    "12,10"
            );

          /*  repository.saveAll(
                    List.of(product1, product2)
            );*/
        };
    }

    }