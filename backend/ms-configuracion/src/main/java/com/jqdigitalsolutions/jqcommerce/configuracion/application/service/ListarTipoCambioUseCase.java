package com.jqdigitalsolutions.jqcommerce.configuracion.application.service;

import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.TipoCambio;
import com.jqdigitalsolutions.jqcommerce.configuracion.domain.port.TipoCambioRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

// Ing_JQC: Listar tipos de cambio
// Tecnología: Arquitectura Hexagonal
// Finalidad: Consultar todos los tipos de cambio registrados

@Service
public class ListarTipoCambioUseCase {

    private final TipoCambioRepositoryPort tipoCambioRepositoryPort;

    public ListarTipoCambioUseCase(
            TipoCambioRepositoryPort tipoCambioRepositoryPort) {

        this.tipoCambioRepositoryPort = tipoCambioRepositoryPort;

    }

    public List<TipoCambio> ejecutar() {

        return tipoCambioRepositoryPort.listar();

    }

}


