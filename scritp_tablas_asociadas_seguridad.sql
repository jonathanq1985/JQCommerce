SELECT *
FROM seguridad.rol;

delete from seguridad.rol where  id_rol=8 and empresa_id=1;

drop table configuracion.empresa;

SELECT
column_name,
data_type
FROM information_schema.columns
WHERE table_schema = 'configuracion'
AND table_name = 'empresa';

SELECT * FROM configuracion.empresa;

INSERT INTO seguridad.rol
(
    empresa_id,
    codigo,
    nombre,
    descripcion,
    estado,
    fecha_creacion
)
VALUES
(
    1,
    'ADMIN',
    'Administrador',
    'Rol con acceso total al sistema',
    TRUE,
    NOW()
);

INSERT INTO seguridad.rol
(
    empresa_id,
    codigo,
    nombre,
    descripcion,
    estado,
    fecha_creacion
)
VALUES
(
    1,
    'VENDEDOR',
    'Vendedor',
    'Rol responsable de ventas',
    TRUE,
    NOW()
);

INSERT INTO seguridad.rol
(
    empresa_id,
    codigo,
    nombre,
    descripcion,
    estado,
    fecha_creacion
)
VALUES
(
    1,
    'ALMACEN',
    'Almacén',
    'Rol responsable de inventario',
    TRUE,
    NOW()
);

SELECT
column_name,
data_type
FROM information_schema.columns
WHERE table_schema = 'seguridad'
AND table_name = 'usuario_rol';

SELECT *
FROM seguridad.rol; 

INSERT INTO seguridad.usuario_rol
(
    usuario_id,
    rol_id,
    estado,
    fecha_creacion
)
VALUES
(
    1,
    5,
    'A',
    NOW()
);

SELECT
column_name,
data_type
FROM information_schema.columns
WHERE table_schema = 'seguridad'
AND table_name = 'permiso'

----------------------------------------

-- Ing_JQC: Permiso para consultar usuarios

INSERT INTO seguridad.permiso
(
    codigo,
    nombre,
    descripcion,
    estado,
    fecha_creacion
)
VALUES
(
    'USUARIO_VER',
    'Ver Usuarios',
    'Permite consultar usuarios',
    TRUE,
    NOW()
);

-- Ing_JQC: Permiso para registrar usuarios

INSERT INTO seguridad.permiso
(
    codigo,
    nombre,
    descripcion,
    estado,
    fecha_creacion
)
VALUES
(
    'USUARIO_CREAR',
    'Crear Usuarios',
    'Permite registrar usuarios',
    TRUE,
    NOW()
);

-- Ing_JQC: Permiso para consultar productos

INSERT INTO seguridad.permiso
(
    codigo,
    nombre,
    descripcion,
    estado,
    fecha_creacion
)
VALUES
(
    'PRODUCTO_VER',
    'Ver Productos',
    'Permite consultar productos',
    TRUE,
    NOW()
);

-- Ing_JQC: Permiso para registrar productos

INSERT INTO seguridad.permiso
(
    codigo,
    nombre,
    descripcion,
    estado,
    fecha_creacion
)
VALUES
(
    'PRODUCTO_CREAR',
    'Crear Productos',
    'Permite registrar productos',
    TRUE,
    NOW()
);

-- Ing_JQC: Permiso para consultar ventas

INSERT INTO seguridad.permiso
(
    codigo,
    nombre,
    descripcion,
    estado,
    fecha_creacion
)
VALUES
(
    'VENTA_VER',
    'Ver Ventas',
    'Permite consultar ventas',
    TRUE,
    NOW()
);

-- Ing_JQC: Permiso para registrar ventas

INSERT INTO seguridad.permiso
(
    codigo,
    nombre,
    descripcion,
    estado,
    fecha_creacion
)
VALUES
(
    'VENTA_CREAR',
    'Crear Ventas',
    'Permite registrar ventas',
    TRUE,
    NOW()
);

SELECT *
FROM seguridad.permiso;

SELECT
    column_name,
    data_type
FROM information_schema.columns
WHERE table_schema = 'seguridad'
AND table_name = 'rol_permiso';

SELECT * FROM seguridad.rol;

SELECT * FROM seguridad.permiso;

-- Ing_JQC: ADMIN puede crear productos

INSERT INTO seguridad.rol_permiso
(
    rol_id,
    permiso_id,
    estado,
    fecha_creacion
)
VALUES
(
    5,
    1,
    'A',
    NOW()
);

-- Ing_JQC: ADMIN puede consultar ventas

INSERT INTO seguridad.rol_permiso
(
    rol_id,
    permiso_id,
    estado,
    fecha_creacion
)
VALUES
(
    5,
    2,
    'A',
    NOW()
);

-- Ing_JQC: ADMIN puede registrar ventas

