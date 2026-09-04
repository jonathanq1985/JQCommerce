package com.jqdigitalsolutions.jqcommerce.configuracion.infrastructure.adapter;

import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.Sucursal;
import com.jqdigitalsolutions.jqcommerce.configuracion.domain.port.SucursalRepositoryPort;
import com.jqdigitalsolutions.jqcommerce.configuracion.infrastructure.entity.SucursalEntity;
import com.jqdigitalsolutions.jqcommerce.configuracion.infrastructure.repository.SucursalJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
// Ing_JQC: Adaptador de persistencia de sucursales
// Tecnología: Arquitectura Hexagonal
// Finalidad: Conectar dominio con PostgreSQL

@Component
public class SucursalRepositoryAdapter      implements SucursalRepositoryPort {
    private final SucursalJpaRepository sucursalJpaRepository;

    public SucursalRepositoryAdapter( SucursalJpaRepository sucursalJpaRepository) {
        this.sucursalJpaRepository = sucursalJpaRepository;
    }

    @Override
    public Sucursal guardar(Sucursal sucursal) {

        SucursalEntity entity = SucursalEntity.builder()
                .empresaId(sucursal.getEmpresaId())
                .codigo(sucursal.getCodigo())
                .nombre(sucursal.getNombre())
                .direccion(sucursal.getDireccion())
                .telefono(sucursal.getTelefono())
                .correo(sucursal.getCorreo())
                .estado(sucursal.getEstado())
                .build();

        SucursalEntity guardada = sucursalJpaRepository.save(entity);
        sucursal.setIdSucursal(guardada.getIdSucursal());
        return sucursal;

    }

    @Override
    public List<Sucursal> listar() {
        return sucursalJpaRepository.findAll()
                .stream()
                .map(entity -> new Sucursal(
                        entity.getIdSucursal(),
                        entity.getEmpresaId(),
                        entity.getCodigo(),
                        entity.getNombre(),
                        entity.getDireccion(),
                        entity.getTelefono(),
                        entity.getCorreo(),
                        entity.getEstado()
                ))
                .toList();
    }

    @Override
    public Optional<Sucursal> buscarPorId(Long idSucursal) {

        return sucursalJpaRepository
                .findById(idSucursal)
                .map(entity -> new Sucursal(
                        entity.getIdSucursal(),
                        entity.getEmpresaId(),
                        entity.getCodigo(),
                        entity.getNombre(),
                        entity.getDireccion(),
                        entity.getTelefono(),
                        entity.getCorreo(),
                        entity.getEstado()
                ));

    }

}