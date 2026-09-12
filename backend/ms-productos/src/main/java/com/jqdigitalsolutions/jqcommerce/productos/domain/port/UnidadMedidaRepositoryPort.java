package com.jqdigitalsolutions.jqcommerce.productos.domain.port;

import com.jqdigitalsolutions.jqcommerce.productos.domain.model.UnidadMedida;

import java.util.List;
import java.util.Optional;

public interface UnidadMedidaRepositoryPort {

    UnidadMedida guardar(UnidadMedida unidadMedida);

    List<UnidadMedida> listar();

    Optional<UnidadMedida> buscarPorId(Long idUnidad);
    UnidadMedida actualizar(UnidadMedida unidadMedida);
    void desactivar(Long idUnidad);
}