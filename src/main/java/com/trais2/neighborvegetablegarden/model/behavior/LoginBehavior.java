package com.trais2.neighborvegetablegarden.model.behavior;

import org.springframework.http.ResponseEntity;

public interface LoginBehavior<T> {
    ResponseEntity<?> login(T loginRequest);
}
