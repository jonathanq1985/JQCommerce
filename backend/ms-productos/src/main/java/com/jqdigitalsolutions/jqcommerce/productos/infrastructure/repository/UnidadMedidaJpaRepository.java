package com.jqdigitalsolutions.jqcommerce.productos.infrastructure.repository;

import com.jqdigitalsolutions.jqcommerce.productos.infrastructure.entity.UnidadMedidaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadMedidaJpaRepository
        extends JpaRepository<UnidadMedidaEntity, Long> {

}