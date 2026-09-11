package com.jqdigitalsolutions.jqcommerce.productos.application.service;

import com.jqdigitalsolutions.jqcommerce.productos.domain.model.UnidadMedida;
import com.jqdigitalsolutions.jqcommerce.productos.domain.port.UnidadMedidaRepositoryPort;
import org.springframework.stereotype.Service;

/**
 * Caso de uso responsable de actualizar
 * la información de una unidad de medida.
 */
@Service
public class ActualizarUnidadMedidaUseCase {

    private final UnidadMedidaRepositoryPort unidadMedidaRepositoryPort;

    public ActualizarUnidadMedidaUseCase(
            UnidadMedidaRepositoryPort unidadMedidaRepositoryPort) {

        this.unidadMedidaRepositoryPort =
                unidadMedidaRepositoryPort;

    }

    public UnidadMedida ejecutar(
            UnidadMedida unidadMedida) {

        return unidadMedidaRepositoryPort
                .actualizar(unidadMedida);

    }

}