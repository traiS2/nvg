package com.trais2.neighborvegetablegarden.controllers;

import com.trais2.neighborvegetablegarden.controllers.controllerImpl.login.LoginByPhoneNumber;
import com.trais2.neighborvegetablegarden.controllers.controllerImpl.login.LoginByUsernameImpl;
import com.trais2.neighborvegetablegarden.utils.validate.AccountValidate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import payload.request.login.LoginByUsernameRequest;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class LoginController {
    @Autowired
    private LoginByUsernameImpl loginByUsernameImpl;

    @Autowired
    private LoginByPhoneNumber loginByPhoneNumber;

    @Autowired
    private AccountValidate accountValidate;

    @PostMapping("/login-by-username")
    public ResponseEntity<?> loginByUsername(@Valid @RequestBody LoginByUsernameRequest request) {
        String message = accountValidate.checkUsernameExists(request.getUsername());

        if(message != null) {
            return ResponseEntity.badRequest().body(message);
        }

        return loginByUsernameImpl.login(new LoginByUsernameRequest(request.getUsername(), request.getPassword()));
    }

    @PostMapping("/login-by-phone-number")
    public ResponseEntity<?> loginByPhoneNumber() {
//        loginByPhoneNumber.login()
        return null;
    }
}
