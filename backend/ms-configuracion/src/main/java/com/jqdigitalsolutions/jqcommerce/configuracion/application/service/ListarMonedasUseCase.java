package com.jqdigitalsolutions.jqcommerce.configuracion.application.service;

import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.Moneda;
import com.jqdigitalsolutions.jqcommerce.configuracion.domain.port.MonedaRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

// Ing_JQC: Listar monedas
// Tecnología: Arquitectura Hexagonal
// Finalidad: Consultar monedas registradas

@Service
public class ListarMonedasUseCase {

    private final MonedaRepositoryPort monedaRepositoryPort;

    public ListarMonedasUseCase(
            MonedaRepositoryPort monedaRepositoryPort) {

        this.monedaRepositoryPort = monedaRepositoryPort;

    }

    public List<Moneda> ejecutar() {

        return monedaRepositoryPort.listar();

    }

}