package com.trais2.neighborvegetablegarden.controllers.controllerImpl.login;

import com.trais2.neighborvegetablegarden.models.behavior.LoginBehavior;
import com.trais2.neighborvegetablegarden.models.entity.User;
import com.trais2.neighborvegetablegarden.security.jwt.JwtUtils;
import com.trais2.neighborvegetablegarden.security.services.UserDetailsImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import payload.request.login.LoginByUsernameRequest;
import payload.response.login.LoginByUsernameResponse;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoginByUsernameImpl implements LoginBehavior<LoginByUsernameResponse, LoginByUsernameRequest> {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public LoginByUsernameResponse login(LoginByUsernameRequest loginRequest) {
        LoginByUsernameResponse response = null;

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImplement personDetails = (UserDetailsImplement) authentication.getPrincipal();
        List<String> roles = personDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        response = new LoginByUsernameResponse(jwt, personDetails.getId(), personDetails.getUsername(), roles);
        return  response;
    }
}
