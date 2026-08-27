package com.jqdigitalsolutions.jqcommerce.auth.repository;

import com.jqdigitalsolutions.jqcommerce.auth.entity.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Ing_JQC: Acceso a datos de la relación usuario rol
@Repository
public interface UsuarioRolRepository
        extends JpaRepository<UsuarioRol, Long> {

}