package com.jqdigitalsolutions.jqcommerce.auth.controller;

import com.jqdigitalsolutions.jqcommerce.auth.dto.RolResponse;
import com.jqdigitalsolutions.jqcommerce.auth.service.RolService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Ing_JQC: Controlador para gestión de roles
@RestController
@RequestMapping("/api/v1/roles")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    // Ing_JQC: Obtiene todos los roles del sistema
    @GetMapping
    public List<RolResponse> listarRoles() {

        return rolService.listarRoles();
    }
}