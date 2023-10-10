package com.trais2.neighborvegetablegarden.model.behavior;

import org.springframework.http.ResponseEntity;

public interface RegisterBehavior<T> {
    ResponseEntity<?> register(T registerRequest);
}
