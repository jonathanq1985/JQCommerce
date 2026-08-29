package com.jqdigitalsolutions.jqcommerce.auth.service;

import com.jqdigitalsolutions.jqcommerce.auth.dto.AuditoriaSesionResponse;
import com.jqdigitalsolutions.jqcommerce.auth.repository.AuditoriaSesionRepository;
import org.springframework.stereotype.Service;
import com.jqdigitalsolutions.jqcommerce.auth.entity.AuditoriaSesion;
import java.time.LocalDateTime;

import java.util.List;

// Ing_JQC: Servicio de auditoría de sesiones
@Service
public class AuditoriaSesionService {

    private final AuditoriaSesionRepository auditoriaSesionRepository;

    public AuditoriaSesionService(AuditoriaSesionRepository auditoriaSesionRepository) {

        this.auditoriaSesionRepository =  auditoriaSesionRepository;
    }

    // Ing_JQC: Obtiene todas las auditorías registradas
    public List<AuditoriaSesionResponse> listarAuditorias() {

        return auditoriaSesionRepository.findAll()
                .stream()
                .map(auditoria ->

                        new AuditoriaSesionResponse(
                                auditoria.getIdAuditoria(),
                                auditoria.getUsuarioId(),
                                auditoria.getFechaLogin(),
                                auditoria.getFechaLogout(),
                                auditoria.getDireccionIp(),
                                auditoria.getUserAgent(),
                                auditoria.getEstado()
                        )

                )
                .toList();
    }
    // Ing_JQC: Registra auditoría de inicio de sesión
    public void guardarAuditoriaLogin(
            Long usuarioId,
            String direccionIp,
            String userAgent) {

        AuditoriaSesion auditoria =
                AuditoriaSesion.builder()
                        .usuarioId(usuarioId)
                        .fechaLogin(LocalDateTime.now())
                        .direccionIp(direccionIp)
                        .userAgent(userAgent)
                        .estado("ACTIVA")
                        .build();

        auditoriaSesionRepository.save(
                auditoria
        );
    }
}