INSERT INTO seguridad.rol_permiso
(
    rol_id,
    permiso_id,
    estado,
    fecha_creacion
)
VALUES
(
    5,
    3,
    'A',
    NOW()
);

SELECT *
FROM seguridad.rol_permiso;

---------------SCRITP PARA PROBAR LAS TABLAS --------------
CREATE TABLE seguridad.auditoria_sesion
(
    id_auditoria BIGSERIAL PRIMARY KEY,

    usuario_id BIGINT NOT NULL,

    fecha_login TIMESTAMP NOT NULL,

    fecha_logout TIMESTAMP,

    direccion_ip VARCHAR(100),

    user_agent VARCHAR(500),

    estado VARCHAR(20)
);

-- Ing_JQC: Registrar auditoría inicial para pruebas
INSERT INTO seguridad.auditoria_sesion
(
    usuario_id,
    fecha_login,
    fecha_logout,
    direccion_ip,
    user_agent,
    estado
)
VALUES
(
    1,
    NOW(),
    NULL,
    '127.0.0.1',
    'Postman',
    'ACTIVA'
);

-- Ing_JQC: Consulta de auditorías registradas

-- Ing_JQC: Consultar auditoría de sesiones

SELECT *
FROM seguridad.auditoria_sesion
ORDER BY id_auditoria DESC;

-- Ing_JQC: Verificar hash actualizado del usuario

SELECT
    id_usuario,
    username,
    password_hash
FROM seguridad.usuario
WHERE username = 'admin';

-- Ing_JQC: Verificar hash actualizado de contraseña

SELECT
    id_usuario,
    username,
    password_hash
FROM seguridad.usuario
WHERE username = 'admin';

-- Ing_JQC: Agregar control de intentos fallidos de autenticación

ALTER TABLE seguridad.usuario
ADD COLUMN intentos_fallidos INTEGER DEFAULT 0;

-- Ing_JQC: Agregar indicador de bloqueo de usuario

ALTER TABLE seguridad.usuario
ADD COLUMN bloqueado BOOLEAN DEFAULT FALSE;

SELECT id_empresa,
       razon_social,
       estado
FROM configuracion.empresa
WHERE id_empresa = 2;

-- Ing_JQC: Desactivar empresa
-- Tecnología: PostgreSQL
-- Finalidad: Realizar borrado lógico de una empresa manteniendo
--            el historial de información para auditoría.

UPDATE configuracion.empresa
SET estado = FALSE
WHERE id_empresa = :idEmpresa;

-- Ing_JQC: Activar empresa
-- Tecnología: PostgreSQL
-- Finalidad: Reactivar una empresa previamente deshabilitada.

UPDATE configuracion.empresa
SET estado = TRUE
WHERE id_empresa = :idEmpresa;

-- Ing_JQC: Activar empresa
-- Tecnología: PostgreSQL
-- Finalidad: Reactivar una empresa previamente deshabilitada.

UPDATE configuracion.empresa
SET estado = TRUE
WHERE id_empresa = :idEmpresa;

-- Ing_JQC: Consultar estado de empresa
-- Finalidad: Verificar activación de empresa

SELECT
    id_empresa,
    razon_social,
    estado
FROM configuracion.empresa
WHERE id_empresa = 2;

-- Ing_JQC: Tabla de sucursales
-- Tecnología: PostgreSQL
-- Finalidad: Administrar las sucursales de cada empresa

CREATE TABLE configuracion.sucursal
(
    id_sucursal BIGSERIAL PRIMARY KEY,

    id_empresa BIGINT NOT NULL,

    codigo VARCHAR(20) NOT NULL,

    nombre VARCHAR(150) NOT NULL,

    direccion VARCHAR(250),

    telefono VARCHAR(30),

    correo VARCHAR(150),

    principal BOOLEAN NOT NULL DEFAULT FALSE,

    estado BOOLEAN NOT NULL DEFAULT TRUE,

    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_sucursal_empresa
        FOREIGN KEY (id_empresa)
        REFERENCES configuracion.empresa(id_empresa)
);

select *  from configuracion.sucursal

-- Ing_JQC: Agregar correo a sucursal
-- Tecnología: PostgreSQL
-- Finalidad: Permitir registrar correo de contacto por sucursal

ALTER TABLE configuracion.sucursal
ADD COLUMN correo VARCHAR(150);


-- Ing_JQC: Consultar sucursales registradas
-- Finalidad: Verificar persistencia de sucursales

SELECT *
FROM configuracion.sucursal;

-- Ing_JQC: Consultar sucursales registradas
-- Tecnología: PostgreSQL
-- Finalidad: Obtener todas las sucursales disponibles

SELECT *
FROM configuracion.sucursal
ORDER BY id_sucursal;