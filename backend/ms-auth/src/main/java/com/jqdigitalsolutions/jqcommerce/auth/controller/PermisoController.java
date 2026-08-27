package com.jqdigitalsolutions.jqcommerce.auth.controller;

import com.jqdigitalsolutions.jqcommerce.auth.dto.PermisoResponse;
import com.jqdigitalsolutions.jqcommerce.auth.service.PermisoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Ing_JQC: Controlador para gestión de permisos
@RestController
@RequestMapping("/api/v1/permisos")
public class PermisoController {

    private final PermisoService permisoService;

    public PermisoController(
            PermisoService permisoService) {

        this.permisoService = permisoService;
    }

    // Ing_JQC: Obtiene todos los permisos del sistema
    @GetMapping
    public List<PermisoResponse> listarPermisos() {

        return permisoService.listarPermisos();
    }
}