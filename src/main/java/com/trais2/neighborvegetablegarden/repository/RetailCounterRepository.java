package com.trais2.neighborvegetablegarden.repository;

import com.trais2.neighborvegetablegarden.model.entity.store.RetailCounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetailCounterRepository extends JpaRepository<RetailCounter, Integer> {
}
