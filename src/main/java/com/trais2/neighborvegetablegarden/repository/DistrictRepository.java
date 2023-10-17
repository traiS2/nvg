package com.trais2.neighborvegetablegarden.repository;

import com.trais2.neighborvegetablegarden.model.entity.address.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
}
