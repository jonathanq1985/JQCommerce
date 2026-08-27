package com.jqdigitalsolutions.jqcommerce.auth.service;

import com.jqdigitalsolutions.jqcommerce.auth.dto.RolResponse;
import com.jqdigitalsolutions.jqcommerce.auth.repository.RolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Ing_JQC: Servicio de consulta y gestión de roles
@Service
public class RolService {

    private final RolRepository rolRepository;

    public RolService( RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }
    // Ing_JQC: Lista todos los roles registrados
    public List<RolResponse> listarRoles() {

        return rolRepository.findAll()
                .stream()
                .map(rol ->

                        new RolResponse(
                                rol.getIdRol(),
                                rol.getEmpresaId(),
                                rol.getCodigo(),
                                rol.getNombre(),
                                rol.getDescripcion(),
                                rol.getEstado()
                        )

                )
                .toList();
    }

}