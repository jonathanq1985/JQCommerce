package com.jqdigitalsolutions.jqcommerce.configuracion.application.service;

import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.Sucursal;
import com.jqdigitalsolutions.jqcommerce.configuracion.domain.port.SucursalRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Ing_JQC: Buscar sucursal por identificador
// Tecnología: Arquitectura Hexagonal
// Finalidad: Consultar una sucursal específica

@Service
public class BuscarSucursalPorIdUseCase {

    private final SucursalRepositoryPort sucursalRepositoryPort;

    public BuscarSucursalPorIdUseCase(
            SucursalRepositoryPort sucursalRepositoryPort) {

        this.sucursalRepositoryPort = sucursalRepositoryPort;

    }

    public Optional<Sucursal> ejecutar(Long idSucursal) {
        return sucursalRepositoryPort
                .buscarPorId(idSucursal);
    }

}