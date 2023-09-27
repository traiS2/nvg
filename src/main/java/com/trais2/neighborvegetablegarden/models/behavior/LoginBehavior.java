package com.trais2.neighborvegetablegarden.models.behavior;

import org.springframework.http.ResponseEntity;

public interface LoginBehavior<T> {
    ResponseEntity<?> login(T loginRequest);
}
