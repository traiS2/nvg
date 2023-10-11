package com.trais2.neighborvegetablegarden.repository;

import org.springframework.http.ResponseEntity;

public interface RegisterBehavior<T> {
    ResponseEntity<?> register(T registerRequest);
}
