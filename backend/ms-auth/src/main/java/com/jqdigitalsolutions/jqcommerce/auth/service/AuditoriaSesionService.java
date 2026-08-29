package com.jqdigitalsolutions.jqcommerce.auth.service;

import com.jqdigitalsolutions.jqcommerce.auth.dto.AuditoriaSesionResponse;
import com.jqdigitalsolutions.jqcommerce.auth.repository.AuditoriaSesionRepository;
import org.springframework.stereotype.Service;

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
}
