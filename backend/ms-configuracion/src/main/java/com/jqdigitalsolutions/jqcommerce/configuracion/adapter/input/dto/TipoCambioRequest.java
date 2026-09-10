package com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

// Ing_JQC: Request de tipo de cambio
// Tecnología: Arquitectura Hexagonal
// Finalidad: Recibir datos para registrar tipos de cambio

public record TipoCambioRequest(

        Long monedaOrigenId,
        Long monedaDestinoId,
        BigDecimal valor,
        LocalDate fechaVigencia

) {
}