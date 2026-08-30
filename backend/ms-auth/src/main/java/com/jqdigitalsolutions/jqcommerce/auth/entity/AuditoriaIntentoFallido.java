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

// Ing_JQC: Entidad de auditoría de intentos fallidos
@Entity
@Table(name = "auditoria_intento_fallido", schema = "seguridad")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditoriaIntentoFallido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIntento;

    private String username;

    private String direccionIp;

    private String userAgent;

    private LocalDateTime fechaIntento;

    private String observacion;

}