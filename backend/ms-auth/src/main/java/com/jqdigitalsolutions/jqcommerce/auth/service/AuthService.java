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
import com.jqdigitalsolutions.jqcommerce.auth.dto.ChangePasswordRequest;
import com.jqdigitalsolutions.jqcommerce.auth.dto.ForgotPasswordRequest;
import com.jqdigitalsolutions.jqcommerce.auth.dto.ForgotPasswordResponse;
import com.jqdigitalsolutions.jqcommerce.auth.dto.ResetPasswordRequest;
import com.jqdigitalsolutions.jqcommerce.auth.dto.UnlockUserRequest;
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
    public LoginResponse login( LoginRequest request, String direccionIp, String userAgent) {
        Usuario usuario = usuarioRepository
                .findByUsername(request.username())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Usuario no encontrado"
                        )
                );

        // Ing_JQC: Valida si el usuario está bloqueado
        if(Boolean.TRUE.equals(usuario.getBloqueado())) {
             throw new RuntimeException("Usuario bloqueado");
        }
        if(!passwordEncoder.matches(request.password(), usuario.getPasswordHash())) {
            usuario.setIntentosFallidos(usuario.getIntentosFallidos() + 1);
            if(usuario.getIntentosFallidos() >= 3) {
                usuario.setBloqueado(true);
            }
            usuarioRepository.save(usuario);
            throw new RuntimeException("Credenciales inválidas");

        }
        // Ing_JQC: Reinicia intentos fallidos y desbloqueo
        usuario.setIntentosFallidos(0);
        usuario.setBloqueado(false);
        usuarioRepository.save(usuario);

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
    // Ing_JQC: Cambia la contraseña del usuario
    public void changePassword(ChangePasswordRequest request) {

        Usuario usuario =
                usuarioRepository
                        .findByUsername(
                                request.username()
                        )
                        .orElseThrow();

        boolean passwordValido =
                passwordEncoder.matches(
                        request.currentPassword(),
                        usuario.getPasswordHash()
                );

        if (!passwordValido) {
            throw new RuntimeException("Contraseña actual incorrecta");
        }

        usuario.setPasswordHash(
                passwordEncoder.encode(
                        request.newPassword()
                ));

        usuarioRepository.save(usuario);
    }
    // Ing_JQC: Genera token de recuperación de contraseña
    public ForgotPasswordResponse forgotPassword(
            ForgotPasswordRequest request) {

        Usuario usuario =
                usuarioRepository
                        .findByUsername(
                                request.username()
                        )
                        .orElseThrow();

        String resetToken =
                jwtService
                        .generatePasswordResetToken(
                                usuario.getUsername()
                        );

        return new ForgotPasswordResponse(resetToken);
    }
    // Ing_JQC: Restablece contraseña mediante token
    public void resetPassword(ResetPasswordRequest request) {

        String username =
                jwtService.extractUsername(request.resetToken());

        Usuario usuario =
                usuarioRepository.findByUsername(username)
                        .orElseThrow();

        usuario.setPasswordHash(
                passwordEncoder.encode(request.newPassword())
        );

        usuarioRepository.save(usuario);

    }
    // Ing_JQC: Desbloquea un usuario bloqueado
    public void unlockUser(UnlockUserRequest request) {

        Usuario usuario =
                usuarioRepository.findByUsername(request.username())
                        .orElseThrow(() ->
                                new RuntimeException("Usuario no encontrado"));

        usuario.setIntentosFallidos(0);
        usuario.setBloqueado(false);
        usuarioRepository.save(usuario);

    }
}