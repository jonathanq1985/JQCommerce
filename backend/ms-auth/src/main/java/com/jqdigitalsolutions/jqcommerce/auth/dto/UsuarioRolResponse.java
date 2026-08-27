package com.jqdigitalsolutions.jqcommerce.auth.dto;

// Ing_JQC: DTO para exponer la relación usuario rol
public record UsuarioRolResponse(

        Long idUsuarioRol,
        Long usuarioId,
        Long rolId,
        String estado

) {
}