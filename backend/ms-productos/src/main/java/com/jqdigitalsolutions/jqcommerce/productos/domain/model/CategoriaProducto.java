package com.jqdigitalsolutions.jqcommerce.productos.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa una categoría de producto utilizada
 * para clasificar los productos del catálogo.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaProducto {

    private Long idCategoria;

    private String codigo;

    private String nombre;

    private String descripcion;

    private Boolean estado;

}