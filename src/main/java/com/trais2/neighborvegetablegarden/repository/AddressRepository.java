package com.trais2.neighborvegetablegarden.repository;

import com.trais2.neighborvegetablegarden.model.entity.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer>{
}
