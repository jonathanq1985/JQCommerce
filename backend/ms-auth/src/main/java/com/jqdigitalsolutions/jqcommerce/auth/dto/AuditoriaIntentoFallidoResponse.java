package com.jqdigitalsolutions.jqcommerce.auth.dto;

import java.time.LocalDateTime;

// Ing_JQC: DTO de auditoría de intentos fallidos
public record AuditoriaIntentoFallidoResponse(
        Long idIntento,
        String username,
        String direccionIp,
        String userAgent,
        LocalDateTime fechaIntento,
        String observacion
) {
}
