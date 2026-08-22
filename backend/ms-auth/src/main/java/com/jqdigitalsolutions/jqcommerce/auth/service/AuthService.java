package com.jqdigitalsolutions.jqcommerce.auth.service;

import com.jqdigitalsolutions.jqcommerce.auth.dto.LoginRequest;
import com.jqdigitalsolutions.jqcommerce.auth.dto.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public LoginResponse login(LoginRequest request) {

        return new LoginResponse(
                "TOKEN_PRUEBA",
                "Bearer"
        );

    }

}