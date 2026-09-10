package com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.controller;

import com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.dto.TipoCambioRequest;
import com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.dto.TipoCambioResponse;
import com.jqdigitalsolutions.jqcommerce.configuracion.application.service.ListarTipoCambioUseCase;
import com.jqdigitalsolutions.jqcommerce.configuracion.application.service.RegistrarTipoCambioUseCase;
import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.TipoCambio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tipos-cambio")
public class TipoCambioController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(TipoCambioController.class);

    private final RegistrarTipoCambioUseCase registrarTipoCambioUseCase;
    private final ListarTipoCambioUseCase listarTipoCambioUseCase;
    public TipoCambioController(
            RegistrarTipoCambioUseCase registrarTipoCambioUseCase,
            ListarTipoCambioUseCase listarTipoCambioUseCase) {

        this.registrarTipoCambioUseCase = registrarTipoCambioUseCase;
        this.listarTipoCambioUseCase = listarTipoCambioUseCase;

    }

    @PostMapping
    public TipoCambioResponse registrarTipoCambio(
            @RequestBody TipoCambioRequest request) {

        LOGGER.info(
                "Registrando tipo de cambio {} -> {}",
                request.monedaOrigenId(),
                request.monedaDestinoId()
        );

        TipoCambio tipoCambio = new TipoCambio();

        tipoCambio.setMonedaOrigenId(
                request.monedaOrigenId()
        );

        tipoCambio.setMonedaDestinoId(
                request.monedaDestinoId()
        );

        tipoCambio.setValor(
                request.valor()
        );

        tipoCambio.setFechaVigencia(
                request.fechaVigencia()
        );

        TipoCambio tipoCambioGuardado =  registrarTipoCambioUseCase.ejecutar(tipoCambio);
        return new TipoCambioResponse(
                tipoCambioGuardado.getIdTipoCambio(),
                tipoCambioGuardado.getMonedaOrigenId(),
                tipoCambioGuardado.getMonedaDestinoId(),
                tipoCambioGuardado.getValor(),
                tipoCambioGuardado.getFechaVigencia()
        );

    }
    @GetMapping
    public List<TipoCambioResponse> listarTiposCambio() {

        LOGGER.info(
                "Consultando listado de tipos de cambio"
        );

        List<TipoCambio> tiposCambio = listarTipoCambioUseCase.ejecutar();
        return tiposCambio.stream()
                .map(tipoCambio -> new TipoCambioResponse(
                        tipoCambio.getIdTipoCambio(),
                        tipoCambio.getMonedaOrigenId(),
                        tipoCambio.getMonedaDestinoId(),
                        tipoCambio.getValor(),
                        tipoCambio.getFechaVigencia()
                ))
                .toList();

    }

}