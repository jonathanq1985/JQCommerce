package com.jqdigitalsolutions.jqcommerce.auth.dto;

import java.time.LocalDateTime;

// Ing_JQC: DTO para exponer auditoría de sesiones
public record AuditoriaSesionResponse(

        Long idAuditoria,
        Long usuarioId,
        LocalDateTime fechaLogin,
        LocalDateTime fechaLogout,
        String direccionIp,
        String userAgent,
        String estado

) {
}