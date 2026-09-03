package com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.dto;
// Ing_JQC: Respuesta de empresa
// Tecnología: Arquitectura Hexagonal
// Finalidad: Exponer información de empresa

public record EmpresaResponse(

        Long idEmpresa,
        String codigo,
        String razonSocial,
        String nombreComercial,
        String ruc,
        String direccion,
        String telefono,
        String correo,
        String monedaPrincipal,
        Boolean estado

) {
}