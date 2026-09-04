package com.jqdigitalsolutions.jqcommerce.configuracion.application.service;

import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.Empresa;
import com.jqdigitalsolutions.jqcommerce.configuracion.domain.port.EmpresaRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

// Ing_JQC: Lista empresas registradas
// Tecnología: Arquitectura Hexagonal
// Finalidad: Consultar empresas de la plataforma SaaS

@Service
public class ListarEmpresasUseCase {

    private final EmpresaRepositoryPort empresaRepositoryPort;

    public ListarEmpresasUseCase(EmpresaRepositoryPort empresaRepositoryPort) {
        this.empresaRepositoryPort = empresaRepositoryPort;
    }

    public List<Empresa> ejecutar() {
        return empresaRepositoryPort.listar();
    }
}