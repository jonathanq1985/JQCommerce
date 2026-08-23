package com.jqdigitalsolutions.jqcommerce.auth.dto;

public record UsuarioResponse(

        Long idUsuario,
        Long empresaId,
        String username,
        String nombres,
        String apellidos,
        String correo,
        Boolean estado

) {
}