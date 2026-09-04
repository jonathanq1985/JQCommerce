package com.jqdigitalsolutions.jqcommerce.configuracion.application.service;

import com.jqdigitalsolutions.jqcommerce.configuracion.domain.port.SucursalRepositoryPort;
import org.springframework.stereotype.Service;

// Ing_JQC: Desactivar sucursal
// Tecnología: Arquitectura Hexagonal
// Finalidad: Realizar deshabilitación lógica

@Service
public class DesactivarSucursalUseCase {

    private final SucursalRepositoryPort sucursalRepositoryPort;

    public DesactivarSucursalUseCase(SucursalRepositoryPort sucursalRepositoryPort) {
        this.sucursalRepositoryPort = sucursalRepositoryPort;
    }
    public void ejecutar(Long idSucursal) {
        sucursalRepositoryPort.desactivar(idSucursal);
    }



}