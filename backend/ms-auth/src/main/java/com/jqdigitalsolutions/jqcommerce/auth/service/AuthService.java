package com.jqdigitalsolutions.jqcommerce.auth.service;

import com.jqdigitalsolutions.jqcommerce.auth.dto.LoginRequest;
import com.jqdigitalsolutions.jqcommerce.auth.dto.LoginResponse;
import com.jqdigitalsolutions.jqcommerce.auth.dto.RefreshTokenRequest;
import com.jqdigitalsolutions.jqcommerce.auth.dto.RefreshTokenResponse;
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

        String accessToken =   jwtService.generateToken(usuario.getUsername());
        String refreshToken = jwtService.generateRefreshToken(usuario.getUsername());

        // Ing_JQC: Retorna access token y refresh token
        return new LoginResponse(
                accessToken,
                refreshToken,
                "Bearer"
        );
    }
    // Ing_JQC: Genera un nuevo access token usando refresh token
    public RefreshTokenResponse refreshToken(
            RefreshTokenRequest request) {

        String username =
                jwtService.extractUsername(
                        request.refreshToken()
                );

        String newAccessToken =
                jwtService.generateToken(
                        username
                );

        return new RefreshTokenResponse(
                newAccessToken
        );
    }

}