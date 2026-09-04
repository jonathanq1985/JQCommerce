package com.jqdigitalsolutions.jqcommerce.configuracion.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

// Ing_JQC: Entidad JPA de sucursal
// Tecnología: JPA
// Finalidad: Persistencia de sucursales

@Entity
@Table(name = "sucursal", schema = "configuracion")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SucursalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sucursal")
    private Long idSucursal;

    @Column(name = "empresa_id")
    private Long empresaId;

    private String codigo;

    private String nombre;

    private String direccion;

    private String telefono;

    private String correo;

    private Boolean estado;

    private LocalDateTime fechaCreacion;

}