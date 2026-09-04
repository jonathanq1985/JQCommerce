package com.jqdigitalsolutions.jqcommerce.configuracion.application.service;

import com.jqdigitalsolutions.jqcommerce.configuracion.domain.port.EmpresaRepositoryPort;
import org.springframework.stereotype.Service;

// Ing_JQC: Activar empresa
// Tecnología: Arquitectura Hexagonal
// Finalidad: Reactivar empresa deshabilitada

@Service
public class ActivarEmpresaUseCase {

    private final EmpresaRepositoryPort empresaRepositoryPort;

    public ActivarEmpresaUseCase(EmpresaRepositoryPort empresaRepositoryPort) {
        this.empresaRepositoryPort = empresaRepositoryPort;
    }

    public void ejecutar(Long idEmpresa) {
        empresaRepositoryPort.activar(idEmpresa);
    }

}