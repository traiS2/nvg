package com.trais2.neighborvegetablegarden.repository;

import com.trais2.neighborvegetablegarden.models.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByAccount_Username(String username);

    Boolean existsUserByAccount_Username(String username);
}
