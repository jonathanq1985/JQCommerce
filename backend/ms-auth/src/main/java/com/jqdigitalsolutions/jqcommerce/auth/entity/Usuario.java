package com.jqdigitalsolutions.jqcommerce.auth.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuario", schema = "seguridad")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "empresa_id")
    private Long empresaId;

    private String username;

    @Column(name = "password_hash")
    private String passwordHash;

    private String nombres;

    private String apellidos;

    private String correo;

    private String telefono;

    private Boolean estado;

    private Boolean bloqueado;
}