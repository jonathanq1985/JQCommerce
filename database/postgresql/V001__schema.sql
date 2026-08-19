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

CREATE TABLE seguridad.usuario (
    id_usuario BIGSERIAL PRIMARY KEY,
    empresa_id BIGINT NOT NULL,
    sucursal_id BIGINT,
    username VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(500) NOT NULL,

    nombres VARCHAR(150) NOT NULL,
    apellidos VARCHAR(150) NOT NULL,

    correo VARCHAR(150),
    telefono VARCHAR(20),

    estado BOOLEAN DEFAULT TRUE,
    bloqueado BOOLEAN DEFAULT FALSE,

    ultimo_login TIMESTAMP,

    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_usuario_empresa
    FOREIGN KEY (empresa_id)
    REFERENCES configuracion.empresa(id_empresa),

    CONSTRAINT fk_usuario_sucursal
    FOREIGN KEY (sucursal_id)
    REFERENCES configuracion.sucursal(id_sucursal)
);

CREATE TABLE seguridad.usuario_rol (
    id_usuario_rol BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    rol_id BIGINT NOT NULL,

    CONSTRAINT fk_usuario_rol_usuario
    FOREIGN KEY (usuario_id)
    REFERENCES seguridad.usuario(id_usuario),

    CONSTRAINT fk_usuario_rol_rol
    FOREIGN KEY (rol_id)
    REFERENCES seguridad.rol(id_rol)
);

CREATE TABLE crm.cliente (
    id_cliente BIGSERIAL PRIMARY KEY,
    empresa_id BIGINT NOT NULL,

    tipo_documento VARCHAR(20) DEFAULT 'DNI',
    numero_documento VARCHAR(20),

    nombres VARCHAR(150) NOT NULL,
    apellidos VARCHAR(150),

    correo VARCHAR(150),
    telefono VARCHAR(20),

    direccion VARCHAR(300),

    fecha_nacimiento DATE,

    estado BOOLEAN DEFAULT TRUE,

    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_cliente_empresa
    FOREIGN KEY (empresa_id)
    REFERENCES configuracion.empresa(id_empresa)
);

CREATE TABLE productos.categoria (
    id_categoria BIGSERIAL PRIMARY KEY,
    empresa_id BIGINT NOT NULL,

    codigo VARCHAR(50),
    nombre VARCHAR(150) NOT NULL,
    descripcion VARCHAR(300),

    estado BOOLEAN DEFAULT TRUE,

    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_categoria_empresa
    FOREIGN KEY (empresa_id)
    REFERENCES configuracion.empresa(id_empresa)
);

CREATE TABLE productos.marca (
    id_marca BIGSERIAL PRIMARY KEY,
    empresa_id BIGINT NOT NULL,

    codigo VARCHAR(50),
    nombre VARCHAR(150) NOT NULL,

    estado BOOLEAN DEFAULT TRUE,

    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_marca_empresa
    FOREIGN KEY (empresa_id)
    REFERENCES configuracion.empresa(id_empresa)
);

CREATE TABLE productos.producto (
    id_producto BIGSERIAL PRIMARY KEY,

    empresa_id BIGINT NOT NULL,

    categoria_id BIGINT,
    marca_id BIGINT,

    codigo VARCHAR(100) NOT NULL,
    nombre VARCHAR(200) NOT NULL,

    descripcion TEXT,

    precio_compra NUMERIC(15,2),
    precio_venta NUMERIC(15,2),

    unidad_medida VARCHAR(20),

    stock_minimo NUMERIC(15,2) DEFAULT 0,

    estado BOOLEAN DEFAULT TRUE,

    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_producto_empresa
    FOREIGN KEY (empresa_id)
    REFERENCES configuracion.empresa(id_empresa),

    CONSTRAINT fk_producto_categoria
    FOREIGN KEY (categoria_id)
    REFERENCES productos.categoria(id_categoria),

    CONSTRAINT fk_producto_marca
    FOREIGN KEY (marca_id)
    REFERENCES productos.marca(id_marca)
);

CREATE TABLE productos.producto_variante (
    id_variante BIGSERIAL PRIMARY KEY,

    producto_id BIGINT NOT NULL,

    talla VARCHAR(20),
    color VARCHAR(50),

    codigo_barras VARCHAR(100),

    precio_venta NUMERIC(15,2),

    estado BOOLEAN DEFAULT TRUE,

    CONSTRAINT fk_variante_producto
    FOREIGN KEY (producto_id)
    REFERENCES productos.producto(id_producto)
);

