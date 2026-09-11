package com.jqdigitalsolutions.jqcommerce.productos.adapter.input.dto;

public record UnidadMedidaResponse(

        Long idUnidad,

        String codigo,

        String nombre,

        String abreviatura,

        String descripcion,

        Boolean estado

) {
}