package com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.dto;

// Ing_JQC: Request de moneda
// Tecnología: Arquitectura Hexagonal
// Finalidad: Recibir información para registrar moneda

public record MonedaRequest(

        String codigo,
        String nombre,
        String simbolo

) {
}