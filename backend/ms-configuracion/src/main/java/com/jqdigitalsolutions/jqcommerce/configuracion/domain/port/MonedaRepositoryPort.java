package com.jqdigitalsolutions.jqcommerce.configuracion.domain.port;

import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.Moneda;

import java.util.List;
import java.util.Optional;

// Ing_JQC: Puerto de monedas
// Tecnología: Arquitectura Hexagonal
// Finalidad: Desacoplar dominio de persistencia

public interface MonedaRepositoryPort {

    Moneda guardar(Moneda moneda);

    List<Moneda> listar();

    Optional<Moneda> buscarPorId(Long idMoneda);

}