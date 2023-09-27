package com.trais2.neighborvegetablegarden.repository;

import com.trais2.neighborvegetablegarden.models.entity.user.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);

    Boolean existsAccountByUsername(String name);
}
