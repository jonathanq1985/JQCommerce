package com.jqdigitalsolutions.jqcommerce.auth.dto;

// Ing_JQC: Solicitud para renovar access token
public record RefreshTokenRequest(
        String refreshToken
) {
}
