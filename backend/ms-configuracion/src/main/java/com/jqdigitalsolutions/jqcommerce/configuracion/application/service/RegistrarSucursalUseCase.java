package com.jqdigitalsolutions.jqcommerce.configuracion.application.service;

import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.Sucursal;
import com.jqdigitalsolutions.jqcommerce.configuracion.domain.port.SucursalRepositoryPort;
import org.springframework.stereotype.Service;

// Ing_JQC: Registrar sucursal
// Tecnología: Arquitectura Hexagonal
// Finalidad: Crear sucursales asociadas a una empresa

@Service
public class RegistrarSucursalUseCase {

    private final SucursalRepositoryPort sucursalRepositoryPort;

    public RegistrarSucursalUseCase(SucursalRepositoryPort sucursalRepositoryPort) {
        this.sucursalRepositoryPort = sucursalRepositoryPort;
    }

    public Sucursal ejecutar(Sucursal sucursal) {
        sucursal.setEstado(true);
        return sucursalRepositoryPort.guardar(sucursal);

    }

}