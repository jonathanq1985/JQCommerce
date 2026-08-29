package com.jqdigitalsolutions.jqcommerce.auth.dto;

// Ing_JQC: Solicitud de restablecimiento de contraseña
public record ResetPasswordRequest(
        String resetToken,
        String newPassword
) {
}