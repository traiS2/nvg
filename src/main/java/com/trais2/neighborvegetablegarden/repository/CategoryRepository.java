package com.trais2.neighborvegetablegarden.repository;

import com.trais2.neighborvegetablegarden.model.entity.store.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("SELECT  C FROM Category C LEFT JOIN FETCH C.products")
    List<Category> getAllCategoriesWithProducts();
}
