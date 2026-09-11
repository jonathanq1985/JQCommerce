package com.jqdigitalsolutions.jqcommerce.productos.application.service;

import com.jqdigitalsolutions.jqcommerce.productos.domain.model.CategoriaProducto;
import com.jqdigitalsolutions.jqcommerce.productos.domain.port.CategoriaProductoRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Caso de uso responsable de obtener
 * las categorías registradas.
 */
@Service
public class ListarCategoriaProductoUseCase {

    private final CategoriaProductoRepositoryPort categoriaProductoRepositoryPort;

    public ListarCategoriaProductoUseCase(
            CategoriaProductoRepositoryPort categoriaProductoRepositoryPort) {

        this.categoriaProductoRepositoryPort = categoriaProductoRepositoryPort;

    }

    public List<CategoriaProducto> ejecutar() {

        return categoriaProductoRepositoryPort.listar();

    }

}