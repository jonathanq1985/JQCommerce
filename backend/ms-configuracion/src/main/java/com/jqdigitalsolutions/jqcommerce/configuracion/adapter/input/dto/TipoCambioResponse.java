package com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

// Ing_JQC: Response de tipo de cambio
// Tecnología: Arquitectura Hexagonal
// Finalidad: Exponer información de tipos de cambio

public record TipoCambioResponse(

        Long idTipoCambio,
        Long monedaOrigenId,
        Long monedaDestinoId,
        BigDecimal valor,
        LocalDate fechaVigencia

) {
}