package com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.dto;

public record ParametroSistemaRequest(

        Long empresaId,

        String codigo,

        String nombre,

        String valor,

        String descripcion

) {
}