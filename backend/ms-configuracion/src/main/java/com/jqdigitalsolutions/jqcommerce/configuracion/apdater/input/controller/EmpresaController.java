package com.jqdigitalsolutions.jqcommerce.configuracion.apdater.input.controller;

import com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.dto.EmpresaRequest;
import com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.dto.EmpresaResponse;
import com.jqdigitalsolutions.jqcommerce.configuracion.application.service.ListarEmpresasUseCase;
import com.jqdigitalsolutions.jqcommerce.configuracion.application.service.RegistrarEmpresaUseCase;
import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.Empresa;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

// Ing_JQC: Controlador de empresas
// Tecnología: Arquitectura Hexagonal
// Finalidad: Exponer API REST de empresas

@RestController
@RequestMapping("/api/v1/empresas")
public class EmpresaController {

    private final RegistrarEmpresaUseCase registrarEmpresaUseCase;
    private final ListarEmpresasUseCase listarEmpresasUseCase;
    private static final Logger LOGGER =
            LoggerFactory.getLogger(EmpresaController.class);
    public EmpresaController(
            RegistrarEmpresaUseCase registrarEmpresaUseCase,
            ListarEmpresasUseCase listarEmpresasUseCase) {

        this.registrarEmpresaUseCase = registrarEmpresaUseCase;
        this.listarEmpresasUseCase = listarEmpresasUseCase;

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


}
