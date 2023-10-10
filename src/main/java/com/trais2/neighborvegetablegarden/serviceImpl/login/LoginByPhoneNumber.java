package com.trais2.neighborvegetablegarden.serviceImpl.login;

import com.trais2.neighborvegetablegarden.model.behavior.LoginBehavior;
import com.trais2.neighborvegetablegarden.service.LoginService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Qualifier("loginByPhoneNumberService")
public class LoginByPhoneNumber implements LoginService {
    @Override
    public ResponseEntity<?> login(Object loginRequest) {
        return null;
    }
}
