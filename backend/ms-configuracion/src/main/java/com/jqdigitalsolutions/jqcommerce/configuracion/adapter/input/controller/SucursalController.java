package com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.controller;

import com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.dto.SucursalRequest;
import com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.dto.SucursalResponse;
import com.jqdigitalsolutions.jqcommerce.configuracion.application.service.RegistrarSucursalUseCase;
import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.Sucursal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sucursales")
public class SucursalController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SucursalController.class);

    private final RegistrarSucursalUseCase registrarSucursalUseCase;
    public SucursalController(
            RegistrarSucursalUseCase registrarSucursalUseCase) {

        this.registrarSucursalUseCase = registrarSucursalUseCase;

    }

    @PostMapping
    public SucursalResponse registrarSucursal(
            @RequestBody SucursalRequest request) {

        LOGGER.info(
                "Registrando sucursal {}",
                request.nombre()
        );

        Sucursal sucursal = new Sucursal();

        sucursal.setEmpresaId(request.empresaId());
        sucursal.setCodigo(request.codigo());
        sucursal.setNombre(request.nombre());
        sucursal.setDireccion(request.direccion());
        sucursal.setTelefono(request.telefono());
        sucursal.setCorreo(request.correo());

        Sucursal sucursalGuardada =
                registrarSucursalUseCase.ejecutar(sucursal);

        return new SucursalResponse(
                sucursalGuardada.getIdSucursal(),
                sucursalGuardada.getEmpresaId(),
                sucursalGuardada.getCodigo(),
                sucursalGuardada.getNombre(),
                sucursalGuardada.getDireccion(),
                sucursalGuardada.getTelefono(),
                sucursalGuardada.getCorreo(),
                sucursalGuardada.getEstado()
        );

    }

}