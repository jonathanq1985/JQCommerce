package com.jqdigitalsolutions.jqcommerce.auth.service;

import com.jqdigitalsolutions.jqcommerce.auth.dto.LoginRequest;
import com.jqdigitalsolutions.jqcommerce.auth.dto.LoginResponse;
import com.jqdigitalsolutions.jqcommerce.auth.security.JwtService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtService jwtService;

    public AuthService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequest request) {

        String token =
                jwtService.generateToken(
                        request.username()
                );

        return new LoginResponse(
                token,
                "Bearer"
        );

    }
}