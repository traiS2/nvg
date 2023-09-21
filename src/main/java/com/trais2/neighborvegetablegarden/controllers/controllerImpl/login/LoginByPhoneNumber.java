package com.trais2.neighborvegetablegarden.controllers.controllerImpl.login;

import com.trais2.neighborvegetablegarden.models.behavior.LoginBehavior;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoginByPhoneNumber implements LoginBehavior {
    @Override
    public ResponseEntity<?> login(Object loginRequest) {
        return null;
    }
}
