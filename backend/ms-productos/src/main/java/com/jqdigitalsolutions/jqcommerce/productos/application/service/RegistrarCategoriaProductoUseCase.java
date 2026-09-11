package com.jqdigitalsolutions.jqcommerce.productos.application.service;

import com.jqdigitalsolutions.jqcommerce.productos.domain.model.CategoriaProducto;
import com.jqdigitalsolutions.jqcommerce.productos.domain.port.CategoriaProductoRepositoryPort;
import org.springframework.stereotype.Service;

/**
 * Caso de uso responsable del registro
 * de categorías de producto.
 */
@Service
public class RegistrarCategoriaProductoUseCase {

    private final CategoriaProductoRepositoryPort categoriaProductoRepositoryPort;

    public RegistrarCategoriaProductoUseCase(
            CategoriaProductoRepositoryPort categoriaProductoRepositoryPort) {

        this.categoriaProductoRepositoryPort = categoriaProductoRepositoryPort;

    }

    public CategoriaProducto ejecutar(
            CategoriaProducto categoriaProducto) {

        categoriaProducto.setEstado(true);

        return categoriaProductoRepositoryPort.guardar(
                categoriaProducto
        );

    }

}