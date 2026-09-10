# ms-configuracion

Microservicio encargado de la administración de configuraciones base del ecosistema JQCommerce.

## Arquitectura

- Arquitectura Hexagonal
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven

## Release Notes

### v1.0 - ms-configuracion

Componentes implementados:

- Empresa
- Sucursal
- Moneda
- TipoCambio
- ParametroSistema

Funcionalidades:

#### Empresa

- Registrar
- Listar
- Buscar por ID
- Actualizar
- Activar
- Desactivar

#### Sucursal

- Registrar
- Listar
- Buscar por ID
- Actualizar
- Activar
- Desactivar

#### Moneda

- Registrar
- Listar
- Buscar por ID
- Actualizar
- Activar
- Desactivar

#### TipoCambio

- Registrar
- Listar
- Buscar por ID
- Actualizar
- Validación de duplicidad
- Validación de moneda origen y destino

#### ParametroSistema

- Registrar
- Listar
- Buscar por ID
- Actualizar