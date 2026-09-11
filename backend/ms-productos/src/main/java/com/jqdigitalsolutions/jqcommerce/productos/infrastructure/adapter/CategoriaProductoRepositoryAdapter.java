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

    @Override
    public CategoriaProducto actualizar(
            CategoriaProducto categoriaProducto) {

        CategoriaProductoEntity entity =
                categoriaProductoJpaRepository
                        .findById(categoriaProducto.getIdCategoria())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Categoria de producto no encontrada"
                                ));

        entity.setCodigo(categoriaProducto.getCodigo());
        entity.setNombre(categoriaProducto.getNombre());
        entity.setDescripcion(categoriaProducto.getDescripcion());
        categoriaProductoJpaRepository.save(entity);

        return categoriaProducto;

    }
    @Override
    public void desactivar(Long idCategoria) {

        CategoriaProductoEntity entity =
                categoriaProductoJpaRepository
                        .findById(idCategoria)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Categoria de producto no encontrada"
                                ));

        entity.setEstado(false);
        categoriaProductoJpaRepository.save(entity);

    }
}