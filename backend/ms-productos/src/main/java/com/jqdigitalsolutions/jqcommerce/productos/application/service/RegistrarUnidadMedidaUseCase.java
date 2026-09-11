package com.jqdigitalsolutions.jqcommerce.productos.application.service;

import com.jqdigitalsolutions.jqcommerce.productos.domain.model.UnidadMedida;
import com.jqdigitalsolutions.jqcommerce.productos.domain.port.UnidadMedidaRepositoryPort;
import org.springframework.stereotype.Service;

/**
 * Caso de uso responsable del registro
 * de unidades de medida.
 */
@Service
public class RegistrarUnidadMedidaUseCase {

    private final UnidadMedidaRepositoryPort unidadMedidaRepositoryPort;

    public RegistrarUnidadMedidaUseCase(
            UnidadMedidaRepositoryPort unidadMedidaRepositoryPort) {

        this.unidadMedidaRepositoryPort = unidadMedidaRepositoryPort;

    }

    public UnidadMedida ejecutar(
            UnidadMedida unidadMedida) {

        unidadMedida.setEstado(true);

        return unidadMedidaRepositoryPort.guardar(
                unidadMedida
        );

    }

}