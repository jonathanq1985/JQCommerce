package com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.dto;

public record SucursalResponse(
        Long idSucursal,
        Long empresaId,
        String codigo,
        String nombre,
        String direccion,
        String telefono,
        String correo,
        Boolean estado

) {
}