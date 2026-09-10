package com.jqdigitalsolutions.jqcommerce.configuracion.infrastructure.adapter;

import com.jqdigitalsolutions.jqcommerce.configuracion.domain.model.ParametroSistema;
import com.jqdigitalsolutions.jqcommerce.configuracion.domain.port.ParametroSistemaRepositoryPort;
import com.jqdigitalsolutions.jqcommerce.configuracion.infrastructure.entity.ParametroSistemaEntity;
import com.jqdigitalsolutions.jqcommerce.configuracion.infrastructure.repository.ParametroSistemaJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ParametroSistemaRepositoryAdapter implements ParametroSistemaRepositoryPort {

    private final ParametroSistemaJpaRepository parametroSistemaJpaRepository;

    public ParametroSistemaRepositoryAdapter(
            ParametroSistemaJpaRepository parametroSistemaJpaRepository) {

        this.parametroSistemaJpaRepository = parametroSistemaJpaRepository;

    }

    @Override
    public ParametroSistema guardar(
            ParametroSistema parametroSistema) {

        ParametroSistemaEntity entity = ParametroSistemaEntity.builder()
                        .empresaId(
                                parametroSistema.getEmpresaId()
                        )
                        .codigo(
                                parametroSistema.getCodigo()
                        )
                        .nombre(
                                parametroSistema.getNombre()
                        )
                        .valor(
                                parametroSistema.getValor()
                        )
                        .descripcion(
                                parametroSistema.getDescripcion()
                        )
                        .build();

        ParametroSistemaEntity guardado = parametroSistemaJpaRepository.save(entity);
        parametroSistema.setIdParametro(guardado.getIdParametro());
        return parametroSistema;

    }

    @Override
    public List<ParametroSistema> listar() {
        return parametroSistemaJpaRepository.findAll()
                .stream()
                .map(entity -> new ParametroSistema(
                        entity.getIdParametro(),
                        entity.getEmpresaId(),
                        entity.getCodigo(),
                        entity.getNombre(),
                        entity.getValor(),
                        entity.getDescripcion()
                ))
                .toList();

    }
    @Override
    public Optional<ParametroSistema> buscarPorId(
            Long idParametro) {

        return parametroSistemaJpaRepository
                .findById(idParametro)
                .map(entity -> new ParametroSistema(
                        entity.getIdParametro(),
                        entity.getEmpresaId(),
                        entity.getCodigo(),
                        entity.getNombre(),
                        entity.getValor(),
                        entity.getDescripcion()
                ));

    }

    @Override
    public ParametroSistema actualizar(
            ParametroSistema parametroSistema) {

        ParametroSistemaEntity entity =
                parametroSistemaJpaRepository
                        .findById(
                                parametroSistema.getIdParametro()
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Parametro del sistema no encontrado"
                                )
                        );

        entity.setEmpresaId(
                parametroSistema.getEmpresaId()
        );

        entity.setCodigo(
                parametroSistema.getCodigo()
        );

        entity.setNombre(
                parametroSistema.getNombre()
        );

        entity.setValor(parametroSistema.getValor());

        entity.setDescripcion(
                parametroSistema.getDescripcion()
        );

        parametroSistemaJpaRepository.save(entity);

        return parametroSistema;

    }

}