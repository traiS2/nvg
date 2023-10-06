package com.trais2.neighborvegetablegarden.services;

import com.trais2.neighborvegetablegarden.models.entity.store.Product;
import com.trais2.neighborvegetablegarden.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {
        return null;
    }
}
