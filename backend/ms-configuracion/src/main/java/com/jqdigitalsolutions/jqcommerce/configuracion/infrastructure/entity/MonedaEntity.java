package com.jqdigitalsolutions.jqcommerce.configuracion.infrastructure.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// Ing_JQC: Entidad JPA de moneda
// Tecnología: Spring Data JPA
// Finalidad: Persistir monedas en PostgreSQL

@Entity
@Table(name = "moneda", schema = "configuracion")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonedaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_moneda")
    private Long idMoneda;

    private String codigo;

    private String nombre;

    private String simbolo;

    private Boolean estado;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

}