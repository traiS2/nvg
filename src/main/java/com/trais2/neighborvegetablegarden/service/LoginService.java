package com.trais2.neighborvegetablegarden.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface LoginService<T> {
    ResponseEntity<?> login(T loginRequest);
}

