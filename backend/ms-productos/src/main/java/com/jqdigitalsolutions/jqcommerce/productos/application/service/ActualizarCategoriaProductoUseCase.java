package com.jqdigitalsolutions.jqcommerce.productos.application.service;

import com.jqdigitalsolutions.jqcommerce.productos.domain.model.CategoriaProducto;
import com.jqdigitalsolutions.jqcommerce.productos.domain.port.CategoriaProductoRepositoryPort;
import org.springframework.stereotype.Service;

/**
 * Caso de uso responsable de actualizar
 * la información de una categoría de producto.
 */
@Service
public class ActualizarCategoriaProductoUseCase {

    private final CategoriaProductoRepositoryPort categoriaProductoRepositoryPort;

    public ActualizarCategoriaProductoUseCase(
            CategoriaProductoRepositoryPort categoriaProductoRepositoryPort) {

        this.categoriaProductoRepositoryPort = categoriaProductoRepositoryPort;

    }

    public CategoriaProducto ejecutar(
            CategoriaProducto categoriaProducto) {

        return categoriaProductoRepositoryPort
                .actualizar(categoriaProducto);

    }

}