package com.trais2.neighborvegetablegarden.controllers;

import com.trais2.neighborvegetablegarden.config.GlobalExceptionHandler;
import com.trais2.neighborvegetablegarden.services.register.RegisterByUsernameImpl;
import com.trais2.neighborvegetablegarden.models.entity.user.Account;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import payload.request.register.RegisterByUsernameRequest;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class RegisterController {
    @Autowired
    private RegisterByUsernameImpl registerByUsernameImpl;

    @Autowired
    private GlobalExceptionHandler validationExceptionHandler;

    @PostMapping("/register-by-username")
    public ResponseEntity<?> registerByUsername(@Valid @RequestBody RegisterByUsernameRequest request, BindingResult bindingResult) {
        String errorMessage = validationExceptionHandler.getFirstErrorMessage(bindingResult);

        if (errorMessage != null) {
            return ResponseEntity.badRequest().body(errorMessage);
        } else {
            return registerByUsernameImpl.register(new Account(request.getUsername(), request.getPassword()));
        }
    }
}
