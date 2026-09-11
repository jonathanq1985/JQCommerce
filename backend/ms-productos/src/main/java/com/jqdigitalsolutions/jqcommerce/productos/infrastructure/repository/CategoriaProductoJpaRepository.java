package com.jqdigitalsolutions.jqcommerce.productos.infrastructure.repository;

import com.jqdigitalsolutions.jqcommerce.productos.infrastructure.entity.CategoriaProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaProductoJpaRepository
        extends JpaRepository<CategoriaProductoEntity, Long> {

}