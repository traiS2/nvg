package com.trais2.neighborvegetablegarden.repository;

import com.trais2.neighborvegetablegarden.models.entity.store.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
