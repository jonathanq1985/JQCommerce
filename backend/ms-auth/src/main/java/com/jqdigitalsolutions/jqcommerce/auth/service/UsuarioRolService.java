package com.jqdigitalsolutions.jqcommerce.auth.service;

import com.jqdigitalsolutions.jqcommerce.auth.dto.UsuarioRolResponse;
import com.jqdigitalsolutions.jqcommerce.auth.repository.UsuarioRolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Ing_JQC: Servicio de consulta de relaciones usuario rol
@Service
public class UsuarioRolService {

    private final UsuarioRolRepository usuarioRolRepository;

    public UsuarioRolService(UsuarioRolRepository usuarioRolRepository) {
        this.usuarioRolRepository = usuarioRolRepository;
    }

    // Ing_JQC: Obtiene todas las relaciones usuario rol
    public List<UsuarioRolResponse> listarUsuarioRoles() {

        return usuarioRolRepository.findAll()
                .stream()
                .map(usuarioRol ->
                        new UsuarioRolResponse(
                                usuarioRol.getIdUsuarioRol(),
                                usuarioRol.getUsuarioId(),
                                usuarioRol.getRolId(),
                                usuarioRol.getEstado()
                        )
                )
                .toList();
    }
}