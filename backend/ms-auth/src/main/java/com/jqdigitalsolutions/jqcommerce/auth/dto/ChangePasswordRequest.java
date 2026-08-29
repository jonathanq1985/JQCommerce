package com.jqdigitalsolutions.jqcommerce.auth.dto;

// Ing_JQC: Solicitud para cambio de contraseña
public record ChangePasswordRequest(

        String username,
        String currentPassword,
        String newPassword

) {
}