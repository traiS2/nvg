package com.trais2.neighborvegetablegarden.security.services;

import com.trais2.neighborvegetablegarden.model.entity.user.User;
import com.trais2.neighborvegetablegarden.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImplement implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserDetailsImplement loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByAccount_Username(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return UserDetailsImplement.build(user);
    }
}
