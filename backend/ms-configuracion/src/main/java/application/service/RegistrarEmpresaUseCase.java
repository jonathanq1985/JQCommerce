package com.jqdigitalsolutions.jqcommerce.configuracion.application.service;

import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.Empresa;
import com.jqdigitalsolutions.jqcommerce.configuracion.domain.port.EmpresaRepositoryPort;
import org.springframework.stereotype.Service;

// Ing_JQC: Registro de empresas
// Tecnología: Arquitectura Hexagonal
// Finalidad: Ejecutar caso de uso de negocio

@Service
public class RegistrarEmpresaUseCase {

    private final EmpresaRepositoryPort empresaRepositoryPort;

    public RegistrarEmpresaUseCase(EmpresaRepositoryPort empresaRepositoryPort) {
        this.empresaRepositoryPort = empresaRepositoryPort;
    }

    public Empresa ejecutar(Empresa empresa) {
        empresa.setEstado(true);
        return empresaRepositoryPort.guardar(empresa);
    }

}