CREATE TABLE inventario.almacen (
    id_almacen BIGSERIAL PRIMARY KEY,

    empresa_id BIGINT NOT NULL,

    codigo VARCHAR(50),
    nombre VARCHAR(150) NOT NULL,

    direccion VARCHAR(300),

    estado BOOLEAN DEFAULT TRUE,

    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_almacen_empresa
    FOREIGN KEY (empresa_id)
    REFERENCES configuracion.empresa(id_empresa)
);

CREATE TABLE inventario.inventario (
    id_inventario BIGSERIAL PRIMARY KEY,

    empresa_id BIGINT NOT NULL,

    almacen_id BIGINT NOT NULL,

    variante_id BIGINT NOT NULL,

    stock_actual NUMERIC(15,2) DEFAULT 0,

    stock_minimo NUMERIC(15,2) DEFAULT 0,

    stock_maximo NUMERIC(15,2),

    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_inventario_empresa
    FOREIGN KEY (empresa_id)
    REFERENCES configuracion.empresa(id_empresa),

    CONSTRAINT fk_inventario_almacen
    FOREIGN KEY (almacen_id)
    REFERENCES inventario.almacen(id_almacen),

    CONSTRAINT fk_inventario_variante
    FOREIGN KEY (variante_id)
    REFERENCES productos.producto_variante(id_variante)
);

CREATE TABLE inventario.movimiento_inventario (
    id_movimiento BIGSERIAL PRIMARY KEY,

    empresa_id BIGINT NOT NULL,

    almacen_id BIGINT NOT NULL,

    variante_id BIGINT NOT NULL,

    tipo_movimiento VARCHAR(50) NOT NULL,

    cantidad NUMERIC(15,2) NOT NULL,

    observacion TEXT,

    usuario_id BIGINT,

    fecha_movimiento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_mov_empresa
    FOREIGN KEY (empresa_id)
    REFERENCES configuracion.empresa(id_empresa),

    CONSTRAINT fk_mov_almacen
    FOREIGN KEY (almacen_id)
    REFERENCES inventario.almacen(id_almacen),

    CONSTRAINT fk_mov_variante
    FOREIGN KEY (variante_id)
    REFERENCES productos.producto_variante(id_variante),

    CONSTRAINT fk_mov_usuario
    FOREIGN KEY (usuario_id)
    REFERENCES seguridad.usuario(id_usuario)
);

CREATE TABLE ventas.pedido (
    id_pedido BIGSERIAL PRIMARY KEY,

    empresa_id BIGINT NOT NULL,

    cliente_id BIGINT NOT NULL,

    fecha_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    estado VARCHAR(30) DEFAULT 'PENDIENTE',

    subtotal NUMERIC(15,2) DEFAULT 0,
    impuesto NUMERIC(15,2) DEFAULT 0,
    descuento NUMERIC(15,2) DEFAULT 0,
    total NUMERIC(15,2) DEFAULT 0,

    observacion TEXT,

    usuario_id BIGINT,

    CONSTRAINT fk_pedido_empresa
    FOREIGN KEY (empresa_id)
    REFERENCES configuracion.empresa(id_empresa),

    CONSTRAINT fk_pedido_cliente
    FOREIGN KEY (cliente_id)
    REFERENCES crm.cliente(id_cliente),

    CONSTRAINT fk_pedido_usuario
    FOREIGN KEY (usuario_id)
    REFERENCES seguridad.usuario(id_usuario)
);

CREATE TABLE ventas.detalle_pedido (
    id_detalle BIGSERIAL PRIMARY KEY,

    pedido_id BIGINT NOT NULL,

    variante_id BIGINT NOT NULL,

    cantidad NUMERIC(15,2) NOT NULL,

    precio_unitario NUMERIC(15,2) NOT NULL,

    descuento NUMERIC(15,2) DEFAULT 0,

    subtotal NUMERIC(15,2) NOT NULL,

    CONSTRAINT fk_detalle_pedido
    FOREIGN KEY (pedido_id)
    REFERENCES ventas.pedido(id_pedido),

    CONSTRAINT fk_detalle_variante
    FOREIGN KEY (variante_id)
    REFERENCES productos.producto_variante(id_variante)
);


