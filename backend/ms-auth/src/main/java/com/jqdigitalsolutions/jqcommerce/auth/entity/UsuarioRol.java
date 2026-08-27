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

import java.time.LocalDateTime;

// Ing_JQC: Entidad que relaciona usuarios con roles
@Entity
@Table(name = "usuario_rol", schema = "seguridad")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuarioRol;

    private Long usuarioId;

    private Long rolId;

    private String estado;

    private LocalDateTime fechaCreacion;
}