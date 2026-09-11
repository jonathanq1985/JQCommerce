package com.jqdigitalsolutions.jqcommerce.productos.application.service;

import com.jqdigitalsolutions.jqcommerce.productos.domain.model.UnidadMedida;
import com.jqdigitalsolutions.jqcommerce.productos.domain.port.UnidadMedidaRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Caso de uso responsable de obtener
 * las unidades de medida registradas.
 */
@Service
public class ListarUnidadMedidaUseCase {

    private final UnidadMedidaRepositoryPort unidadMedidaRepositoryPort;

    public ListarUnidadMedidaUseCase(
            UnidadMedidaRepositoryPort unidadMedidaRepositoryPort) {

        this.unidadMedidaRepositoryPort =
                unidadMedidaRepositoryPort;

    }

    public List<UnidadMedida> ejecutar() {

        return unidadMedidaRepositoryPort.listar();

    }

}