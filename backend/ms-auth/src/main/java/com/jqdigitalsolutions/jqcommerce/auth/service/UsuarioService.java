package com.jqdigitalsolutions.jqcommerce.auth.service;

import com.jqdigitalsolutions.jqcommerce.auth.dto.UsuarioResponse;
import com.jqdigitalsolutions.jqcommerce.auth.entity.Usuario;
import com.jqdigitalsolutions.jqcommerce.auth.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioResponse> listarUsuarios() {

        return usuarioRepository.findAll()
                .stream()
                .map(usuario ->
                        new UsuarioResponse(
                                usuario.getIdUsuario(),
                                usuario.getEmpresaId(),
                                usuario.getUsername(),
                                usuario.getNombres(),
                                usuario.getApellidos(),
                                usuario.getCorreo(),
                                usuario.getEstado()
                        )

                )
                .toList();
    }
}