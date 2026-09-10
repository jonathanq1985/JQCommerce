package com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.controller;

import com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.dto.MonedaRequest;
import com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.dto.MonedaResponse;
import com.jqdigitalsolutions.jqcommerce.configuracion.application.service.BuscarMonedaPorIdUseCase;
import com.jqdigitalsolutions.jqcommerce.configuracion.application.service.ListarMonedasUseCase;
import com.jqdigitalsolutions.jqcommerce.configuracion.application.service.RegistrarMonedaUseCase;
import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.Moneda;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/monedas")
public class MonedaController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(MonedaController.class);

    private final RegistrarMonedaUseCase registrarMonedaUseCase;
    private final ListarMonedasUseCase listarMonedasUseCase;
    private final BuscarMonedaPorIdUseCase buscarMonedaPorIdUseCase;
    public MonedaController(
            RegistrarMonedaUseCase registrarMonedaUseCase,
            ListarMonedasUseCase listarMonedasUseCase,
            BuscarMonedaPorIdUseCase buscarMonedaPorIdUseCase) {

        this.registrarMonedaUseCase = registrarMonedaUseCase;
        this.listarMonedasUseCase = listarMonedasUseCase;
        this.buscarMonedaPorIdUseCase = buscarMonedaPorIdUseCase;

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
    @GetMapping
    public List<MonedaResponse> listarMonedas() {

        LOGGER.info("Consultando listado de monedas");
        List<Moneda> monedas = listarMonedasUseCase.ejecutar();
        LOGGER.debug("Cantidad de monedas encontradas: {}",monedas.size());

        return monedas.stream()
                .map(moneda -> new MonedaResponse(
                        moneda.getIdMoneda(),
                        moneda.getCodigo(),
                        moneda.getNombre(),
                        moneda.getSimbolo(),
                        moneda.getEstado()
                ))
                .toList();

    }
    @GetMapping("/{id}")
    public MonedaResponse buscarPorId(
            @PathVariable Long id) {

        LOGGER.info(
                "Consultando moneda con id {}",
                id
        );

        Moneda moneda =
                buscarMonedaPorIdUseCase
                        .ejecutar(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Moneda no encontrada"
                                )
                        );

        return new MonedaResponse(
                moneda.getIdMoneda(),
                moneda.getCodigo(),
                moneda.getNombre(),
                moneda.getSimbolo(),
                moneda.getEstado()
        );

    }
}