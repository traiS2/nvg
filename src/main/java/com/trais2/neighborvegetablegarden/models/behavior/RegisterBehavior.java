package com.trais2.neighborvegetablegarden.models.behavior;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface RegisterBehavior<T> {
    ResponseEntity<?> register(T registerRequest);
}
