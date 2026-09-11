package com.jqdigitalsolutions.jqcommerce.productos.adapter.input.controller;

import com.jqdigitalsolutions.jqcommerce.productos.adapter.input.dto.UnidadMedidaRequest;
import com.jqdigitalsolutions.jqcommerce.productos.adapter.input.dto.UnidadMedidaResponse;
import com.jqdigitalsolutions.jqcommerce.productos.application.service.RegistrarUnidadMedidaUseCase;
import com.jqdigitalsolutions.jqcommerce.productos.domain.model.UnidadMedida;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/unidades-medida")
public class UnidadMedidaController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(
                    UnidadMedidaController.class
            );

    private final RegistrarUnidadMedidaUseCase
            registrarUnidadMedidaUseCase;

    public UnidadMedidaController(
            RegistrarUnidadMedidaUseCase registrarUnidadMedidaUseCase) {

        this.registrarUnidadMedidaUseCase =
                registrarUnidadMedidaUseCase;

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

}