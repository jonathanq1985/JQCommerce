package com.jqdigitalsolutions.jqcommerce.auth.controller;

import com.jqdigitalsolutions.jqcommerce.auth.dto.LoginRequest;
import com.jqdigitalsolutions.jqcommerce.auth.dto.LoginResponse;
import com.jqdigitalsolutions.jqcommerce.auth.security.JwtService;
import com.jqdigitalsolutions.jqcommerce.auth.service.AuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    public AuthController(
            AuthService authService,
            JwtService jwtService) {

        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request) {

        return authService.login(request);
    }

    @GetMapping("/token-info")
    public String tokenInfo(
            @RequestParam String token) {

        return jwtService.extractUsername(token);
    }
}