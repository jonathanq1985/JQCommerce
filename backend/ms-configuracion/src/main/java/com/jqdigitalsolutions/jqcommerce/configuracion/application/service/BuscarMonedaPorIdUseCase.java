package com.jqdigitalsolutions.jqcommerce.configuracion.application.service;

import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.Moneda;
import com.jqdigitalsolutions.jqcommerce.configuracion.domain.port.MonedaRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Ing_JQC: Buscar moneda por identificador
// Tecnología: Arquitectura Hexagonal
// Finalidad: Consultar una moneda específica

@Service
public class BuscarMonedaPorIdUseCase {

    private final MonedaRepositoryPort monedaRepositoryPort;

    public BuscarMonedaPorIdUseCase(MonedaRepositoryPort monedaRepositoryPort) {
        this.monedaRepositoryPort = monedaRepositoryPort;
    }

    public Optional<Moneda> ejecutar(Long idMoneda) {
        return monedaRepositoryPort.buscarPorId(idMoneda);
    }

}