package com.jqdigitalsolutions.jqcommerce.configuracion.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Ing_JQC: Modelo de dominio de sucursal
// Tecnología: Arquitectura Hexagonal
// Finalidad: Representar las sucursales de una empresa

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sucursal {

    private Long idSucursal;

    private Long empresaId;

    private String codigo;

    private String nombre;

    private String direccion;

    private String telefono;

    private String correo;

    private Boolean estado;

}
