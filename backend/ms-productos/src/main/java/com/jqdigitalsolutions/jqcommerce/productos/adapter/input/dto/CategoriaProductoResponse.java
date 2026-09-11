package com.jqdigitalsolutions.jqcommerce.productos.adapter.input.dto;

public record CategoriaProductoResponse(

        Long idCategoria,

        String codigo,

        String nombre,

        String descripcion,

        Boolean estado

) {
}