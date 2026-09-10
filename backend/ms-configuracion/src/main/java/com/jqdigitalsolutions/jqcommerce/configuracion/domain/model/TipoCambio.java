package com.jqdigitalsolutions.jqcommerce.configuracion.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

// Ing_JQC: Modelo de dominio de tipo de cambio
// Tecnología: Arquitectura Hexagonal
// Finalidad: Administrar conversiones monetarias

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoCambio {

    private Long idTipoCambio;

    private Long monedaOrigenId;

    private Long monedaDestinoId;

    private BigDecimal valor;

    private LocalDate fechaVigencia;

}