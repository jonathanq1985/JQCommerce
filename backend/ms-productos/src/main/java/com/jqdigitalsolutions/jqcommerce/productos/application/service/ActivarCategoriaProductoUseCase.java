package com.jqdigitalsolutions.jqcommerce.productos.application.service;

import com.jqdigitalsolutions.jqcommerce.productos.domain.port.CategoriaProductoRepositoryPort;
import org.springframework.stereotype.Service;

/**
 * Caso de uso responsable de la activación
 * lógica de categorías de producto.
 */
@Service
public class ActivarCategoriaProductoUseCase {

    private final CategoriaProductoRepositoryPort categoriaProductoRepositoryPort;

    public ActivarCategoriaProductoUseCase(
            CategoriaProductoRepositoryPort categoriaProductoRepositoryPort) {

        this.categoriaProductoRepositoryPort = categoriaProductoRepositoryPort;

    }

    public void ejecutar(Long idCategoria) {

        categoriaProductoRepositoryPort.activar(idCategoria);

    }

}