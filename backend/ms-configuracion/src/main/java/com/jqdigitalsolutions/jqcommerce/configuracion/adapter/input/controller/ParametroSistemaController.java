package com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.controller;

import com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.dto.ParametroSistemaRequest;
import com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.dto.ParametroSistemaResponse;
import com.jqdigitalsolutions.jqcommerce.configuracion.application.service.ListarParametroSistemaUseCase;
import com.jqdigitalsolutions.jqcommerce.configuracion.application.service.RegistrarParametroSistemaUseCase;
import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.ParametroSistema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parametros-sistema")
public class ParametroSistemaController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(
                    ParametroSistemaController.class);

    private final RegistrarParametroSistemaUseCase
            registrarParametroSistemaUseCase;
    private final ListarParametroSistemaUseCase listarParametroSistemaUseCase;
    //contructor
    public ParametroSistemaController(
            RegistrarParametroSistemaUseCase registrarParametroSistemaUseCase,
            ListarParametroSistemaUseCase listarParametroSistemaUseCase) {

        this.registrarParametroSistemaUseCase =
                registrarParametroSistemaUseCase;

        this.listarParametroSistemaUseCase =
                listarParametroSistemaUseCase;

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
    @GetMapping
    public List<ParametroSistemaResponse> listarParametrosSistema() {

        LOGGER.info(
                "Consultando listado de parametros del sistema"
        );

        List<ParametroSistema> parametros =
                listarParametroSistemaUseCase.ejecutar();

        return parametros.stream()
                .map(parametro ->
                        new ParametroSistemaResponse(
                                parametro.getIdParametro(),
                                parametro.getEmpresaId(),
                                parametro.getCodigo(),
                                parametro.getNombre(),
                                parametro.getValor(),
                                parametro.getDescripcion()
                        )
                )
                .toList();

    }

}