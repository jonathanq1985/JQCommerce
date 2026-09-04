package com.jqdigitalsolutions.jqcommerce.configuracion.infrastructure.repository;

import com.jqdigitalsolutions.jqcommerce.configuracion.infrastructure.entity.MonedaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Ing_JQC: Repositorio JPA de monedas
// Tecnología: Spring Data JPA
// Finalidad: Acceso a datos de monedas

@Repository
public interface MonedaJpaRepository
        extends JpaRepository<MonedaEntity, Long> {

}