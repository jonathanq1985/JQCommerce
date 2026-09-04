package com.jqdigitalsolutions.jqcommerce.configuracion.application.service;

import com.jqdigitalsolutions.jqcommerce.configuracion.domain.port.SucursalRepositoryPort;
import org.springframework.stereotype.Service;

// Ing_JQC: Activar sucursal
// Tecnología: Arquitectura Hexagonal
// Finalidad: Reactivar sucursal deshabilitada

@Service
public class ActivarSucursalUseCase {

    private final SucursalRepositoryPort sucursalRepositoryPort;

    public ActivarSucursalUseCase(
            SucursalRepositoryPort sucursalRepositoryPort) {

        this.sucursalRepositoryPort = sucursalRepositoryPort;

    }

    public void ejecutar(Long idSucursal) {

        sucursalRepositoryPort.activar(idSucursal);

    }

}