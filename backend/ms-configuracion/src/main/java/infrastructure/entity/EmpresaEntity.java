package com.jqdigitalsolutions.jqcommerce.configuracion.infrastructure.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// Ing_JQC: Entidad JPA de empresa
// Tecnología: Spring Data JPA
// Finalidad: Persistir empresas en PostgreSQL

@Entity
@Table(name = "empresa", schema = "configuracion")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;

}