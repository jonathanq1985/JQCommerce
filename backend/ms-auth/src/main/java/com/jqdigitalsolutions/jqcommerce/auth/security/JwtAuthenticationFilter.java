package com.jqdigitalsolutions.jqcommerce.auth.security;

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

@Component
public class JwtAuthenticationFilter   extends OncePerRequestFilter {

    private final JwtService jwtService;
    public JwtAuthenticationFilter(
            JwtService jwtService) {

        this.jwtService = jwtService;
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

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        Collections.emptyList()
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);

    } catch (Exception e) {
        System.out.println("ERROR JWT: " + e.getMessage());
    }
    filterChain.doFilter(request, response);
}
}