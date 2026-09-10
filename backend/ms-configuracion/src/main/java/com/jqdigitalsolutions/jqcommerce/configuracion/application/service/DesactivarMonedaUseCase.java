package com.jqdigitalsolutions.jqcommerce.configuracion.application.service;

import com.jqdigitalsolutions.jqcommerce.configuracion.domain.port.MonedaRepositoryPort;
import org.springframework.stereotype.Service;

// Ing_JQC: Desactivar moneda
// Tecnología: Arquitectura Hexagonal
// Finalidad: Realizar deshabilitación lógica

@Service
public class DesactivarMonedaUseCase {

    private final MonedaRepositoryPort monedaRepositoryPort;

    public DesactivarMonedaUseCase(
            MonedaRepositoryPort monedaRepositoryPort) {

        this.monedaRepositoryPort = monedaRepositoryPort;

    }

    public void ejecutar(Long idMoneda) {

        monedaRepositoryPort.desactivar(idMoneda);

    }

}