package com.jqdigitalsolutions.jqcommerce.auth.dto;

// Ing_JQC: DTO para exponer permisos
public record PermisoResponse(

        Long idPermiso,
        String codigo,
        String nombre,
        String descripcion,
        Boolean estado

) {
}