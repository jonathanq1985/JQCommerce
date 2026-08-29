package com.jqdigitalsolutions.jqcommerce.auth.controller;

import com.jqdigitalsolutions.jqcommerce.auth.dto.LoginRequest;
import com.jqdigitalsolutions.jqcommerce.auth.dto.LoginResponse;
import com.jqdigitalsolutions.jqcommerce.auth.dto.UsuarioActualResponse;
import com.jqdigitalsolutions.jqcommerce.auth.security.JwtService;
import com.jqdigitalsolutions.jqcommerce.auth.service.AuthService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import com.jqdigitalsolutions.jqcommerce.auth.dto.RefreshTokenRequest;
import com.jqdigitalsolutions.jqcommerce.auth.dto.RefreshTokenResponse;
import jakarta.servlet.http.HttpServletRequest;
import com.jqdigitalsolutions.jqcommerce.auth.dto.LogoutRequest;
import org.springframework.http.ResponseEntity;
import com.jqdigitalsolutions.jqcommerce.auth.dto.ChangePasswordRequest;
import com.jqdigitalsolutions.jqcommerce.auth.dto.ForgotPasswordRequest;
import com.jqdigitalsolutions.jqcommerce.auth.dto.ForgotPasswordResponse;
import com.jqdigitalsolutions.jqcommerce.auth.dto.ResetPasswordRequest;
import com.jqdigitalsolutions.jqcommerce.auth.dto.UnlockUserRequest;
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    // Ing_JQC: Endpoint para obtener el usuario autenticado desde el JWT
    @GetMapping("/me")
    public UsuarioActualResponse me(Authentication authentication) {
        return new UsuarioActualResponse(
                authentication.getName()
        );
    }
    public AuthController(AuthService authService, JwtService jwtService) {

        this.authService = authService;
        this.jwtService = jwtService;
    }

    // Ing_JQC: Inicio de sesión y generación de JWT
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request, HttpServletRequest httpRequest) {
        return authService.login(
                request,
                httpRequest.getRemoteAddr(),
                httpRequest.getHeader("User-Agent")
        );
    }

    @GetMapping("/token-info")
    public String tokenInfo(@RequestParam String token) {

        return jwtService.extractUsername(token);
    }

    // Ing_JQC: Renueva access token usando refresh token
    @PostMapping("/refresh-token")
    public RefreshTokenResponse refreshToken(@RequestBody RefreshTokenRequest request) {
        return authService.refreshToken(request);
    }
    // Ing_JQC: Cierre de sesión
    @PostMapping("/logout")
    public ResponseEntity<Void> logout( @RequestBody LogoutRequest request) {
        authService.logout(request);
        return ResponseEntity.ok().build();
    }
    // Ing_JQC: Cambio de contraseña del usuario
    @PostMapping("/change-password")
    public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordRequest request) {
        authService.changePassword(request);
        return ResponseEntity.ok().build();
    }
    // Ing_JQC: Genera token para recuperación de contraseña
    @PostMapping("/forgot-password")
    public ForgotPasswordResponse forgotPassword(@RequestBody ForgotPasswordRequest request) {

        return authService.forgotPassword(request);
    }
    // Ing_JQC: Restablece contraseña usando token de recuperación
    @PostMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@RequestBody ResetPasswordRequest request) {

        authService.resetPassword(request);
        return ResponseEntity.ok().build();
    }
    // Ing_JQC: Solo administradores pueden desbloquear usuarios
   // @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/unlock-user")
    public ResponseEntity<Void> unlockUser(@RequestBody UnlockUserRequest request) {
        authService.unlockUser(request);
        return ResponseEntity.ok().build();

    }
}