package com.jqdigitalsolutions.jqcommerce.auth.dto;

public record LoginResponse(
        String accessToken,
        String tokenType
) {
}