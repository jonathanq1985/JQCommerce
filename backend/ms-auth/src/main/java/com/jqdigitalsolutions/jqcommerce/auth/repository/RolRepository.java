package com.jqdigitalsolutions.jqcommerce.auth.repository;

import com.jqdigitalsolutions.jqcommerce.auth.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
// Ing_JQC: Acceso a datos de roles
@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    // Ing_JQC: Obtiene un rol por id

    Optional<Rol> findById(Long id);
}