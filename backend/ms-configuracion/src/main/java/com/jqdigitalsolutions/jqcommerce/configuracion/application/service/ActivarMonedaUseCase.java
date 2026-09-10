package com.jqdigitalsolutions.jqcommerce.configuracion.application.service;

import com.jqdigitalsolutions.jqcommerce.configuracion.domain.port.MonedaRepositoryPort;
import org.springframework.stereotype.Service;

// Ing_JQC: Activar moneda
// Tecnología: Arquitectura Hexagonal
// Finalidad: Reactivar monedas deshabilitadas

@Service
public class ActivarMonedaUseCase {

    private final MonedaRepositoryPort monedaRepositoryPort;

    public ActivarMonedaUseCase(
            MonedaRepositoryPort monedaRepositoryPort) {

        this.monedaRepositoryPort = monedaRepositoryPort;

    }

    public void ejecutar(Long idMoneda) {
        monedaRepositoryPort.activar(idMoneda);

    }

}