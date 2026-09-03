package com.jqdigitalsolutions.jqcommerce.configuracion.apdater.input.controller;

import com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.dto.EmpresaRequest;
import com.jqdigitalsolutions.jqcommerce.configuracion.adapter.input.dto.EmpresaResponse;
import com.jqdigitalsolutions.jqcommerce.configuracion.application.service.RegistrarEmpresaUseCase;
import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.Empresa;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Ing_JQC: Controlador de empresas
// Tecnología: Arquitectura Hexagonal
// Finalidad: Exponer API REST de empresas

@RestController
@RequestMapping("/api/v1/empresas")
public class EmpresaController {

    private final RegistrarEmpresaUseCase registrarEmpresaUseCase;

    public EmpresaController(RegistrarEmpresaUseCase registrarEmpresaUseCase) {
        this.registrarEmpresaUseCase = registrarEmpresaUseCase;
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
                empresaGuardada.getEstado()
        );

    }

}
