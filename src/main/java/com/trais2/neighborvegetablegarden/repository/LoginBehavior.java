package com.trais2.neighborvegetablegarden.repository;

import org.springframework.http.ResponseEntity;

public interface LoginBehavior<T> {
    ResponseEntity<?> login(T loginRequest);
}
