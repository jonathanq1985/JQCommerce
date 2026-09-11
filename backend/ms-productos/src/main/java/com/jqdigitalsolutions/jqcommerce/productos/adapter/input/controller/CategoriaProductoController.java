package com.jqdigitalsolutions.jqcommerce.productos.adapter.input.controller;

import com.jqdigitalsolutions.jqcommerce.productos.adapter.input.dto.CategoriaProductoRequest;
import com.jqdigitalsolutions.jqcommerce.productos.adapter.input.dto.CategoriaProductoResponse;
import com.jqdigitalsolutions.jqcommerce.productos.application.service.ListarCategoriaProductoUseCase;
import com.jqdigitalsolutions.jqcommerce.productos.application.service.RegistrarCategoriaProductoUseCase;
import com.jqdigitalsolutions.jqcommerce.productos.domain.model.CategoriaProducto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categorias-producto")
public class CategoriaProductoController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(
                    CategoriaProductoController.class
            );

    private final RegistrarCategoriaProductoUseCase
            registrarCategoriaProductoUseCase;
    private final ListarCategoriaProductoUseCase
            listarCategoriaProductoUseCase;
    public CategoriaProductoController(
            RegistrarCategoriaProductoUseCase registrarCategoriaProductoUseCase,
            ListarCategoriaProductoUseCase listarCategoriaProductoUseCase) {

        this.registrarCategoriaProductoUseCase =
                registrarCategoriaProductoUseCase;

        this.listarCategoriaProductoUseCase =
                listarCategoriaProductoUseCase;

    }

    @PostMapping
    public CategoriaProductoResponse registrarCategoriaProducto(
            @RequestBody CategoriaProductoRequest request) {

        LOGGER.info(
                "Registrando categoria de producto {}",
                request.codigo()
        );

        CategoriaProducto categoria =
                new CategoriaProducto();

        categoria.setCodigo(
                request.codigo()
        );

        categoria.setNombre(
                request.nombre()
        );

        categoria.setDescripcion(
                request.descripcion()
        );

        CategoriaProducto guardada =
                registrarCategoriaProductoUseCase
                        .ejecutar(categoria);

        return new CategoriaProductoResponse(
                guardada.getIdCategoria(),
                guardada.getCodigo(),
                guardada.getNombre(),
                guardada.getDescripcion(),
                guardada.getEstado()
        );

    }
    @GetMapping
    public List<CategoriaProductoResponse> listarCategoriasProducto() {

        LOGGER.info("Consultando listado de categorias de producto");
        List<CategoriaProducto> categorias =
                listarCategoriaProductoUseCase.ejecutar();

        return categorias.stream()
                .map(categoria ->
                        new CategoriaProductoResponse(
                                categoria.getIdCategoria(),
                                categoria.getCodigo(),
                                categoria.getNombre(),
                                categoria.getDescripcion(),
                                categoria.getEstado()
                        )
                )
                .toList();

    }

}