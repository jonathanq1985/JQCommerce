package com.jqdigitalsolutions.jqcommerce.productos.application.service;

import com.jqdigitalsolutions.jqcommerce.productos.domain.port.UnidadMedidaRepositoryPort;
import org.springframework.stereotype.Service;

/**
 * Caso de uso responsable de la desactivación
 * lógica de unidades de medida.
 */
@Service
public class DesactivarUnidadMedidaUseCase {

    private final UnidadMedidaRepositoryPort unidadMedidaRepositoryPort;

    public DesactivarUnidadMedidaUseCase(
            UnidadMedidaRepositoryPort unidadMedidaRepositoryPort) {

        this.unidadMedidaRepositoryPort =
                unidadMedidaRepositoryPort;

    }

    public void ejecutar(Long idUnidad) {

        unidadMedidaRepositoryPort.desactivar(idUnidad);

    }

}