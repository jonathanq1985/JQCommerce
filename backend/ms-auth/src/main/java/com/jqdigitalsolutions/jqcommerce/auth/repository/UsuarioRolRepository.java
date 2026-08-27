package com.jqdigitalsolutions.jqcommerce.auth.repository;

import com.jqdigitalsolutions.jqcommerce.auth.entity.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

// Ing_JQC: Acceso a datos de la relación usuario rol
@Repository
public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, Long> {

    // Ing_JQC: Obtiene roles asociados a un usuario
    List<UsuarioRol> findByUsuarioId(Long usuarioId);

}