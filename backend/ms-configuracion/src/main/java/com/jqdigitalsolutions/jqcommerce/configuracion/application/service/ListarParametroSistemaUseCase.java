package com.jqdigitalsolutions.jqcommerce.configuracion.application.service;

import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.ParametroSistema;
import com.jqdigitalsolutions.jqcommerce.configuracion.domain.port.ParametroSistemaRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Caso de uso responsable de consultar los parámetros
 * configurados en el sistema.
 *
 * Objetivo:
 * Obtener el catálogo de parámetros registrados para
 * su posterior administración desde los módulos de negocio.
 */
@Service
public class ListarParametroSistemaUseCase {

    private final ParametroSistemaRepositoryPort parametroSistemaRepositoryPort;

    public ListarParametroSistemaUseCase(
            ParametroSistemaRepositoryPort parametroSistemaRepositoryPort) {

        this.parametroSistemaRepositoryPort = parametroSistemaRepositoryPort;

    }

    public List<ParametroSistema> ejecutar() {

        return parametroSistemaRepositoryPort.listar();

    }

}