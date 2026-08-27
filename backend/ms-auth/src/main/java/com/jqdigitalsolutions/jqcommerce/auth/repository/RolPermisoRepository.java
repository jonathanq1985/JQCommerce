package com.jqdigitalsolutions.jqcommerce.auth.repository;

import com.jqdigitalsolutions.jqcommerce.auth.entity.RolPermiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Ing_JQC: Acceso a datos de la relación rol permiso
@Repository
public interface RolPermisoRepository
        extends JpaRepository<RolPermiso, Long> {

    // Ing_JQC: Obtiene permisos asociados a un rol
    List<RolPermiso> findByRolId(Long rolId);

}