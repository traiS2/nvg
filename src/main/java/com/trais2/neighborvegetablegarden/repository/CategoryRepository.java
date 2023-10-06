package com.trais2.neighborvegetablegarden.repository;

import com.trais2.neighborvegetablegarden.models.entity.store.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
