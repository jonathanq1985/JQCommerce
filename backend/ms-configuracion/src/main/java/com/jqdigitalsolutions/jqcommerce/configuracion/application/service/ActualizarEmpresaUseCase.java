package com.jqdigitalsolutions.jqcommerce.configuracion.application.service;

import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.Empresa;
import com.jqdigitalsolutions.jqcommerce.configuracion.domain.port.EmpresaRepositoryPort;
import org.springframework.stereotype.Service;

// Ing_JQC: Actualiza una empresa
// Tecnología: Arquitectura Hexagonal
// Finalidad: Modificar datos empresariales

@Service
public class ActualizarEmpresaUseCase {

    private final EmpresaRepositoryPort empresaRepositoryPort;

    public ActualizarEmpresaUseCase(
            EmpresaRepositoryPort empresaRepositoryPort) {

        this.empresaRepositoryPort = empresaRepositoryPort;

    }

    public Empresa ejecutar(Empresa empresa) {

        return empresaRepositoryPort.actualizar(
                empresa
        );

    }

}