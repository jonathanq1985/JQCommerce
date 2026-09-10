package com.jqdigitalsolutions.jqcommerce.configuracion.infrastructure.repository;

import com.jqdigitalsolutions.jqcommerce.configuracion.infrastructure.entity.TipoCambioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoCambioJpaRepository
        extends JpaRepository<TipoCambioEntity, Long> {

}