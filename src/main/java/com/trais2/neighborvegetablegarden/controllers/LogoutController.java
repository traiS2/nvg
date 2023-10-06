package com.trais2.neighborvegetablegarden.controllers;

import com.trais2.neighborvegetablegarden.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import payload.response.MessageResponse;

@Controller
@RestController
@RequestMapping("/api/auth")
public class LogoutController {
    private final JwtUtils jwtUtils;
    @Autowired
    public LogoutController(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @RequestMapping("/logout")
    public ResponseEntity<?> logout() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(new MessageResponse("Logout successfully"));
    }
}
