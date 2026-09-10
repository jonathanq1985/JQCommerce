package com.jqdigitalsolutions.jqcommerce.configuracion.application.service;

import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.Moneda;
import com.jqdigitalsolutions.jqcommerce.configuracion.domain.port.MonedaRepositoryPort;
import org.springframework.stereotype.Service;

// Ing_JQC: Actualizar moneda
// Tecnología: Arquitectura Hexagonal
// Finalidad: Modificar información de una moneda

@Service
public class ActualizarMonedaUseCase {

    private final MonedaRepositoryPort monedaRepositoryPort;

    public ActualizarMonedaUseCase(
            MonedaRepositoryPort monedaRepositoryPort) {

        this.monedaRepositoryPort = monedaRepositoryPort;

    }

    public Moneda ejecutar(Moneda moneda) {

        return monedaRepositoryPort.actualizar(moneda);

    }

}