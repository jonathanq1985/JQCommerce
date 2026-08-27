package com.jqdigitalsolutions.jqcommerce.auth.dto;

// Ing_JQC: DTO para exponer la relación rol permiso
public record RolPermisoResponse(

        Long idRolPermiso,
        Long rolId,
        Long permisoId,
        String estado

) {
}