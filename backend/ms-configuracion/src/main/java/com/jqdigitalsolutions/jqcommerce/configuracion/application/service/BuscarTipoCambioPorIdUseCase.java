package com.jqdigitalsolutions.jqcommerce.configuracion.application.service;

import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.TipoCambio;
import com.jqdigitalsolutions.jqcommerce.configuracion.domain.port.TipoCambioRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarTipoCambioPorIdUseCase {

    private final TipoCambioRepositoryPort tipoCambioRepositoryPort;

    public BuscarTipoCambioPorIdUseCase(TipoCambioRepositoryPort tipoCambioRepositoryPort) {

        this.tipoCambioRepositoryPort = tipoCambioRepositoryPort;

    }

    public Optional<TipoCambio> ejecutar( Long idTipoCambio) {

        return tipoCambioRepositoryPort.buscarPorId(idTipoCambio);

    }

}