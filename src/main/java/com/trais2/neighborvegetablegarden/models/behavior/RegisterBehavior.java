package com.trais2.neighborvegetablegarden.models.behavior;

import org.springframework.http.ResponseEntity;

public interface RegisterBehavior<T> {
    ResponseEntity<?> register(T registerRequest);
}
