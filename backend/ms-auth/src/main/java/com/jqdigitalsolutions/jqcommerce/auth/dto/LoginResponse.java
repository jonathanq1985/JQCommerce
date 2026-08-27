package com.jqdigitalsolutions.jqcommerce.auth.dto;

// Ing_JQC: Respuesta de autenticación
public record LoginResponse(

        String accessToken,
        String refreshToken,
        String tokenType

) {
}