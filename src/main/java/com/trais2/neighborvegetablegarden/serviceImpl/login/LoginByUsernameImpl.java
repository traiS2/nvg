package com.trais2.neighborvegetablegarden.serviceImpl.login;

import com.trais2.neighborvegetablegarden.security.jwt.JwtUtils;
import com.trais2.neighborvegetablegarden.security.services.UserDetailsImplement;
import com.trais2.neighborvegetablegarden.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import payload.request.login.LoginByUsernameRequest;
import payload.response.login.LoginByUsernameResponse;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Qualifier("loginByUsernameService")
public class LoginByUsernameImpl implements LoginService<LoginByUsernameRequest> {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    @Autowired
    public LoginByUsernameImpl(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public ResponseEntity<?> login(LoginByUsernameRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        } catch (AuthenticationException e) {
            if (e instanceof BadCredentialsException) {
                return ResponseEntity.badRequest().body("Incorrect password");
            } else {
                return ResponseEntity.badRequest().body(e);
            }
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        UserDetailsImplement userDetails = (UserDetailsImplement) authentication.getPrincipal();

        SecurityContextHolder.getContext().setAuthentication(authentication);
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        UserDetailsImplement userDetailsImplement = (UserDetailsImplement) authentication.getPrincipal();

        List<String> roles = userDetailsImplement.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        LoginByUsernameResponse response = new LoginByUsernameResponse(userDetailsImplement.getId(), userDetailsImplement.getUsername(), roles);
//        return ResponseEntity.ok(response);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).
                body(response);
    }
}
