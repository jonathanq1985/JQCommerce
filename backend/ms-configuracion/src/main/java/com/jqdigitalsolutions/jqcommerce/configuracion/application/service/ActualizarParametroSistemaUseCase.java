package com.jqdigitalsolutions.jqcommerce.configuracion.application.service;

import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.ParametroSistema;
import com.jqdigitalsolutions.jqcommerce.configuracion.domain.port.ParametroSistemaRepositoryPort;
import org.springframework.stereotype.Service;

/**
 * Caso de uso responsable de actualizar la configuración
 * de parámetros registrada en el sistema.
 *
 * Objetivo:
 * Permitir la modificación controlada de parámetros
 * utilizados por los distintos procesos de negocio.
 */
@Service
public class ActualizarParametroSistemaUseCase {

    private final ParametroSistemaRepositoryPort parametroSistemaRepositoryPort;

    public ActualizarParametroSistemaUseCase(
            ParametroSistemaRepositoryPort parametroSistemaRepositoryPort) {

        this.parametroSistemaRepositoryPort =
                parametroSistemaRepositoryPort;

    }

    public ParametroSistema ejecutar(
            ParametroSistema parametroSistema) {

        return parametroSistemaRepositoryPort
                .actualizar(parametroSistema);

    }

}