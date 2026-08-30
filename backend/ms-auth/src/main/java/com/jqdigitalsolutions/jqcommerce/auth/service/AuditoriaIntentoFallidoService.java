package com.jqdigitalsolutions.jqcommerce.auth.service;

import com.jqdigitalsolutions.jqcommerce.auth.dto.AuditoriaIntentoFallidoResponse;
import com.jqdigitalsolutions.jqcommerce.auth.entity.AuditoriaIntentoFallido;
import com.jqdigitalsolutions.jqcommerce.auth.repository.AuditoriaIntentoFallidoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

// Ing_JQC: Servicio de auditoría de intentos fallidos
@Service
public class AuditoriaIntentoFallidoService {

    private final AuditoriaIntentoFallidoRepository auditoriaIntentoFallidoRepository;

    public AuditoriaIntentoFallidoService(AuditoriaIntentoFallidoRepository auditoriaIntentoFallidoRepository) {

        this.auditoriaIntentoFallidoRepository = auditoriaIntentoFallidoRepository;

    }

    // Ing_JQC: Registra intento fallido de autenticación
    public void registrarIntentoFallido(String username,
                                        String direccionIp,
                                        String userAgent) {

        AuditoriaIntentoFallido auditoria =
                AuditoriaIntentoFallido.builder()
                        .username(username)
                        .direccionIp(direccionIp)
                        .userAgent(userAgent)
                        .fechaIntento(LocalDateTime.now())
                        .observacion("Credenciales inválidas")
                        .build();

        auditoriaIntentoFallidoRepository.save(auditoria);

    }

    // Ing_JQC: Obtiene la lista de intentos fallidos
    public List<AuditoriaIntentoFallidoResponse> listarIntentosFallidos() {

        return auditoriaIntentoFallidoRepository.findAll()
                .stream()
                .map(intento ->
                        new AuditoriaIntentoFallidoResponse(
                                intento.getIdIntento(),
                                intento.getUsername(),
                                intento.getDireccionIp(),
                                intento.getUserAgent(),
                                intento.getFechaIntento(),
                                intento.getObservacion()
                        )
                )
                .toList();

    }

}