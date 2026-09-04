package com.jqdigitalsolutions.jqcommerce.configuracion.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Ing_JQC: Modelo de dominio de moneda
// Tecnología: Arquitectura Hexagonal
// Finalidad: Representar las monedas del sistema

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Moneda {

    private Long idMoneda;

    private String codigo;

    private String nombre;

    private String simbolo;

    private Boolean estado;

}