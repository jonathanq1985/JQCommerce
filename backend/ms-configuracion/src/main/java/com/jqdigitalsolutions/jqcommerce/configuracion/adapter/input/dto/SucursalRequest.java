package com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.dto;

public record SucursalRequest(
        Long empresaId,
        String codigo,
        String nombre,
        String direccion,
        String telefono,
        String correo

) {
}