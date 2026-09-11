package com.jqdigitalsolutions.jqcommerce.productos.infrastructure.adapter;

import com.jqdigitalsolutions.jqcommerce.productos.domain.model.CategoriaProducto;
import com.jqdigitalsolutions.jqcommerce.productos.domain.port.CategoriaProductoRepositoryPort;
import com.jqdigitalsolutions.jqcommerce.productos.infrastructure.entity.CategoriaProductoEntity;
import com.jqdigitalsolutions.jqcommerce.productos.infrastructure.repository.CategoriaProductoJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoriaProductoRepositoryAdapter
        implements CategoriaProductoRepositoryPort {

    private final CategoriaProductoJpaRepository categoriaProductoJpaRepository;

    public CategoriaProductoRepositoryAdapter(
            CategoriaProductoJpaRepository categoriaProductoJpaRepository) {

        this.categoriaProductoJpaRepository = categoriaProductoJpaRepository;

    }

    @Override
    public CategoriaProducto guardar(
            CategoriaProducto categoriaProducto) {

        CategoriaProductoEntity entity =
                CategoriaProductoEntity.builder()
                        .codigo(categoriaProducto.getCodigo())
                        .nombre(categoriaProducto.getNombre())
                        .descripcion(categoriaProducto.getDescripcion())
                        .estado(categoriaProducto.getEstado())
                        .build();

        CategoriaProductoEntity guardado =
                categoriaProductoJpaRepository.save(entity);

        categoriaProducto.setIdCategoria(
                guardado.getIdCategoria()
        );

        return categoriaProducto;

    }

    @Override
    public List<CategoriaProducto> listar() {

        return categoriaProductoJpaRepository.findAll()
                .stream()
                .map(entity -> new CategoriaProducto(
                        entity.getIdCategoria(),
                        entity.getCodigo(),
                        entity.getNombre(),
                        entity.getDescripcion(),
                        entity.getEstado()
                ))
                .toList();

    }

    @Override
    public Optional<CategoriaProducto> buscarPorId(Long idCategoria) {

        return categoriaProductoJpaRepository
                .findById(idCategoria)
                .map(entity -> new CategoriaProducto(
                        entity.getIdCategoria(),
                        entity.getCodigo(),
                        entity.getNombre(),
                        entity.getDescripcion(),
                        entity.getEstado()
                ));

    }

}