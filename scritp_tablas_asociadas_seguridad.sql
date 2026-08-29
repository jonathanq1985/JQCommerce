SELECT *
FROM seguridad.rol;

delete from seguridad.rol where  id_rol=8 and empresa_id=1;

SELECT
column_name,
data_type
FROM information_schema.columns
WHERE table_schema = 'seguridad'
AND table_name = 'rol';

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