package com.jqdigitalsolutions.jqcommerce.productos.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entidad JPA responsable de la persistencia
 * de categorías de producto.
 */
@Entity
@Table(name = "categoria_producto", schema = "productos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long idCategoria;

    private String codigo;

    private String nombre;

    private String descripcion;

    private Boolean estado;

    @Column(name = "fecha_creacion",
            nullable = false,
            updatable = false)
    private LocalDateTime fechaCreacion;

    @PrePersist
    public void prePersist() {

        this.fechaCreacion = LocalDateTime.now();

    }
}