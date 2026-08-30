package com.jqdigitalsolutions.jqcommerce.auth.controller;

import com.jqdigitalsolutions.jqcommerce.auth.dto.AuditoriaIntentoFallidoResponse;
import com.jqdigitalsolutions.jqcommerce.auth.service.AuditoriaIntentoFallidoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Ing_JQC: Controlador de auditoría de intentos fallidos
@RestController
@RequestMapping("/api/v1/intentos-fallidos")
public class AuditoriaIntentoFallidoController {

    private final AuditoriaIntentoFallidoService auditoriaIntentoFallidoService;

    public AuditoriaIntentoFallidoController(AuditoriaIntentoFallidoService auditoriaIntentoFallidoService) {
        this.auditoriaIntentoFallidoService = auditoriaIntentoFallidoService;
    }
    // Ing_JQC: Obtiene todos los intentos fallidos registrados
    @GetMapping
    public List<AuditoriaIntentoFallidoResponse> listarIntentosFallidos() {
        return auditoriaIntentoFallidoService.listarIntentosFallidos();
    }

}