package com.jqdigitalsolutions.jqcommerce.productos.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "unidad_medida", schema = "productos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnidadMedidaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_unidad")
    private Long idUnidad;

    private String codigo;

    private String nombre;

    private String abreviatura;

    private String descripcion;

    private Boolean estado;

    @Column(
            name = "fecha_creacion",
            nullable = false,
            updatable = false
    )
    private LocalDateTime fechaCreacion;

    @PrePersist
    public void prePersist() {

        this.fechaCreacion = LocalDateTime.now();

    }

}