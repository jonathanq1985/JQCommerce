package com.jqdigitalsolutions.jqcommerce.productos.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa una unidad de medida
 * utilizada por los productos.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnidadMedida {

    private Long idUnidad;

    private String codigo;

    private String nombre;

    private String abreviatura;

    private String descripcion;

    private Boolean estado;

}