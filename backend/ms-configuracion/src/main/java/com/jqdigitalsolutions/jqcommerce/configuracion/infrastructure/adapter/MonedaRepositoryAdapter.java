package com.jqdigitalsolutions.jqcommerce.configuracion.infrastructure.adapter;

import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.Moneda;
import com.jqdigitalsolutions.jqcommerce.configuracion.domain.port.MonedaRepositoryPort;
import com.jqdigitalsolutions.jqcommerce.configuracion.infrastructure.entity.MonedaEntity;
import com.jqdigitalsolutions.jqcommerce.configuracion.infrastructure.repository.MonedaJpaRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
// Ing_JQC: Adaptador de persistencia de monedas
// Tecnología: Arquitectura Hexagonal
// Finalidad: Conectar dominio con PostgreSQL
@Component
public class MonedaRepositoryAdapter implements MonedaRepositoryPort {

    private final MonedaJpaRepository monedaJpaRepository;

    public MonedaRepositoryAdapter(
            MonedaJpaRepository monedaJpaRepository) {

        this.monedaJpaRepository = monedaJpaRepository;

    }

    @Override
    public Moneda guardar(Moneda moneda) {

        MonedaEntity entity = MonedaEntity.builder()
                .codigo(moneda.getCodigo())
                .nombre(moneda.getNombre())
                .simbolo(moneda.getSimbolo())
                .estado(moneda.getEstado())
                .build();

        MonedaEntity guardada =
                monedaJpaRepository.save(entity);

        moneda.setIdMoneda(
                guardada.getIdMoneda()
        );

        return moneda;

    }

    @Override
    public List<Moneda> listar() {

        return List.of();

    }

    @Override
    public Optional<Moneda> buscarPorId(Long idMoneda) {

        return Optional.empty();

    }

}