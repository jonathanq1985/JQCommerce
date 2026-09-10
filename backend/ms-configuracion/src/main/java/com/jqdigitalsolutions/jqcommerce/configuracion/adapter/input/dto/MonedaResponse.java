package com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.dto;

// Ing_JQC: Response de moneda
// Tecnología: Arquitectura Hexagonal
// Finalidad: Exponer información de monedas

public record MonedaResponse(

        Long idMoneda,
        String codigo,
        String nombre,
        String simbolo,
        Boolean estado

) {
}