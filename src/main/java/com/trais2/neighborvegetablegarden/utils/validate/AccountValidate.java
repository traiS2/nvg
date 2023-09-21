package com.trais2.neighborvegetablegarden.utils.validate;

import com.trais2.neighborvegetablegarden.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountValidate {

    @Autowired
    private AccountRepository accountRepository;

    public String checkUsernameExists(String username) {
        boolean check = accountRepository.existsAccountByUsername(username);
        return check ? null : "User do not exist";
    }
}
