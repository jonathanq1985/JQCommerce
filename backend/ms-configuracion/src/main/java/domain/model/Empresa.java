package com.jqdigitalsolutions.jqcommerce.configuracion.domain.model;

// Ing_JQC: Representa una empresa registrada en la plataforma
// Tecnología: Arquitectura Hexagonal
// Finalidad: Mantener el dominio independiente de la infraestructura

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

    public Empresa() {
    }

    public Empresa(Long idEmpresa,
                   String ruc,
                   String razonSocial,
                   String nombreComercial,
                   String direccion,
                   String telefono,
                   String correo,
                   Boolean estado,
                   String codigo,
                   String monedaPrincipal) {

        this.idEmpresa = idEmpresa;
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.nombreComercial = nombreComercial;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.estado = estado;
        this.codigo=codigo;
        this.monedaPrincipal=monedaPrincipal;

    }

    // getters y setters
}