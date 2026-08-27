package com.jqdigitalsolutions.jqcommerce.auth.service;

import com.jqdigitalsolutions.jqcommerce.auth.dto.PermisoResponse;
import com.jqdigitalsolutions.jqcommerce.auth.repository.PermisoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Ing_JQC: Servicio de consulta de permisos
@Service
public class PermisoService {

    private final PermisoRepository permisoRepository;

    public PermisoService(
            PermisoRepository permisoRepository) {

        this.permisoRepository = permisoRepository;
    }

    // Ing_JQC: Obtiene todos los permisos registrados
    public List<PermisoResponse> listarPermisos() {

        return permisoRepository.findAll()
                .stream()
                .map(permiso ->
                        new PermisoResponse(
                                permiso.getIdPermiso(),
                                permiso.getCodigo(),
                                permiso.getNombre(),
                                permiso.getDescripcion(),
                                permiso.getEstado()
                        )
                )
                .toList();
    }
}