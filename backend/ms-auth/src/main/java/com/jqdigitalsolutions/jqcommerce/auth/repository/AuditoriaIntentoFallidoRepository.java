package com.jqdigitalsolutions.jqcommerce.auth.repository;

import com.jqdigitalsolutions.jqcommerce.auth.entity.AuditoriaIntentoFallido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Ing_JQC: Acceso a datos de intentos fallidos
@Repository
public interface AuditoriaIntentoFallidoRepository
        extends JpaRepository<AuditoriaIntentoFallido, Long> {
}
