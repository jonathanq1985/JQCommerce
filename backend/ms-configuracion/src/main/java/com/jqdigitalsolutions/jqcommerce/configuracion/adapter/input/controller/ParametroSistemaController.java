package com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.controller;

import com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.dto.ParametroSistemaRequest;
import com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.dto.ParametroSistemaResponse;
import com.jqdigitalsolutions.jqcommerce.configuracion.application.service.RegistrarParametroSistemaUseCase;
import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.ParametroSistema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/parametros-sistema")
public class ParametroSistemaController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(
                    ParametroSistemaController.class);

    private final RegistrarParametroSistemaUseCase
            registrarParametroSistemaUseCase;

    public ParametroSistemaController(
            RegistrarParametroSistemaUseCase registrarParametroSistemaUseCase) {

        this.registrarParametroSistemaUseCase =
                registrarParametroSistemaUseCase;

    }

    @PostMapping
    public ParametroSistemaResponse registrarParametroSistema(
            @RequestBody ParametroSistemaRequest request) {

        LOGGER.info(
                "Registrando parametro de sistema {}",
                request.codigo()
        );

        ParametroSistema parametro =
                new ParametroSistema();

        parametro.setEmpresaId(
                request.empresaId()
        );

        parametro.setCodigo(
                request.codigo()
        );

        parametro.setNombre(
                request.nombre()
        );

        parametro.setValor(
                request.valor()
        );

        parametro.setDescripcion(
                request.descripcion()
        );

        ParametroSistema guardado =
                registrarParametroSistemaUseCase
                        .ejecutar(parametro);

        return new ParametroSistemaResponse(
                guardado.getIdParametro(),
                guardado.getEmpresaId(),
                guardado.getCodigo(),
                guardado.getNombre(),
                guardado.getValor(),
                guardado.getDescripcion()
        );

    }

}