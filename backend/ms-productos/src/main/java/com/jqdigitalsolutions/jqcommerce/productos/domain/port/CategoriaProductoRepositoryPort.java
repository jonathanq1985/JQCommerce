package com.jqdigitalsolutions.jqcommerce.productos.domain.port;

import com.jqdigitalsolutions.jqcommerce.productos.domain.model.CategoriaProducto;

import java.util.List;
import java.util.Optional;

public interface CategoriaProductoRepositoryPort {

    CategoriaProducto guardar(
            CategoriaProducto categoriaProducto
    );

    List<CategoriaProducto> listar();

    Optional<CategoriaProducto> buscarPorId(Long idCategoria);
    CategoriaProducto actualizar(CategoriaProducto categoriaProducto);
    void desactivar(Long idCategoria);
    void activar(Long idCategoria);
}