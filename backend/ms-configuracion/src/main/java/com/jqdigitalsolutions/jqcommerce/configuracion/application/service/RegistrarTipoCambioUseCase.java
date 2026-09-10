package com.jqdigitalsolutions.jqcommerce.configuracion.application.service;

import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.TipoCambio;
import com.jqdigitalsolutions.jqcommerce.configuracion.domain.port.TipoCambioRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class RegistrarTipoCambioUseCase {

    private final TipoCambioRepositoryPort tipoCambioRepositoryPort;

    public RegistrarTipoCambioUseCase(
            TipoCambioRepositoryPort tipoCambioRepositoryPort) {

        this.tipoCambioRepositoryPort = tipoCambioRepositoryPort;

    }

    public TipoCambio ejecutar(
            TipoCambio tipoCambio) {

        if (tipoCambio.getMonedaOrigenId()
                .equals(tipoCambio.getMonedaDestinoId())) {

            throw new RuntimeException(
                    "La moneda origen y destino no pueden ser iguales"
            );

        }

        if (tipoCambioRepositoryPort.existeTipoCambio(
                tipoCambio.getMonedaOrigenId(),
                tipoCambio.getMonedaDestinoId(),
                tipoCambio.getFechaVigencia())) {

            throw new RuntimeException(
                    "Ya existe un tipo de cambio para esa fecha"
            );

        }

        return tipoCambioRepositoryPort.guardar(
                tipoCambio
        );

    }

}