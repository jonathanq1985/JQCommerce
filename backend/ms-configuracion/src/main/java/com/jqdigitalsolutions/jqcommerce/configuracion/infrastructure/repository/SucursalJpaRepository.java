package com.jqdigitalsolutions.jqcommerce.configuracion.infrastructure.repository;

import com.jqdigitalsolutions.jqcommerce.configuracion.infrastructure.entity.SucursalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Ing_JQC: Repositorio JPA de sucursales
// Tecnología: Spring Data JPA
// Finalidad: Acceso a datos de sucursales

@Repository
public interface SucursalJpaRepository extends JpaRepository<SucursalEntity, Long> {

}