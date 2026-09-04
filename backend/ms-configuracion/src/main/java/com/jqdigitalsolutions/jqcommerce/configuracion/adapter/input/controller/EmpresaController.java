package com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.controller;

import com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.dto.EmpresaRequest;
import com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.dto.EmpresaResponse;
import com.jqdigitalsolutions.jqcommerce.configuracion.application.service.*;
import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.Empresa;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import org.springframework.web.bind.annotation.PatchMapping;

// Ing_JQC: Controlador de empresas
// Tecnología: Arquitectura Hexagonal
// Finalidad: Exponer API REST de empresas

@RestController
@RequestMapping("/api/v1/empresas")
public class EmpresaController {

    private final RegistrarEmpresaUseCase registrarEmpresaUseCase;
    private final ListarEmpresasUseCase listarEmpresasUseCase;
    private final BuscarEmpresaPorIdUseCase buscarEmpresaPorIdUseCase;
    private final ActualizarEmpresaUseCase actualizarEmpresaUseCase;
    private final DesactivarEmpresaUseCase desactivarEmpresaUseCase;
    private final ActivarEmpresaUseCase activarEmpresaUseCase;

    private static final Logger LOGGER =
            LoggerFactory.getLogger(EmpresaController.class);
    public EmpresaController(
            RegistrarEmpresaUseCase registrarEmpresaUseCase,
            ListarEmpresasUseCase listarEmpresasUseCase,
            BuscarEmpresaPorIdUseCase buscarEmpresaPorIdUseCase,
            ActualizarEmpresaUseCase actualizarEmpresaUseCase,
            DesactivarEmpresaUseCase desactivarEmpresaUseCase,
            ActivarEmpresaUseCase activarEmpresaUseCase) {

        this.registrarEmpresaUseCase = registrarEmpresaUseCase;
        this.listarEmpresasUseCase = listarEmpresasUseCase;
        this.buscarEmpresaPorIdUseCase = buscarEmpresaPorIdUseCase;
        this.actualizarEmpresaUseCase=actualizarEmpresaUseCase;
        this.desactivarEmpresaUseCase = desactivarEmpresaUseCase;
        this.activarEmpresaUseCase = activarEmpresaUseCase;
    }


    // Ing_JQC: Registrar empresa
    // Tecnología: Arquitectura Hexagonal
    // Finalidad: Crear nuevas empresas SaaS
    @PostMapping
    public EmpresaResponse registrarEmpresa(@RequestBody EmpresaRequest request) {

        Empresa empresa = new Empresa();
        empresa.setCodigo(request.codigo());
        empresa.setRazonSocial(request.razonSocial());
        empresa.setNombreComercial(request.nombreComercial());
        empresa.setRuc(request.ruc());
        empresa.setDireccion(request.direccion());
        empresa.setTelefono(request.telefono());
        empresa.setCorreo(request.correo());
        empresa.setMonedaPrincipal(request.monedaPrincipal());

        Empresa empresaGuardada =registrarEmpresaUseCase.ejecutar(empresa);

        return new EmpresaResponse(
                empresaGuardada.getIdEmpresa(),
                empresaGuardada.getCodigo(),
                empresaGuardada.getRazonSocial(),
                empresaGuardada.getNombreComercial(),
                empresaGuardada.getRuc(),
                empresaGuardada.getDireccion(),
                empresaGuardada.getTelefono(),
                empresaGuardada.getCorreo(),
                empresaGuardada.getMonedaPrincipal(),
                empresaGuardada.getEstado());

    }


    @GetMapping
    public List<EmpresaResponse> listarEmpresas() {
        System.out.println("CONSULTANDO LISTADO DE EMPRESAS");
        LOGGER.info("Consultando listado de empresas");
        List<Empresa> empresas = listarEmpresasUseCase.ejecutar();

        LOGGER.debug("Cantidad de empresas encontradas: {}", empresas.size());

        return empresas.stream()
                .map(empresa -> new EmpresaResponse(
                        empresa.getIdEmpresa(),
                        empresa.getCodigo(),
                        empresa.getRazonSocial(),
                        empresa.getNombreComercial(),
                        empresa.getRuc(),
                        empresa.getDireccion(),
                        empresa.getTelefono(),
                        empresa.getCorreo(),
                        empresa.getMonedaPrincipal(),
                        empresa.getEstado()
                ))
                .toList();

    }

    @GetMapping("/{id}")
    public EmpresaResponse buscarPorId(@PathVariable Long id) {

        LOGGER.info("Consultando empresa con id {}", id);

        Empresa empresa =
                buscarEmpresaPorIdUseCase
                        .ejecutar(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Empresa no encontrada"
                                ));

        return new EmpresaResponse(
                empresa.getIdEmpresa(),
                empresa.getCodigo(),
                empresa.getRazonSocial(),
                empresa.getNombreComercial(),
                empresa.getRuc(),
                empresa.getDireccion(),
                empresa.getTelefono(),
                empresa.getCorreo(),
                empresa.getMonedaPrincipal(),
                empresa.getEstado()
        );

    }

    @PutMapping("/{id}")
    public EmpresaResponse actualizarEmpresa(
            @PathVariable Long id,
            @RequestBody EmpresaRequest request) {

        LOGGER.info("Actualizando empresa con id {}", id);
        Empresa empresa = new Empresa();

        empresa.setIdEmpresa(id);
        empresa.setCodigo(request.codigo());
        empresa.setRazonSocial(request.razonSocial());
        empresa.setNombreComercial(request.nombreComercial());
        empresa.setRuc(request.ruc());
        empresa.setDireccion(request.direccion());
        empresa.setTelefono(request.telefono());
        empresa.setCorreo(request.correo());
        empresa.setMonedaPrincipal(request.monedaPrincipal());

        Empresa empresaActualizada = actualizarEmpresaUseCase.ejecutar(empresa);
        return new EmpresaResponse(
                empresaActualizada.getIdEmpresa(),
                empresaActualizada.getCodigo(),
                empresaActualizada.getRazonSocial(),
                empresaActualizada.getNombreComercial(),
                empresaActualizada.getRuc(),
                empresaActualizada.getDireccion(),
                empresaActualizada.getTelefono(),
                empresaActualizada.getCorreo(),
                empresaActualizada.getMonedaPrincipal(),
                empresaActualizada.getEstado()
        );
    }

    @PatchMapping("/{id}/desactivar")
    public void desactivarEmpresa(@PathVariable Long id) {
        LOGGER.info("Desactivando empresa con id {}",id);
        desactivarEmpresaUseCase.ejecutar(id);
    }
    @PatchMapping("/{id}/activar")
    public void activarEmpresa(
            @PathVariable Long id) {

        LOGGER.info("Activando empresa con id {}", id);
        activarEmpresaUseCase.ejecutar(id);

    }

}
