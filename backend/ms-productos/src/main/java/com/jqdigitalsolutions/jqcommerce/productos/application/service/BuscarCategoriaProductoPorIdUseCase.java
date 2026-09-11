package com.jqdigitalsolutions.jqcommerce.productos.application.service;

import com.jqdigitalsolutions.jqcommerce.productos.domain.model.CategoriaProducto;
import com.jqdigitalsolutions.jqcommerce.productos.domain.port.CategoriaProductoRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Caso de uso responsable de consultar
 * una categoría de producto por identificador.
 */
@Service
public class BuscarCategoriaProductoPorIdUseCase {

    private final CategoriaProductoRepositoryPort categoriaProductoRepositoryPort;

    public BuscarCategoriaProductoPorIdUseCase(
            CategoriaProductoRepositoryPort categoriaProductoRepositoryPort) {

        this.categoriaProductoRepositoryPort = categoriaProductoRepositoryPort;

    }

    public Optional<CategoriaProducto> ejecutar(Long idCategoria) {

        return categoriaProductoRepositoryPort.buscarPorId(idCategoria);

    }

}