package com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.dto;

// Ing_JQC: Solicitud para registrar empresa
// Tecnología: Arquitectura Hexagonal
// Finalidad: Recibir datos desde la API REST

public record EmpresaRequest(

        String codigo,
        String razonSocial,
        String nombreComercial,
        String ruc,
        String direccion,
        String telefono,
        String correo,
        String monedaPrincipal

) {
}