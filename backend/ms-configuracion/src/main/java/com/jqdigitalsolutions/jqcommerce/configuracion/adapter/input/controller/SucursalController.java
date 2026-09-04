package com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.controller;

import com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.dto.SucursalRequest;
import com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.dto.SucursalResponse;
import com.jqdigitalsolutions.jqcommerce.configuracion.application.service.ListarSucursalesUseCase;
import com.jqdigitalsolutions.jqcommerce.configuracion.application.service.RegistrarSucursalUseCase;
import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.Sucursal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sucursales")
public class SucursalController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SucursalController.class);

    private final RegistrarSucursalUseCase registrarSucursalUseCase;
    private final ListarSucursalesUseCase listarSucursalesUseCase;
    public SucursalController(
            RegistrarSucursalUseCase registrarSucursalUseCase,
            ListarSucursalesUseCase listarSucursalesUseCase) {

        this.registrarSucursalUseCase = registrarSucursalUseCase;
        this.listarSucursalesUseCase = listarSucursalesUseCase;

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
    @GetMapping
    public List<SucursalResponse> listarSucursales() {

        LOGGER.info(
                "Consultando listado de sucursales"
        );

        List<Sucursal> sucursales =
                listarSucursalesUseCase.ejecutar();

        LOGGER.debug(
                "Cantidad de sucursales encontradas: {}",
                sucursales.size()
        );

        return sucursales.stream()
                .map(sucursal -> new SucursalResponse(
                        sucursal.getIdSucursal(),
                        sucursal.getEmpresaId(),
                        sucursal.getCodigo(),
                        sucursal.getNombre(),
                        sucursal.getDireccion(),
                        sucursal.getTelefono(),
                        sucursal.getCorreo(),
                        sucursal.getEstado()
                ))
                .toList();

    }

}