package com.trais2.neighborvegetablegarden.repository;

import com.trais2.neighborvegetablegarden.model.entity.address.Commune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommuneRepository extends JpaRepository<Commune, Integer> {
}