CREATE TABLE ventas.metodo_pago (
    id_metodo_pago BIGSERIAL PRIMARY KEY,

    empresa_id BIGINT NOT NULL,

    codigo VARCHAR(50),

    nombre VARCHAR(100),

    estado BOOLEAN DEFAULT TRUE,

    CONSTRAINT fk_metodo_empresa
    FOREIGN KEY (empresa_id)
    REFERENCES configuracion.empresa(id_empresa)
);

CREATE TABLE ventas.pago (
    id_pago BIGSERIAL PRIMARY KEY,

    pedido_id BIGINT NOT NULL,

    metodo_pago_id BIGINT NOT NULL,

    monto NUMERIC(15,2) NOT NULL,

    fecha_pago TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    referencia VARCHAR(200),

    CONSTRAINT fk_pago_pedido
    FOREIGN KEY (pedido_id)
    REFERENCES ventas.pedido(id_pedido),

    CONSTRAINT fk_pago_metodo
    FOREIGN KEY (metodo_pago_id)
    REFERENCES ventas.metodo_pago(id_metodo_pago)
);

CREATE TABLE auditoria.auditoria_sesion (
    id_sesion BIGSERIAL PRIMARY KEY,

    empresa_id BIGINT NOT NULL,

    usuario_id BIGINT NOT NULL,

    fecha_login TIMESTAMP NOT NULL,

    fecha_logout TIMESTAMP,

    direccion_ip VARCHAR(100),

    navegador VARCHAR(200),

    sistema_operativo VARCHAR(200),

    token_jwt TEXT,

    estado VARCHAR(50),

    CONSTRAINT fk_auditoria_sesion_empresa
    FOREIGN KEY (empresa_id)
    REFERENCES configuracion.empresa(id_empresa),

    CONSTRAINT fk_auditoria_sesion_usuario
    FOREIGN KEY (usuario_id)
    REFERENCES seguridad.usuario(id_usuario)
);

CREATE TABLE auditoria.auditoria_evento (
    id_evento BIGSERIAL PRIMARY KEY,

    empresa_id BIGINT NOT NULL,

    usuario_id BIGINT NOT NULL,

    modulo VARCHAR(100),

    accion VARCHAR(100),

    entidad VARCHAR(100),

    registro_id BIGINT,

    descripcion TEXT,

    fecha_evento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_auditoria_evento_empresa
    FOREIGN KEY (empresa_id)
    REFERENCES configuracion.empresa(id_empresa),

    CONSTRAINT fk_auditoria_evento_usuario
    FOREIGN KEY (usuario_id)
    REFERENCES seguridad.usuario(id_usuario)
);

CREATE TABLE auditoria.historial_cambios (
    id_historial BIGSERIAL PRIMARY KEY,

    empresa_id BIGINT NOT NULL,

    usuario_id BIGINT NOT NULL,

    modulo VARCHAR(100),

    tabla_afectada VARCHAR(100),

    campo VARCHAR(100),

    valor_anterior TEXT,

    valor_nuevo TEXT,

    fecha_cambio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_historial_empresa
    FOREIGN KEY (empresa_id)
    REFERENCES configuracion.empresa(id_empresa),

    CONSTRAINT fk_historial_usuario
    FOREIGN KEY (usuario_id)
    REFERENCES seguridad.usuario(id_usuario)
);

CREATE TABLE auditoria.bitacora_operaciones (
    id_bitacora BIGSERIAL PRIMARY KEY,

    empresa_id BIGINT NOT NULL,

    usuario_id BIGINT,

    proceso VARCHAR(200),

    descripcion TEXT,

    resultado VARCHAR(50),

    fecha_operacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_bitacora_empresa
    FOREIGN KEY (empresa_id)
    REFERENCES configuracion.empresa(id_empresa),

    CONSTRAINT fk_bitacora_usuario
    FOREIGN KEY (usuario_id)
    REFERENCES seguridad.usuario(id_usuario)
);

CREATE TABLE auditoria.logs_aplicacion (
    id_log BIGSERIAL PRIMARY KEY,

    empresa_id BIGINT,

    usuario_id BIGINT,

    nivel_log VARCHAR(20),

    modulo VARCHAR(100),

    mensaje TEXT,

    stacktrace TEXT,

    fecha_log TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_logs_empresa
    FOREIGN KEY (empresa_id)
    REFERENCES configuracion.empresa(id_empresa),

    CONSTRAINT fk_logs_usuario
    FOREIGN KEY (usuario_id)
    REFERENCES seguridad.usuario(id_usuario)
);

