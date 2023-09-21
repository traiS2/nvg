package com.trais2.neighborvegetablegarden.controllers;

import com.trais2.neighborvegetablegarden.controllers.controllerImpl.register.RegisterByUsernameImpl;
import com.trais2.neighborvegetablegarden.models.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import payload.request.register.RegisterByUsernameRequest;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class RegisterController {
    @Autowired
    private RegisterByUsernameImpl registerByUsernameImpl;

    @PostMapping("/register-by-username")
    public ResponseEntity<?> registerByUsername(@Valid @RequestBody RegisterByUsernameRequest request) {
        return registerByUsernameImpl.register(new Account(request.getUsername(), request.getPassword()));
    }
}
