package com.jqdigitalsolutions.jqcommerce.auth.controller;

import com.jqdigitalsolutions.jqcommerce.auth.dto.AuditoriaSesionResponse;
import com.jqdigitalsolutions.jqcommerce.auth.service.AuditoriaSesionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Ing_JQC: Controlador para consulta de auditorías de sesión
@RestController
@RequestMapping("/api/v1/auditorias")
public class AuditoriaSesionController {
    
    private final AuditoriaSesionService auditoriaSesionService;

    public AuditoriaSesionController(AuditoriaSesionService auditoriaSesionService) {
        this.auditoriaSesionService = auditoriaSesionService;
    }

    // Ing_JQC: Obtiene todas las auditorías registradas
    @GetMapping
    public List<AuditoriaSesionResponse> listarAuditorias() {
        return auditoriaSesionService.listarAuditorias();

    }
}