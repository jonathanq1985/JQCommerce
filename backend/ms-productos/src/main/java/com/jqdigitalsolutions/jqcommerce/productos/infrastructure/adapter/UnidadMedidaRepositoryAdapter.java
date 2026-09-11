package com.jqdigitalsolutions.jqcommerce.productos.infrastructure.adapter;

import com.jqdigitalsolutions.jqcommerce.productos.domain.model.UnidadMedida;
import com.jqdigitalsolutions.jqcommerce.productos.domain.port.UnidadMedidaRepositoryPort;
import com.jqdigitalsolutions.jqcommerce.productos.infrastructure.entity.UnidadMedidaEntity;
import com.jqdigitalsolutions.jqcommerce.productos.infrastructure.repository.UnidadMedidaJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UnidadMedidaRepositoryAdapter
        implements UnidadMedidaRepositoryPort {

    private final UnidadMedidaJpaRepository unidadMedidaJpaRepository;

    public UnidadMedidaRepositoryAdapter(
            UnidadMedidaJpaRepository unidadMedidaJpaRepository) {

        this.unidadMedidaJpaRepository = unidadMedidaJpaRepository;

    }

    @Override
    public UnidadMedida guardar(UnidadMedida unidadMedida) {

        UnidadMedidaEntity entity =
                UnidadMedidaEntity.builder()
                        .codigo(unidadMedida.getCodigo())
                        .nombre(unidadMedida.getNombre())
                        .abreviatura(unidadMedida.getAbreviatura())
                        .descripcion(unidadMedida.getDescripcion())
                        .estado(unidadMedida.getEstado())
                        .build();

        UnidadMedidaEntity guardado =
                unidadMedidaJpaRepository.save(entity);

        unidadMedida.setIdUnidad(
                guardado.getIdUnidad()
        );

        return unidadMedida;

    }

    @Override
    public List<UnidadMedida> listar() {

        return List.of();

    }

    @Override
    public Optional<UnidadMedida> buscarPorId(Long idUnidad) {

        return Optional.empty();

    }

}