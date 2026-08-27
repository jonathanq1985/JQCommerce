package com.jqdigitalsolutions.jqcommerce.auth.repository;

import com.jqdigitalsolutions.jqcommerce.auth.entity.RolPermiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Ing_JQC: Acceso a datos de la relación rol permiso
@Repository
public interface RolPermisoRepository
        extends JpaRepository<RolPermiso, Long> {

}