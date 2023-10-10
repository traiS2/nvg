package com.trais2.neighborvegetablegarden.controller;

import com.trais2.neighborvegetablegarden.service.LoginService;
import com.trais2.neighborvegetablegarden.serviceImpl.login.LoginByPhoneNumber;
import com.trais2.neighborvegetablegarden.serviceImpl.login.LoginByUsernameImpl;
import com.trais2.neighborvegetablegarden.utils.validate.AccountValidate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import payload.request.login.LoginByUsernameRequest;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class LoginController {
    private final LoginService<LoginByUsernameRequest> loginByUsernameService;
    private final LoginService loginByPhoneNumberService;
    private final LoginService loginByEmailService;

    public LoginController(@Qualifier("loginByUsernameService") LoginService<LoginByUsernameRequest> loginByUsernameService,
                           @Qualifier("loginByPhoneNumberService") LoginService loginByPhoneNumberService,
                           @Qualifier("loginByEmailService") LoginService loginByEmailService) {
        this.loginByUsernameService = loginByUsernameService;
        this.loginByPhoneNumberService = loginByPhoneNumberService;
        this.loginByEmailService = loginByEmailService;
    }
    @Autowired
    private LoginByUsernameImpl loginByUsernameImpl;

    @Autowired
    private AccountValidate accountValidate;

    @PostMapping("/login-by-username")
    public ResponseEntity<?> loginByUsername(@Valid @RequestBody LoginByUsernameRequest request) {
        String message = accountValidate.checkUsernameExists(request.getUsername());

        if(message != null) {
            return ResponseEntity.badRequest().body(message);
        }

        return loginByUsernameService.login(new LoginByUsernameRequest(request.getUsername(), request.getPassword()));
    }

    @PostMapping("/login-by-phone-number")
    public ResponseEntity<?> loginByPhoneNumber() {
//        loginByPhoneNumber.login()
        return null;
    }

}
