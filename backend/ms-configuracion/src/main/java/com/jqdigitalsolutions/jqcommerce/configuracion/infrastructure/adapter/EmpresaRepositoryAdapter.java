package com.jqdigitalsolutions.jqcommerce.configuracion.infrastructure.adapter;

import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.Empresa;
import com.jqdigitalsolutions.jqcommerce.configuracion.domain.port.EmpresaRepositoryPort;
import com.jqdigitalsolutions.jqcommerce.configuracion.infrastructure.entity.EmpresaEntity;
import com.jqdigitalsolutions.jqcommerce.configuracion.infrastructure.repository.EmpresaJpaRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

import static org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties.UiService.LOGGER;

// Ing_JQC: Adaptador de persistencia de empresas
// Tecnología: Arquitectura Hexagonal
// Finalidad: Conectar el dominio con PostgreSQL

@Component
public class EmpresaRepositoryAdapter implements EmpresaRepositoryPort {

    private final EmpresaJpaRepository empresaJpaRepository;

    public EmpresaRepositoryAdapter(EmpresaJpaRepository empresaJpaRepository) {
        this.empresaJpaRepository = empresaJpaRepository;
    }

    @Override
    public Empresa guardar(Empresa empresa) {

        EmpresaEntity entity = EmpresaEntity.builder()
                .codigo(empresa.getCodigo())
                .razonSocial(empresa.getRazonSocial())
                .nombreComercial(empresa.getNombreComercial())
                .ruc(empresa.getRuc())
                .direccion(empresa.getDireccion())
                .telefono(empresa.getTelefono())
                .correo(empresa.getCorreo())
                .monedaPrincipal(empresa.getMonedaPrincipal())
                .estado(empresa.getEstado())
                .build();

        EmpresaEntity guardada =
                empresaJpaRepository.save(entity);

        empresa.setIdEmpresa(
                guardada.getIdEmpresa()
        );

        return empresa;

    }

    @Override
    public List<Empresa> listar() {
        return empresaJpaRepository.findAll()
                .stream()
                .map(entity -> new Empresa(
                        entity.getIdEmpresa(),
                        entity.getCodigo(),
                        entity.getRazonSocial(),
                        entity.getNombreComercial(),
                        entity.getRuc(),
                        entity.getDireccion(),
                        entity.getTelefono(),
                        entity.getCorreo(),
                        entity.getMonedaPrincipal(),
                        entity.getEstado()
                ))
                .toList();

    }

    @Override
    public Optional<Empresa> buscarPorId(Long idEmpresa) {
        return empresaJpaRepository
                .findById(idEmpresa)
                .map(entity -> new Empresa(
                        entity.getIdEmpresa(),
                        entity.getCodigo(),
                        entity.getRazonSocial(),
                        entity.getNombreComercial(),
                        entity.getRuc(),
                        entity.getDireccion(),
                        entity.getTelefono(),
                        entity.getCorreo(),
                        entity.getMonedaPrincipal(),
                        entity.getEstado()
                ));

    }

    @Override
    public Empresa actualizar(Empresa empresa) {
        EmpresaEntity entity =
                empresaJpaRepository.findById(
                        empresa.getIdEmpresa()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Empresa no encontrada"
                        )
                );

        entity.setCodigo(empresa.getCodigo());

        entity.setRazonSocial(
                empresa.getRazonSocial()
        );

        entity.setNombreComercial(
                empresa.getNombreComercial()
        );

        entity.setRuc(
                empresa.getRuc()
        );

        entity.setDireccion(
                empresa.getDireccion()
        );

        entity.setTelefono(
                empresa.getTelefono()
        );

        entity.setCorreo(
                empresa.getCorreo()
        );

        entity.setMonedaPrincipal(
                empresa.getMonedaPrincipal()
        );

        empresaJpaRepository.save(entity);

        return empresa;

    }
}