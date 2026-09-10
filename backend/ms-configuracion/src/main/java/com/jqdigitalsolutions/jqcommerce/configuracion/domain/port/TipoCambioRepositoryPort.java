package com.jqdigitalsolutions.jqcommerce.configuracion.domain.port;

import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.TipoCambio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TipoCambioRepositoryPort {

    TipoCambio guardar(TipoCambio tipoCambio);

    List<TipoCambio> listar();

    Optional<TipoCambio> buscarPorId(Long idTipoCambio);
    boolean existeTipoCambio(
            Long monedaOrigenId,
            Long monedaDestinoId,
            LocalDate fechaVigencia
    );
}