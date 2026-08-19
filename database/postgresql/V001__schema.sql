CREATE SCHEMA IF NOT EXISTS seguridad;
CREATE SCHEMA IF NOT EXISTS configuracion;
CREATE SCHEMA IF NOT EXISTS crm;
CREATE SCHEMA IF NOT EXISTS productos;
CREATE SCHEMA IF NOT EXISTS inventario;
CREATE SCHEMA IF NOT EXISTS ventas;
CREATE SCHEMA IF NOT EXISTS auditoria;


CREATE TABLE configuracion.empresa (
    id_empresa BIGSERIAL PRIMARY KEY,
    codigo VARCHAR(50) NOT NULL UNIQUE,
    razon_social VARCHAR(200) NOT NULL,
    nombre_comercial VARCHAR(200),
    ruc VARCHAR(11) UNIQUE,
    direccion VARCHAR(300),
    telefono VARCHAR(20),
    correo VARCHAR(150),
    moneda_principal VARCHAR(5) DEFAULT 'PEN',
    estado BOOLEAN DEFAULT TRUE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP
);

CREATE TABLE configuracion.sucursal (
    id_sucursal BIGSERIAL PRIMARY KEY,
    empresa_id BIGINT NOT NULL,
    codigo VARCHAR(50),
    nombre VARCHAR(150),
    direccion VARCHAR(300),
    telefono VARCHAR(20),
    estado BOOLEAN DEFAULT TRUE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_sucursal_empresa
    FOREIGN KEY (empresa_id)
    REFERENCES configuracion.empresa(id_empresa)
);

CREATE TABLE seguridad.rol (
    id_rol BIGSERIAL PRIMARY KEY,
    empresa_id BIGINT NOT NULL,
    codigo VARCHAR(50) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(300),
    estado BOOLEAN DEFAULT TRUE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_rol_empresa
    FOREIGN KEY (empresa_id)
    REFERENCES configuracion.empresa(id_empresa)
);

CREATE TABLE seguridad.permiso (
    id_permiso BIGSERIAL PRIMARY KEY,
    codigo VARCHAR(100) NOT NULL UNIQUE,
    nombre VARCHAR(150) NOT NULL,
    descripcion VARCHAR(300),
    estado BOOLEAN DEFAULT TRUE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE seguridad.rol_permiso (
    id_rol_permiso BIGSERIAL PRIMARY KEY,
    rol_id BIGINT NOT NULL,
    permiso_id BIGINT NOT NULL,

    CONSTRAINT fk_rol_permiso_rol
    FOREIGN KEY (rol_id)
    REFERENCES seguridad.rol(id_rol),

    CONSTRAINT fk_rol_permiso_permiso
    FOREIGN KEY (permiso_id)
    REFERENCES seguridad.permiso(id_permiso)
);



