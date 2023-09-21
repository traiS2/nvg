package com.trais2.neighborvegetablegarden.controllers;

import com.trais2.neighborvegetablegarden.controllers.controllerImpl.login.LoginByUsernameImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import payload.request.login.LoginByUsernameRequest;
import payload.response.login.LoginByUsernameResponse;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class LoginController {
    @Autowired
    private LoginByUsernameImpl loginByUsernameImpl;

    @PostMapping("/login-by-username")
    public ResponseEntity<?> loginByUsername(@Valid @RequestBody LoginByUsernameRequest request) {

        LoginByUsernameResponse response = (LoginByUsernameResponse) loginByUsernameImpl.login(new LoginByUsernameRequest(request.getUsername(), request.getPassword()));
        if(response != null) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body("User isn't exist");
    }
}
