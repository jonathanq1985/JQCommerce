package com.jqdigitalsolutions.jqcommerce.configuracion.domain.model;

import lombok.*;

// Ing_JQC: Modelo de dominio de empresa
// Tecnología: Arquitectura Hexagonal
// Finalidad: Representar una empresa del sistema

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Empresa {

    private Long idEmpresa;

    private String codigo;

    private String razonSocial;

    private String nombreComercial;

    private String ruc;

    private String direccion;

    private String telefono;

    private String correo;

    private String monedaPrincipal;

    private Boolean estado;

}
