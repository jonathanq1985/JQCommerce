package com.jqdigitalsolutions.jqcommerce.configuracion.domain.port;

import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.Sucursal;

import java.util.List;
import java.util.Optional;

// Ing_JQC: Puerto de sucursales
// Tecnología: Arquitectura Hexagonal
// Finalidad: Desacoplar dominio de persistencia

public interface SucursalRepositoryPort {

    Sucursal guardar(Sucursal sucursal);
    List<Sucursal> listar();
    Optional<Sucursal> buscarPorId(Long idSucursal);
    Sucursal actualizar(Sucursal sucursal);
    void desactivar(Long idSucursal);
}