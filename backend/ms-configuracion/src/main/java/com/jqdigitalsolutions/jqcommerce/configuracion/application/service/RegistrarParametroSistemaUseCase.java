package com.jqdigitalsolutions.jqcommerce.configuracion.application.service;

import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.ParametroSistema;
import com.jqdigitalsolutions.jqcommerce.configuracion.domain.port.ParametroSistemaRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class RegistrarParametroSistemaUseCase {

    private final ParametroSistemaRepositoryPort parametroSistemaRepositoryPort;

    public RegistrarParametroSistemaUseCase(
            ParametroSistemaRepositoryPort parametroSistemaRepositoryPort) {

        this.parametroSistemaRepositoryPort = parametroSistemaRepositoryPort;

    }

    public ParametroSistema ejecutar(
            ParametroSistema parametroSistema) {

        return parametroSistemaRepositoryPort
                .guardar(parametroSistema);

    }

}