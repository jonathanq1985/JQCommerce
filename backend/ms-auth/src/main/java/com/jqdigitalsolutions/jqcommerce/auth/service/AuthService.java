package com.jqdigitalsolutions.jqcommerce.auth.service;

import com.jqdigitalsolutions.jqcommerce.auth.dto.LoginRequest;
import com.jqdigitalsolutions.jqcommerce.auth.dto.LoginResponse;
import com.jqdigitalsolutions.jqcommerce.auth.entity.Usuario;
import com.jqdigitalsolutions.jqcommerce.auth.repository.UsuarioRepository;
import com.jqdigitalsolutions.jqcommerce.auth.security.JwtService;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    public AuthService(
            UsuarioRepository usuarioRepository,
            JwtService jwtService,
            PasswordEncoder passwordEncoder) {

        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse login(LoginRequest request) {

        Usuario usuario = usuarioRepository
                .findByUsername(request.username())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Usuario no encontrado"
                        )
                );

        if (!passwordEncoder.matches(
                request.password(),
                usuario.getPasswordHash())) {

            throw new RuntimeException(
                    "Contraseña incorrecta"
            );
        }

        String token =
                jwtService.generateToken(
                        usuario.getUsername()
                );

        return new LoginResponse(
                token,
                "Bearer"
        );
    }
}