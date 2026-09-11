package com.jqdigitalsolutions.jqcommerce.productos.application.service;

import com.jqdigitalsolutions.jqcommerce.productos.domain.port.CategoriaProductoRepositoryPort;
import org.springframework.stereotype.Service;

/**
 * Caso de uso responsable de la desactivación
 * lógica de categorías de producto.
 */
@Service
public class DesactivarCategoriaProductoUseCase {

    private final CategoriaProductoRepositoryPort categoriaProductoRepositoryPort;

    public DesactivarCategoriaProductoUseCase(
            CategoriaProductoRepositoryPort categoriaProductoRepositoryPort) {

        this.categoriaProductoRepositoryPort = categoriaProductoRepositoryPort;
    }

    public void ejecutar(Long idCategoria) {
        categoriaProductoRepositoryPort.desactivar(idCategoria);

    }

}