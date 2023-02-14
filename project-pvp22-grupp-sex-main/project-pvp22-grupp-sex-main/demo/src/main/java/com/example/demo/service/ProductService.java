package com.example.demo.service;

import com.example.demo.dao.ProductRepository;
import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public void addNewProduct(Product product) {
        System.out.println(product);
        Optional<Product> productOptional = productRepository.findProductByBarcode(product.getBarcode());
        if (productOptional.isPresent()){
            throw new IllegalStateException("Barcode already taken");
        }
      //  productRepository.save(product);
    }

    public List<String> getProducts() {
        return productRepository.findAll();
    }

   /* public List<Product> getCustomers(){
        return productRepository.findAll();
    }*/
}
