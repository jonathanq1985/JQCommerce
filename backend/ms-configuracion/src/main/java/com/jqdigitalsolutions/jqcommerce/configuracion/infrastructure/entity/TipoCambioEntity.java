package com.jqdigitalsolutions.jqcommerce.configuracion.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tipo_cambio", schema = "configuracion")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoCambioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_cambio")
    private Long idTipoCambio;

    @Column(name = "moneda_origen_id")
    private Long monedaOrigenId;

    @Column(name = "moneda_destino_id")
    private Long monedaDestinoId;

    private BigDecimal valor;

    @Column(name = "fecha_vigencia")
    private LocalDate fechaVigencia;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

}
