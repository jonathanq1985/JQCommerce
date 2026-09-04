package com.jqdigitalsolutions.jqcommerce.configuracion.application.service;

import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.Moneda;
import com.jqdigitalsolutions.jqcommerce.configuracion.domain.port.MonedaRepositoryPort;
import org.springframework.stereotype.Service;

// Ing_JQC: Registrar moneda
// Tecnología: Arquitectura Hexagonal
// Finalidad: Crear monedas del sistema

@Service
public class RegistrarMonedaUseCase {

    private final MonedaRepositoryPort monedaRepositoryPort;

    public RegistrarMonedaUseCase(
            MonedaRepositoryPort monedaRepositoryPort) {

        this.monedaRepositoryPort = monedaRepositoryPort;

    }

    public Moneda ejecutar(Moneda moneda) {

        moneda.setEstado(true);

        return monedaRepositoryPort.guardar(moneda);

    }

}