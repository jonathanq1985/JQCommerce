package com.jqdigitalsolutions.jqcommerce.configuracion.application.service;

import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.Sucursal;
import com.jqdigitalsolutions.jqcommerce.configuracion.domain.port.SucursalRepositoryPort;
import org.springframework.stereotype.Service;

// Ing_JQC: Actualizar sucursal
// Tecnología: Arquitectura Hexagonal
// Finalidad: Modificar datos de sucursal

@Service
public class ActualizarSucursalUseCase {

    private final SucursalRepositoryPort sucursalRepositoryPort;

    public ActualizarSucursalUseCase(SucursalRepositoryPort sucursalRepositoryPort) {

        this.sucursalRepositoryPort = sucursalRepositoryPort;

    }

    public Sucursal ejecutar(Sucursal sucursal) {
        return sucursalRepositoryPort.actualizar(sucursal);
    }

}