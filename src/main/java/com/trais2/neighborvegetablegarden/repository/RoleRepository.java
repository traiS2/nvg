package com.trais2.neighborvegetablegarden.repository;

import com.trais2.neighborvegetablegarden.models.enums.ERole;
import com.trais2.neighborvegetablegarden.models.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
