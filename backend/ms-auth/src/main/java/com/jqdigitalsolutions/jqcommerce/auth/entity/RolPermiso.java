package com.jqdigitalsolutions.jqcommerce.auth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

// Ing_JQC: Entidad que relaciona roles con permisos
@Entity
@Table(name = "rol_permiso", schema = "seguridad")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolPermiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRolPermiso;

    private Long rolId;

    private Long permisoId;

    private String estado;

    private OffsetDateTime fechaCreacion;

}