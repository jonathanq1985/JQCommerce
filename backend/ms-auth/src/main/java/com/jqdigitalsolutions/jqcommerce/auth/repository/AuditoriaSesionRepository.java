package com.jqdigitalsolutions.jqcommerce.auth.repository;

import com.jqdigitalsolutions.jqcommerce.auth.entity.AuditoriaSesion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

// Ing_JQC: Acceso a datos de auditoría de sesiones
@Repository
public interface AuditoriaSesionRepository
        extends JpaRepository<AuditoriaSesion, Long> {

    // Ing_JQC: Obtiene auditorías por usuario
    List<AuditoriaSesion> findByUsuarioId(Long usuarioId);
    // Ing_JQC: Obtiene la última sesión activa del usuario
    Optional<AuditoriaSesion>findFirstByUsuarioIdAndEstadoOrderByIdAuditoriaDesc( Long usuarioId, String estado);
}