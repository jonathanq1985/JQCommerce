package com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.controller;

import com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.dto.SucursalRequest;
import com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.dto.SucursalResponse;
import com.jqdigitalsolutions.jqcommerce.configuracion.application.service.*;
import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.Sucursal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
@RestController
@RequestMapping("/api/v1/sucursales")
public class SucursalController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SucursalController.class);

    private final RegistrarSucursalUseCase registrarSucursalUseCase;
    private final ListarSucursalesUseCase listarSucursalesUseCase;
    private final BuscarSucursalPorIdUseCase buscarSucursalPorIdUseCase;
    private final ActualizarSucursalUseCase actualizarSucursalUseCase;
    private final DesactivarSucursalUseCase desactivarSucursalUseCase;
    public SucursalController(
            RegistrarSucursalUseCase registrarSucursalUseCase,
            ListarSucursalesUseCase listarSucursalesUseCase,
            BuscarSucursalPorIdUseCase buscarSucursalPorIdUseCase,
            ActualizarSucursalUseCase actualizarSucursalUseCase,
            DesactivarSucursalUseCase desactivarSucursalUseCase) {

        this.registrarSucursalUseCase = registrarSucursalUseCase;
        this.listarSucursalesUseCase = listarSucursalesUseCase;
        this.buscarSucursalPorIdUseCase=buscarSucursalPorIdUseCase;
        this.actualizarSucursalUseCase=actualizarSucursalUseCase;
        this.desactivarSucursalUseCase = desactivarSucursalUseCase;

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
    @GetMapping("/{id}")
    public SucursalResponse buscarPorId(
            @PathVariable Long id) {

        LOGGER.info("Consultando sucursal con id {}", id);

        Sucursal sucursal =
                buscarSucursalPorIdUseCase
                        .ejecutar(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Sucursal no encontrada"
                                )
                        );
        return new SucursalResponse(
                sucursal.getIdSucursal(),
                sucursal.getEmpresaId(),
                sucursal.getCodigo(),
                sucursal.getNombre(),
                sucursal.getDireccion(),
                sucursal.getTelefono(),
                sucursal.getCorreo(),
                sucursal.getEstado()
        );
    }
    @PutMapping("/{id}")
    public SucursalResponse actualizarSucursal(
            @PathVariable Long id,
            @RequestBody SucursalRequest request) {

        LOGGER.info("Actualizando sucursal con id {}", id);

        Sucursal sucursal = new Sucursal();

        sucursal.setIdSucursal(id);
        sucursal.setEmpresaId(request.empresaId());
        sucursal.setCodigo(request.codigo());
        sucursal.setNombre(request.nombre());
        sucursal.setDireccion(request.direccion());
        sucursal.setTelefono(request.telefono());
        sucursal.setCorreo(request.correo());

        Sucursal sucursalActualizada =
                actualizarSucursalUseCase.ejecutar(sucursal);

        return new SucursalResponse(
                sucursalActualizada.getIdSucursal(),
                sucursalActualizada.getEmpresaId(),
                sucursalActualizada.getCodigo(),
                sucursalActualizada.getNombre(),
                sucursalActualizada.getDireccion(),
                sucursalActualizada.getTelefono(),
                sucursalActualizada.getCorreo(),
                sucursalActualizada.getEstado()
        );

    }

    @PatchMapping("/{id}/desactivar")
    public void desactivarSucursal(@PathVariable Long id) {

        LOGGER.info("Desactivando sucursal con id {}",
                id);
        desactivarSucursalUseCase.ejecutar(id);

    }

}