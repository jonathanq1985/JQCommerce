package com.jqdigitalsolutions.jqcommerce.configuracion.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Ing_JQC: Modelo de dominio de parametro sistema
// Tecnología: Arquitectura Hexagonal
// Finalidad: Administrar configuraciones del sistema

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParametroSistema {

    private Long idParametro;

    private Long empresaId;

    private String codigo;

    private String nombre;

    private String valor;

    private String descripcion;

}