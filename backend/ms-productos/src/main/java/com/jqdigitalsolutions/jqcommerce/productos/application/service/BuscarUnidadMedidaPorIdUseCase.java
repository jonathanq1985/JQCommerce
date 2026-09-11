package com.jqdigitalsolutions.jqcommerce.productos.application.service;

import com.jqdigitalsolutions.jqcommerce.productos.domain.model.UnidadMedida;
import com.jqdigitalsolutions.jqcommerce.productos.domain.port.UnidadMedidaRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Caso de uso responsable de consultar
 * una unidad de medida por identificador.
 */
@Service
public class BuscarUnidadMedidaPorIdUseCase {

    private final UnidadMedidaRepositoryPort unidadMedidaRepositoryPort;

    public BuscarUnidadMedidaPorIdUseCase(
            UnidadMedidaRepositoryPort unidadMedidaRepositoryPort) {

        this.unidadMedidaRepositoryPort =
                unidadMedidaRepositoryPort;

    }

    public Optional<UnidadMedida> ejecutar(Long idUnidad) {

        return unidadMedidaRepositoryPort
                .buscarPorId(idUnidad);

    }

}