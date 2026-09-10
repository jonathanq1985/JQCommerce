package com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.controller;

import com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.dto.MonedaRequest;
import com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.dto.MonedaResponse;
import com.jqdigitalsolutions.jqcommerce.configuracion.application.service.RegistrarMonedaUseCase;
import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.Moneda;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/monedas")
public class MonedaController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(MonedaController.class);

    private final RegistrarMonedaUseCase registrarMonedaUseCase;

    public MonedaController(
            RegistrarMonedaUseCase registrarMonedaUseCase) {

        this.registrarMonedaUseCase = registrarMonedaUseCase;

    }

    @PostMapping
    public MonedaResponse registrarMoneda(
            @RequestBody MonedaRequest request) {

        LOGGER.info("Registrando moneda {}", request.codigo());

        Moneda moneda = new Moneda();

        moneda.setCodigo(request.codigo());
        moneda.setNombre(request.nombre());
        moneda.setSimbolo(request.simbolo());

        Moneda monedaGuardada = registrarMonedaUseCase.ejecutar(moneda);

        return new MonedaResponse(
                monedaGuardada.getIdMoneda(),
                monedaGuardada.getCodigo(),
                monedaGuardada.getNombre(),
                monedaGuardada.getSimbolo(),
                monedaGuardada.getEstado()
        );

    }

}