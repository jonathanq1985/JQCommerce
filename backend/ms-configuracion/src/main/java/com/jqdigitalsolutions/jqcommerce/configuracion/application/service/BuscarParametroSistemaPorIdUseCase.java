package com.jqdigitalsolutions.jqcommerce.configuracion.application.service;

import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.ParametroSistema;
import com.jqdigitalsolutions.jqcommerce.configuracion.domain.port.ParametroSistemaRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Caso de uso responsable de obtener el detalle de un
 * parámetro del sistema a partir de su identificador.
 *
 * Objetivo:
 * Facilitar la consulta individual de configuraciones
 * registradas en el sistema.
 */
@Service
public class BuscarParametroSistemaPorIdUseCase {

    private final ParametroSistemaRepositoryPort parametroSistemaRepositoryPort;

    public BuscarParametroSistemaPorIdUseCase(
            ParametroSistemaRepositoryPort parametroSistemaRepositoryPort) {

        this.parametroSistemaRepositoryPort = parametroSistemaRepositoryPort;

    }

    public Optional<ParametroSistema> ejecutar(
            Long idParametro) {

        return parametroSistemaRepositoryPort
                .buscarPorId(idParametro);

    }

}