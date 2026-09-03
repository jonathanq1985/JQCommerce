package com.jqdigitalsolutions.jqcommerce.configuracion.infrastructure.repository;

import com.jqdigitalsolutions.jqcommerce.configuracion.infrastructure.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Ing_JQC: Repositorio JPA de empresas
// Tecnología: Spring Data JPA
// Finalidad: Acceso a datos de empresas

@Repository
public interface EmpresaJpaRepository  extends JpaRepository<EmpresaEntity, Long> {

}