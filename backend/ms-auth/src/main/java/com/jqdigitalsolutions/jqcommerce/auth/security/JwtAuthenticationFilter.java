package com.jqdigitalsolutions.jqcommerce.auth.security;

import com.jqdigitalsolutions.jqcommerce.auth.entity.*;
import com.jqdigitalsolutions.jqcommerce.auth.repository.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.ArrayList;

@Component
public class JwtAuthenticationFilter   extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioRolRepository usuarioRolRepository;
    private final RolRepository rolRepository;

    private final RolPermisoRepository rolPermisoRepository;
    private final PermisoRepository permisoRepository;
    public JwtAuthenticationFilter(
            JwtService jwtService,
            UsuarioRepository usuarioRepository,
            UsuarioRolRepository usuarioRolRepository,
            RolRepository rolRepository,
            RolPermisoRepository rolPermisoRepository,
            PermisoRepository permisoRepository) {

        this.jwtService = jwtService;
        this.usuarioRepository = usuarioRepository;
        this.usuarioRolRepository = usuarioRolRepository;
        this.rolRepository = rolRepository;
        this.rolPermisoRepository = rolPermisoRepository;
        this.permisoRepository = permisoRepository;
    }

// Ing_JQC: Valida y registra autenticación basada en JWT
@Override
protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain)
        throws ServletException, IOException {

    try {

        String authHeader = request.getHeader("Authorization");

        System.out.println("TOKEN RECIBIDO: " + authHeader);

        if (authHeader == null ||  !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token =  authHeader.substring(7);
        String username =    jwtService.extractUsername(token);
        System.out.println("USUARIO TOKEN: " + username);

        // Ing_JQC: Obtiene usuario autenticado
        Usuario usuario = usuarioRepository
                .findByUsername(username)
                .orElseThrow();

        // Ing_JQC: Obtiene rol asociado al usuario
        UsuarioRol usuarioRol =
                usuarioRolRepository
                        .findByUsuarioId(
                                usuario.getIdUsuario()
                        )
                        .stream()
                        .findFirst()
                        .orElseThrow();

                    // Ing_JQC: Obtiene información del rol
                    Rol rol = rolRepository
                            .findById(
                                    usuarioRol.getRolId()
                            )
                            .orElseThrow();


                    // Ing_JQC: Lista de authorities a registrar en Spring Security
                    List<SimpleGrantedAuthority> authorities =
                            new ArrayList<>();

            // Ing_JQC: Agrega el rol del usuario
                    authorities.add(
                            new SimpleGrantedAuthority(
                                    "ROLE_" + rol.getCodigo()
                            )
                    );

            // Ing_JQC: Obtiene permisos asociados al rol
                    List<RolPermiso> rolPermisos =
                            rolPermisoRepository.findByRolId(
                                    rol.getIdRol()
                            );

            // Ing_JQC: Agrega permisos como authorities
                    for (RolPermiso rolPermiso : rolPermisos) {
                        Permiso permiso =
                                permisoRepository
                                        .findById(
                                                rolPermiso.getPermisoId()
                                        )
                                        .orElseThrow();

                        authorities.add(
                                new SimpleGrantedAuthority(
                                        permiso.getCodigo()
                                )
                        );
                    }



        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        authorities
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);

    } catch (Exception e) {
        System.out.println("ERROR JWT: " + e.getMessage());
    }
    filterChain.doFilter(request, response);
}
}