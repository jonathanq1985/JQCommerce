package com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.dto;

public record ParametroSistemaResponse(

        Long idParametro,

        Long empresaId,

        String codigo,

        String nombre,

        String valor,

        String descripcion

) {
}