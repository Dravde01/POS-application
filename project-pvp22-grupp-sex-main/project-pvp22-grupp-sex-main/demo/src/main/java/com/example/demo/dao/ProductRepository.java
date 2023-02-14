package com.example.demo.dao;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<String, Integer> {

    @Query("SELECT p FROM Product p WHERE p.barcode = ?1")
    Optional<Product> findProductByBarcode(String barcode);

}
