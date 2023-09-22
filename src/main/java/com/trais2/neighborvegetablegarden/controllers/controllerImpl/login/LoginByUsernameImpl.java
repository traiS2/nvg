package com.trais2.neighborvegetablegarden.controllers.controllerImpl.login;

import com.trais2.neighborvegetablegarden.models.behavior.LoginBehavior;
import com.trais2.neighborvegetablegarden.security.jwt.JwtUtils;
import com.trais2.neighborvegetablegarden.security.services.UserDetailsImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import payload.request.login.LoginByUsernameRequest;
import payload.response.login.LoginByUsernameResponse;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoginByUsernameImpl implements LoginBehavior<LoginByUsernameRequest> {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

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

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImplement userDetailsImplement = (UserDetailsImplement) authentication.getPrincipal();

        List<String> roles = userDetailsImplement.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        LoginByUsernameResponse response = new LoginByUsernameResponse(jwt, userDetailsImplement.getId(), userDetailsImplement.getUsername(), roles);
        return ResponseEntity.ok(response);
    }
}
