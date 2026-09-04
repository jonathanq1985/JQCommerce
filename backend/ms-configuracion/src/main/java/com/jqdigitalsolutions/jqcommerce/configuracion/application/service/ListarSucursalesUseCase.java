package com.jqdigitalsolutions.jqcommerce.configuracion.application.service;

import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.Sucursal;
import com.jqdigitalsolutions.jqcommerce.configuracion.domain.port.SucursalRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

// Ing_JQC: Listar sucursales
// Tecnología: Arquitectura Hexagonal
// Finalidad: Consultar sucursales registradas

@Service
public class ListarSucursalesUseCase {

    private final SucursalRepositoryPort sucursalRepositoryPort;

    public ListarSucursalesUseCase( SucursalRepositoryPort sucursalRepositoryPort) {

        this.sucursalRepositoryPort = sucursalRepositoryPort;
    }

    public List<Sucursal> ejecutar() {
        return sucursalRepositoryPort.listar();
    }

}