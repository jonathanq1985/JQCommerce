package com.jqdigitalsolutions.jqcommerce.configuracion.application.service;

import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.Empresa;
import com.jqdigitalsolutions.jqcommerce.configuracion.domain.port.EmpresaRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Ing_JQC: Busca empresa por identificador
// Tecnología: Arquitectura Hexagonal
// Finalidad: Consultar una empresa específica

@Service
public class BuscarEmpresaPorIdUseCase {

    private final EmpresaRepositoryPort empresaRepositoryPort;

    public BuscarEmpresaPorIdUseCase(
            EmpresaRepositoryPort empresaRepositoryPort) {

        this.empresaRepositoryPort = empresaRepositoryPort;
    }

    public Optional<Empresa> ejecutar(Long idEmpresa) {

        return empresaRepositoryPort.buscarPorId(idEmpresa);

    }

}