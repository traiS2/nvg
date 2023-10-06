package com.trais2.neighborvegetablegarden.services.register;

import com.trais2.neighborvegetablegarden.models.behavior.RegisterBehavior;
import com.trais2.neighborvegetablegarden.models.entity.user.Account;
import com.trais2.neighborvegetablegarden.models.enums.ERole;
import com.trais2.neighborvegetablegarden.models.entity.user.Role;
import com.trais2.neighborvegetablegarden.models.entity.user.User;
import com.trais2.neighborvegetablegarden.repository.AccountRepository;
import com.trais2.neighborvegetablegarden.repository.RoleRepository;
import com.trais2.neighborvegetablegarden.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import payload.response.MessageResponse;

import java.util.HashSet;
import java.util.Set;

@Component
public class RegisterByUsernameImpl implements RegisterBehavior<Account> {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> register(Account account) {

        if (existsUsername(account.getUsername())) {
            ResponseEntity.badRequest().body("Error: Username is already taken!");
        }

        Set<Role> roles = new HashSet<>();

        Role role = roleRepository.findByName(ERole.ROLE_SELLER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(role);

        account.setPassword(passwordEncoder.encode(account.getPassword()));
        User user = new User(account, roles);
        account.setUser(user);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    private boolean existsUsername(String username) {
        return userRepository.existsUserByAccount_Username(username);
    }
}
