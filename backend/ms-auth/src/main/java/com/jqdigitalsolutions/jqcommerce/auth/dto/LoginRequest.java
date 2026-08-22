package com.jqdigitalsolutions.jqcommerce.auth.dto;

public record LoginRequest(
        String username,
        String password
) {
}