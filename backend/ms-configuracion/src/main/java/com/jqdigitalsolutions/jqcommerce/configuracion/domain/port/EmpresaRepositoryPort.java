package com.jqdigitalsolutions.jqcommerce.configuracion.domain.port;

import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.Empresa;

import java.util.List;
import java.util.Optional;

// Ing_JQC: Puerto para persistencia de empresas
// Tecnología: Arquitectura Hexagonal
// Finalidad: Desacoplar dominio de base de datos

public interface EmpresaRepositoryPort {
    Empresa guardar(Empresa empresa);
    List<Empresa> listar();
    Optional<Empresa> buscarPorId(Long idEmpresa);
    Empresa actualizar(Empresa empresa);
    void desactivar(Long idEmpresa);
    // Ing_JQC: Activar empresa
// Tecnología: Arquitectura Hexagonal
// Finalidad: Reactivar una empresa previamente deshabilitada

    void activar(Long idEmpresa);

}