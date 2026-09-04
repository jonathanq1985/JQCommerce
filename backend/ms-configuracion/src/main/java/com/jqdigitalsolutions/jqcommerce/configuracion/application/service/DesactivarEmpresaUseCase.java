package com.jqdigitalsolutions.jqcommerce.configuracion.application.service;

import com.jqdigitalsolutions.jqcommerce.configuracion.domain.port.EmpresaRepositoryPort;
import org.springframework.stereotype.Service;

// Ing_JQC: Desactivar empresa
// Tecnología: Arquitectura Hexagonal
// Finalidad: Realizar borrado lógico

@Service
public class DesactivarEmpresaUseCase {

    private final EmpresaRepositoryPort empresaRepositoryPort;

    public DesactivarEmpresaUseCase( EmpresaRepositoryPort empresaRepositoryPort) {

        this.empresaRepositoryPort = empresaRepositoryPort;

    }

    public void ejecutar(Long idEmpresa) {
        empresaRepositoryPort.desactivar(idEmpresa);
    }

}