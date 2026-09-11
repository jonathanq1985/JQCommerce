package com.jqdigitalsolutions.jqcommerce.productos.adapter.input.controller;

import com.jqdigitalsolutions.jqcommerce.productos.adapter.input.dto.CategoriaProductoRequest;
import com.jqdigitalsolutions.jqcommerce.productos.adapter.input.dto.CategoriaProductoResponse;
import com.jqdigitalsolutions.jqcommerce.productos.application.service.*;
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
    private final BuscarCategoriaProductoPorIdUseCase
            buscarCategoriaProductoPorIdUseCase;
    private final ActualizarCategoriaProductoUseCase
            actualizarCategoriaProductoUseCase;
    private final DesactivarCategoriaProductoUseCase
            desactivarCategoriaProductoUseCase;
    private final ActivarCategoriaProductoUseCase
            activarCategoriaProductoUseCase;
    public CategoriaProductoController(
            RegistrarCategoriaProductoUseCase registrarCategoriaProductoUseCase,
            ListarCategoriaProductoUseCase listarCategoriaProductoUseCase,
            BuscarCategoriaProductoPorIdUseCase buscarCategoriaProductoPorIdUseCase,
            ActualizarCategoriaProductoUseCase actualizarCategoriaProductoUseCase,
            DesactivarCategoriaProductoUseCase desactivarCategoriaProductoUseCase,
            ActivarCategoriaProductoUseCase activarCategoriaProductoUseCase) {

        this.registrarCategoriaProductoUseCase =
                registrarCategoriaProductoUseCase;

        this.listarCategoriaProductoUseCase =
                listarCategoriaProductoUseCase;

        this.buscarCategoriaProductoPorIdUseCase =
                buscarCategoriaProductoPorIdUseCase;

        this.actualizarCategoriaProductoUseCase =
                actualizarCategoriaProductoUseCase;

        this.desactivarCategoriaProductoUseCase =
                desactivarCategoriaProductoUseCase;

        this.activarCategoriaProductoUseCase =
                activarCategoriaProductoUseCase;

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
    @GetMapping("/{id}")
    public CategoriaProductoResponse buscarPorId(
            @PathVariable Long id) {

        LOGGER.info(
                "Consultando categoria de producto con id {}",
                id
        );

        CategoriaProducto categoria =
                buscarCategoriaProductoPorIdUseCase
                        .ejecutar(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Categoria de producto no encontrada"
                                )
                        );

        return new CategoriaProductoResponse(
                categoria.getIdCategoria(),
                categoria.getCodigo(),
                categoria.getNombre(),
                categoria.getDescripcion(),
                categoria.getEstado()
        );

    }
    @PutMapping("/{id}")
    public CategoriaProductoResponse actualizarCategoriaProducto(
            @PathVariable Long id,
            @RequestBody CategoriaProductoRequest request) {

        LOGGER.info(
                "Actualizando categoria de producto con id {}",
                id
        );

        CategoriaProducto categoria =
                new CategoriaProducto();

        categoria.setIdCategoria(id);

        categoria.setCodigo(
                request.codigo()
        );

        categoria.setNombre(
                request.nombre()
        );

        categoria.setDescripcion(
                request.descripcion()
        );

        CategoriaProducto actualizada =
                actualizarCategoriaProductoUseCase
                        .ejecutar(categoria);

        return new CategoriaProductoResponse(
                actualizada.getIdCategoria(),
                actualizada.getCodigo(),
                actualizada.getNombre(),
                actualizada.getDescripcion(),
                actualizada.getEstado()
        );

    }
    @PatchMapping("/{id}/desactivar")
    public void desactivarCategoriaProducto(
            @PathVariable Long id) {

        LOGGER.info("Desactivando categoria de producto con id {}", id);

        desactivarCategoriaProductoUseCase.ejecutar(id);

    }
    @PatchMapping("/{id}/activar")
    public void activarCategoriaProducto(
            @PathVariable Long id) {

        LOGGER.info(
                "Activando categoria de producto con id {}",
                id
        );

        activarCategoriaProductoUseCase.ejecutar(id);

    }
}