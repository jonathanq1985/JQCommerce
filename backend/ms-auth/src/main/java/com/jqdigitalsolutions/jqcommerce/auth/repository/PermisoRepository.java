package com.jqdigitalsolutions.jqcommerce.auth.repository;

import com.jqdigitalsolutions.jqcommerce.auth.entity.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Ing_JQC: Acceso a datos de permisos
@Repository
public interface PermisoRepository
        extends JpaRepository<Permiso, Long> {

    // Ing_JQC: Obtiene un permiso por id
    Optional<Permiso> findById(Long id);

}