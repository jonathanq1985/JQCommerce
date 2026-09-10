package com.jqdigitalsolutions.jqcommerce.configuracion.infrastructure.adapter;

import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.TipoCambio;
import com.jqdigitalsolutions.jqcommerce.configuracion.domain.port.TipoCambioRepositoryPort;
import com.jqdigitalsolutions.jqcommerce.configuracion.infrastructure.entity.TipoCambioEntity;
import com.jqdigitalsolutions.jqcommerce.configuracion.infrastructure.repository.TipoCambioJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

// Ing_JQC: Adaptador de persistencia de tipos de cambio
// Tecnología: Arquitectura Hexagonal
// Finalidad: Conectar dominio con PostgreSQL

@Component
public class TipoCambioRepositoryAdapter
        implements TipoCambioRepositoryPort {

    private final TipoCambioJpaRepository tipoCambioJpaRepository;

    public TipoCambioRepositoryAdapter(
            TipoCambioJpaRepository tipoCambioJpaRepository) {

        this.tipoCambioJpaRepository = tipoCambioJpaRepository;

    }

    @Override
    public TipoCambio guardar(TipoCambio tipoCambio) {

        TipoCambioEntity entity =
                TipoCambioEntity.builder()
                        .monedaOrigenId(
                                tipoCambio.getMonedaOrigenId()
                        )
                        .monedaDestinoId(
                                tipoCambio.getMonedaDestinoId()
                        )
                        .valor(
                                tipoCambio.getValor()
                        )
                        .fechaVigencia(
                                tipoCambio.getFechaVigencia()
                        )
                        .build();

        TipoCambioEntity guardado =
                tipoCambioJpaRepository.save(entity);

        tipoCambio.setIdTipoCambio(
                guardado.getIdTipoCambio()
        );

        return tipoCambio;

    }

    @Override
    public List<TipoCambio> listar() {

        return List.of();

    }

    @Override
    public Optional<TipoCambio> buscarPorId(
            Long idTipoCambio) {

        return Optional.empty();

    }
}