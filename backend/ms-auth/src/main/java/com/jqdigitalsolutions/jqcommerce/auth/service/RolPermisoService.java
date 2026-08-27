package com.jqdigitalsolutions.jqcommerce.auth.service;

import com.jqdigitalsolutions.jqcommerce.auth.dto.RolPermisoResponse;
import com.jqdigitalsolutions.jqcommerce.auth.repository.RolPermisoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Ing_JQC: Servicio de consulta de roles y permisos
@Service
public class RolPermisoService {

    private final RolPermisoRepository rolPermisoRepository;

    public RolPermisoService(RolPermisoRepository rolPermisoRepository) {

        this.rolPermisoRepository = rolPermisoRepository;
    }

    // Ing_JQC: Obtiene todas las relaciones rol permiso
    public List<RolPermisoResponse> listarRolPermisos() {

        return rolPermisoRepository.findAll()
                .stream()
                .map(rolPermiso ->
                        new RolPermisoResponse(
                                rolPermiso.getIdRolPermiso(),
                                rolPermiso.getRolId(),
                                rolPermiso.getPermisoId(),
                                rolPermiso.getEstado()
                        )
                )
                .toList();
    }
}