CREATE TABLE configuracion.moneda (
    id_moneda BIGSERIAL PRIMARY KEY,

    codigo VARCHAR(10) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    simbolo VARCHAR(10),

    estado BOOLEAN DEFAULT TRUE,

    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE configuracion.tipo_cambio (
    id_tipo_cambio BIGSERIAL PRIMARY KEY,

    moneda_origen_id BIGINT NOT NULL,
    moneda_destino_id BIGINT NOT NULL,

    valor NUMERIC(15,6) NOT NULL,

    fecha_vigencia DATE NOT NULL,

    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_tc_moneda_origen
    FOREIGN KEY (moneda_origen_id)
    REFERENCES configuracion.moneda(id_moneda),

    CONSTRAINT fk_tc_moneda_destino
    FOREIGN KEY (moneda_destino_id)
    REFERENCES configuracion.moneda(id_moneda)
);


CREATE TABLE configuracion.parametro_sistema (
    id_parametro BIGSERIAL PRIMARY KEY,

    empresa_id BIGINT NOT NULL,

    codigo VARCHAR(100) NOT NULL,
    nombre VARCHAR(150) NOT NULL,

    valor TEXT,

    descripcion TEXT,

    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_parametro_empresa
    FOREIGN KEY (empresa_id)
    REFERENCES configuracion.empresa(id_empresa)
);

CREATE TABLE productos.proveedor (
    id_proveedor BIGSERIAL PRIMARY KEY,

    empresa_id BIGINT NOT NULL,

    ruc VARCHAR(20),
    razon_social VARCHAR(250) NOT NULL,

    correo VARCHAR(150),
    telefono VARCHAR(50),

    direccion VARCHAR(300),

    estado BOOLEAN DEFAULT TRUE,

    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_proveedor_empresa
    FOREIGN KEY (empresa_id)
    REFERENCES configuracion.empresa(id_empresa)
);

CREATE TABLE productos.compra (
    id_compra BIGSERIAL PRIMARY KEY,

    empresa_id BIGINT NOT NULL,

    proveedor_id BIGINT NOT NULL,

    fecha_compra TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    subtotal NUMERIC(15,2),
    impuesto NUMERIC(15,2),
    total NUMERIC(15,2),

    observacion TEXT,

    CONSTRAINT fk_compra_empresa
    FOREIGN KEY (empresa_id)
    REFERENCES configuracion.empresa(id_empresa),

    CONSTRAINT fk_compra_proveedor
    FOREIGN KEY (proveedor_id)
    REFERENCES productos.proveedor(id_proveedor)
);

CREATE TABLE productos.detalle_compra (
    id_detalle_compra BIGSERIAL PRIMARY KEY,

    compra_id BIGINT NOT NULL,

    variante_id BIGINT NOT NULL,

    cantidad NUMERIC(15,2) NOT NULL,

    precio_unitario NUMERIC(15,2) NOT NULL,

    subtotal NUMERIC(15,2) NOT NULL,

    CONSTRAINT fk_detalle_compra
    FOREIGN KEY (compra_id)
    REFERENCES productos.compra(id_compra),

    CONSTRAINT fk_detalle_compra_variante
    FOREIGN KEY (variante_id)
    REFERENCES productos.producto_variante(id_variante)
);

CREATE TABLE ventas.cotizacion (
    id_cotizacion BIGSERIAL PRIMARY KEY,

    empresa_id BIGINT NOT NULL,

    cliente_id BIGINT NOT NULL,

    fecha_cotizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    subtotal NUMERIC(15,2),
    impuesto NUMERIC(15,2),
    descuento NUMERIC(15,2),
    total NUMERIC(15,2),

    estado VARCHAR(50) DEFAULT 'PENDIENTE',

    observacion TEXT,

    CONSTRAINT fk_cotizacion_empresa
    FOREIGN KEY (empresa_id)
    REFERENCES configuracion.empresa(id_empresa),

    CONSTRAINT fk_cotizacion_cliente
    FOREIGN KEY (cliente_id)
    REFERENCES crm.cliente(id_cliente)
);
