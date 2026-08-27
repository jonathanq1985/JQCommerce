package com.jqdigitalsolutions.jqcommerce.auth.dto;

// Ing_JQC: DTO para exponer información de roles
public record RolResponse(

        Long idRol,
        Long empresaId,
        String codigo,
        String nombre,
        String descripcion,
        Boolean estado

) {
}