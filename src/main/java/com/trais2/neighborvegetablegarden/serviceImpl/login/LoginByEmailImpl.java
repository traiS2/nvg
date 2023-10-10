package com.trais2.neighborvegetablegarden.serviceImpl.login;

import com.trais2.neighborvegetablegarden.service.LoginService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Qualifier("loginByEmailService")
public class LoginByEmailImpl implements LoginService {
    @Override
    public ResponseEntity<?> login(Object loginRequest) {
        return null;
    }
}
