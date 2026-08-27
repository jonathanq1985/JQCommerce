package com.jqdigitalsolutions.jqcommerce.auth.controller;

import com.jqdigitalsolutions.jqcommerce.auth.dto.RolPermisoResponse;
import com.jqdigitalsolutions.jqcommerce.auth.service.RolPermisoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Ing_JQC: Controlador para gestión de relaciones rol permiso
@RestController
@RequestMapping("/api/v1/rol-permisos")
public class RolPermisoController {

    private final RolPermisoService rolPermisoService;

    public RolPermisoController(RolPermisoService rolPermisoService) {

        this.rolPermisoService = rolPermisoService;
    }

    // Ing_JQC: Obtiene todas las relaciones rol permiso
    @GetMapping
    public List<RolPermisoResponse> listarRolPermisos() {

        return rolPermisoService.listarRolPermisos();
    }
}