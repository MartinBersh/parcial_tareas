# Todo List API

## Descripción del Proyecto
Este proyecto es una API RESTful de gestión de tareas (Todo List) implementada con Spring Boot. La aplicación posee operaciones CRUD para administración de las tareas, utilizando PostgreSQL como base de datos principal.

## Requisitos
- Java JDK 17 o superior
- Docker
- Docker Compose
- Maven

## Instrucciones de Instalación

### 1. Clonar el Repositorio
```bash
git clone [URL_DEL_REPOSITORIO]
cd [NOMBRE_DEL_DIRECTORIO]
```

### 2. Instalación de Dependencias
El proyecto utiliza Maven para la gestión de dependencias. Las principales dependencias incluyen:
- Spring Boot 3.4.0
- Spring Data JPA
- Spring Data Redis
- PostgreSQL Driver
- Lombok
- Spring Boot Test
- Rest Assured (para pruebas)

Las dependencias se instalarán automáticamente al construir el proyecto.

### 3. Variables de Entorno
Las variables de entorno están configuradas en el archivo `compose.yml`:
- `DATABASE_URL`: URL de conexión a PostgreSQL
- `DATABASE_USERNAME`: Usuario de PostgreSQL
- `DATABASE_PASSWORD`: Contraseña de PostgreSQL

## Ejecución Local

### 1. Usando Docker Compose
```bash
# Iniciar todos los servicios
docker compose up -d

# La aplicación estará disponible en http://localhost:8081
```

Este comando iniciará:
- La aplicación Spring Boot (puerto 8081)
- PostgreSQL (puerto 5434)

### 2. Usando Maven (Desarrollo)
```bash
# Construir el proyecto
./mvnw clean package

# Ejecutar la aplicación
./mvnw spring-boot:run
```

### Endpoints de la API
- `GET /api/tareas/{id}` - Obtener una tarea específica
- `POST /api/tareas` - Crear una nueva tarea
- `PUT /api/tareas/{id}` - Actualizar una tarea
- `DELETE /api/tareas/{id}` - Eliminar una tarea

## Pruebas

### Pruebas Unitarias
```bash
# Ejecutar pruebas unitarias
mvn test -Dtest=UnitTest
```
## Tecnologías Usadas

### Backend
- **Framework**: Spring Boot 3.4.0
- **Lenguaje**: Java 17
- **Gestión de Dependencias**: Maven

### Bases de Datos
- **Principal**: PostgreSQL 15
    - Almacenamiento persistente de tareas
### Contenedorización
- Docker
- Docker Compose
    - Orquestación de servicios
    - Configuración de entorno de desarrollo

### Testing
- JUnit
- Spring Boot Test
- Rest Assured

### Herramientas de Desarrollo
- Lombok
- Spring Data JPA
- Spring Web
- Maven Wrapper