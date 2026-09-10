package com.jqdigitalsolutions.jqcommerce.configuracion.infrastructure.repository;

import com.jqdigitalsolutions.jqcommerce.configuracion.infrastructure.entity.TipoCambioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TipoCambioJpaRepository  extends JpaRepository<TipoCambioEntity, Long> {

    boolean existsByMonedaOrigenIdAndMonedaDestinoIdAndFechaVigencia(
            Long monedaOrigenId,
            Long monedaDestinoId,
            LocalDate fechaVigencia
    );
}