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
import com.jqdigitalsolutions.jqcommerce.auth.dto.LogoutRequest;
@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuditoriaSesionService auditoriaSesionService;
    public AuthService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuditoriaSesionService auditoriaSesionService) {

        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.auditoriaSesionService = auditoriaSesionService;
    }

    // Ing_JQC: Autentica usuario y registra auditoría
    public LoginResponse login(
            LoginRequest request,
            String direccionIp,
            String userAgent) {

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

        // Ing_JQC: Registrar auditoría de login
        auditoriaSesionService.guardarAuditoriaLogin(usuario.getIdUsuario(), direccionIp,userAgent);
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
    // Ing_JQC: Cierra la sesión del usuario
    public void logout(
            LogoutRequest request) {

        Usuario usuario =usuarioRepository
                        .findByUsername(
                        request.username()
                        ).orElseThrow();

        auditoriaSesionService.cerrarSesion( usuario.getIdUsuario());
    }

}