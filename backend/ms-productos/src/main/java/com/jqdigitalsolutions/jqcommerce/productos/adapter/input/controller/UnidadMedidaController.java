package com.jqdigitalsolutions.jqcommerce.productos.adapter.input.controller;

import com.jqdigitalsolutions.jqcommerce.productos.adapter.input.dto.UnidadMedidaRequest;
import com.jqdigitalsolutions.jqcommerce.productos.adapter.input.dto.UnidadMedidaResponse;
import com.jqdigitalsolutions.jqcommerce.productos.application.service.BuscarUnidadMedidaPorIdUseCase;
import com.jqdigitalsolutions.jqcommerce.productos.application.service.ListarUnidadMedidaUseCase;
import com.jqdigitalsolutions.jqcommerce.productos.application.service.RegistrarUnidadMedidaUseCase;
import com.jqdigitalsolutions.jqcommerce.productos.domain.model.UnidadMedida;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/unidades-medida")
public class UnidadMedidaController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(
                    UnidadMedidaController.class
            );

    private final RegistrarUnidadMedidaUseCase
            registrarUnidadMedidaUseCase;
    private final ListarUnidadMedidaUseCase
            listarUnidadMedidaUseCase;
    private final BuscarUnidadMedidaPorIdUseCase
            buscarUnidadMedidaPorIdUseCase;
    public UnidadMedidaController(
            RegistrarUnidadMedidaUseCase registrarUnidadMedidaUseCase,
            ListarUnidadMedidaUseCase listarUnidadMedidaUseCase,
            BuscarUnidadMedidaPorIdUseCase buscarUnidadMedidaPorIdUseCase) {

        this.registrarUnidadMedidaUseCase =
                registrarUnidadMedidaUseCase;

        this.listarUnidadMedidaUseCase =
                listarUnidadMedidaUseCase;

        this.buscarUnidadMedidaPorIdUseCase =
                buscarUnidadMedidaPorIdUseCase;

    }

    @PostMapping
    public UnidadMedidaResponse registrarUnidadMedida(
            @RequestBody UnidadMedidaRequest request) {

        LOGGER.info(
                "Registrando unidad de medida {}",
                request.codigo()
        );

        UnidadMedida unidadMedida =
                new UnidadMedida();

        unidadMedida.setCodigo(
                request.codigo()
        );

        unidadMedida.setNombre(
                request.nombre()
        );

        unidadMedida.setAbreviatura(
                request.abreviatura()
        );

        unidadMedida.setDescripcion(
                request.descripcion()
        );

        UnidadMedida guardada =
                registrarUnidadMedidaUseCase
                        .ejecutar(unidadMedida);

        return new UnidadMedidaResponse(
                guardada.getIdUnidad(),
                guardada.getCodigo(),
                guardada.getNombre(),
                guardada.getAbreviatura(),
                guardada.getDescripcion(),
                guardada.getEstado()
        );

    }
    @GetMapping
    public List<UnidadMedidaResponse> listarUnidadesMedida() {

        LOGGER.info(
                "Consultando listado de unidades de medida"
        );

        List<UnidadMedida> unidades =
                listarUnidadMedidaUseCase.ejecutar();

        return unidades.stream()
                .map(unidad ->
                        new UnidadMedidaResponse(
                                unidad.getIdUnidad(),
                                unidad.getCodigo(),
                                unidad.getNombre(),
                                unidad.getAbreviatura(),
                                unidad.getDescripcion(),
                                unidad.getEstado()
                        )
                )
                .toList();

    }
    @GetMapping("/{id}")
    public UnidadMedidaResponse buscarPorId(
            @PathVariable Long id) {

        LOGGER.info(
                "Consultando unidad de medida con id {}",
                id
        );

        UnidadMedida unidad =
                buscarUnidadMedidaPorIdUseCase
                        .ejecutar(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Unidad de medida no encontrada"
                                )
                        );

        return new UnidadMedidaResponse(
                unidad.getIdUnidad(),
                unidad.getCodigo(),
                unidad.getNombre(),
                unidad.getAbreviatura(),
                unidad.getDescripcion(),
                unidad.getEstado()
        );

    }

}