```markdown
# README.md
# E-Commerce API

API REST para sistema de comercio electrónico desarrollada con **Spring Boot 3.3.0** y **Java 17**, utilizando **PostgreSQL** (Supabase) como base de datos.

## 🚀 Características

- ✅ CRUD completo para todas las entidades
- ✅ Validaciones con Jakarta Validation
- ✅ Gestión de carrito de compras
- ✅ Procesamiento de pedidos
- ✅ Sistema de envíos con tracking
- ✅ Documentación automática con OpenAPI/Swagger
- ✅ Soft delete en todas las entidades
- ✅ CORS configurado para integración frontend

## 📋 Requisitos

- Java 17+
- Maven 3.6+
- PostgreSQL (Supabase)
- IDE (IntelliJ IDEA, VS Code, Eclipse)

## ⚙️ Configuración

1. **Clonar el repositorio**

```bash
git clone <repository-url>
cd ecommerce-api
```

2. **Configurar variables de entorno**

Crear archivo `.env` en la raíz del proyecto:

```env
DB_URL=jdbc:postgresql://your-supabase-url:5432/postgres
DB_USERNAME=postgres
DB_PASSWORD=your-password
```

3. **Instalar dependencias**

```bash
mvn clean install
```

4. **Ejecutar la aplicación**

```bash
mvn spring-boot:run
```

La aplicación estará disponible en:  `http://localhost:8080`

## 📚 Documentación API

Una vez iniciada la aplicación, accede a la documentación interactiva: 

- **Swagger UI**: http://localhost:8080/swagger-ui. html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs

## 🗂️ Endpoints Principales

### Categorías
- `GET /api/categorias` - Listar todas las categorías
- `GET /api/categorias/{id}` - Obtener categoría por ID
- `POST /api/categorias` - Crear nueva categoría
- `PUT /api/categorias/{id}` - Actualizar categoría
- `DELETE /api/categorias/{id}` - Desactivar categoría

### Productos
- `GET /api/productos` - Listar todos los productos
- `GET /api/productos/{id}` - Obtener producto por ID
- `POST /api/productos` - Crear nuevo producto
- `PUT /api/productos/{id}` - Actualizar producto
- `DELETE /api/productos/{id}` - Desactivar producto

### Usuarios
- `GET /api/usuarios` - Listar todos los usuarios
- `GET /api/usuarios/{id}` - Obtener usuario por ID
- `POST /api/usuarios` - Registrar nuevo usuario
- `PUT /api/usuarios/{id}` - Actualizar usuario
- `DELETE /api/usuarios/{id}` - Desactivar usuario

### Carrito de Compras
- `GET /api/detalles-carrito` - Listar todos los carritos
- `GET /api/detalles-carrito/usuario/{usuarioId}` - Obtener carrito por usuario
- `POST /api/detalles-carrito` - Agregar producto al carrito
- `PUT /api/detalles-carrito/{id}` - Actualizar cantidad en carrito
- `DELETE /api/detalles-carrito/{id}` - Eliminar producto del carrito
- `DELETE /api/detalles-carrito/usuario/{usuarioId}` - Limpiar carrito completo

### Pedidos
- `GET /api/pedidos` - Listar todos los pedidos
- `GET /api/pedidos/{id}` - Obtener pedido por ID
- `POST /api/pedidos` - Procesar pedido desde carrito
- `PUT /api/pedidos/{id}` - Actualizar pedido
- `DELETE /api/pedidos/{id}` - Cancelar pedido

### Envíos
- `GET /api/envios` - Listar todos los envíos
- `GET /api/envios/{id}` - Obtener envío por ID
- `POST /api/envios` - Crear nuevo envío
- `PUT /api/envios/{id}` - Actualizar estado de envío
- `DELETE /api/envios/{id}` - Cancelar envío

### Domicilios
- `GET /api/domicilios` - Listar todos los domicilios
- `GET /api/domicilios/{id}` - Obtener domicilio por ID
- `POST /api/domicilios` - Crear nuevo domicilio
- `PUT /api/domicilios/{id}` - Actualizar domicilio
- `DELETE /api/domicilios/{id}` - Desactivar domicilio

### Métodos de Pago
- `GET /api/metodos-pago` - Listar todos los métodos
- `GET /api/metodos-pago/{id}` - Obtener método por ID
- `POST /api/metodos-pago` - Crear nuevo método
- `PUT /api/metodos-pago/{id}` - Actualizar método
- `DELETE /api/metodos-pago/{id}` - Desactivar método

## 🏗️ Estructura del Proyecto

```
src/main/java/mx/tecnm/ecommerce/api/
├── config/              # Configuraciones (CORS, DB, OpenAPI)
├── controllers/         # Controladores REST
├── dto/                 # Data Transfer Objects
├── models/              # Modelos de dominio
├── repository/          # DAOs y RowMappers
└── EcommerceApplication.java
```

## 🔄 Flujo de Compra

1. Usuario se registra:  `POST /api/usuarios`
2. Navega productos: `GET /api/productos`
3. Agrega al carrito: `POST /api/detalles-carrito`
4. Revisa carrito: `GET /api/detalles-carrito/usuario/{id}`
5. Procesa pedido: `POST /api/pedidos`
6. Sistema crea envío: `POST /api/envios`
7. Rastrea envío: `GET /api/envios/{id}`

## 🛠️ Tecnologías

- **Spring Boot 3.3.0**
- **Java 17**
- **PostgreSQL** (Supabase)
- **Spring JDBC** (JdbcClient)
- **Jakarta Validation**
- **SpringDoc OpenAPI** (Swagger)
- **Dotenv Java**

## 📝 Licencia

MIT License

---