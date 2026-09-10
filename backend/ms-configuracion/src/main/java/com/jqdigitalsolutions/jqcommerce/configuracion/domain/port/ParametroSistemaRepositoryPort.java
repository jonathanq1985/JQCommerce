package com.jqdigitalsolutions.jqcommerce.configuracion.domain.port;

import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.ParametroSistema;

import java.util.List;
import java.util.Optional;

public interface ParametroSistemaRepositoryPort {

    ParametroSistema guardar(ParametroSistema parametroSistema);

    List<ParametroSistema> listar();
    Optional<ParametroSistema> buscarPorId(Long idParametro);
    ParametroSistema actualizar(
            ParametroSistema parametroSistema
    );
}