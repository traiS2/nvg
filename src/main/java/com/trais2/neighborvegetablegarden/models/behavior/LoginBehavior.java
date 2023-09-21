package com.trais2.neighborvegetablegarden.models.behavior;

import com.trais2.neighborvegetablegarden.models.entity.User;
import org.springframework.http.ResponseEntity;

public interface LoginBehavior<T> {
    ResponseEntity<?> login(T loginRequest);
}
