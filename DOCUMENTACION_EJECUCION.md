# Guía de Ejecución del Proyecto

Este archivo proporciona instrucciones detalladas para ejecutar el proyecto, ya sea de forma local utilizando Java y Maven o mediante contenedores Docker.

---

## 1. Ejecución Local con Java y Maven

### **Requisitos Previos**
- **Java JDK 17** instalado.
- **Maven**, o puedes usar el Maven Wrapper incluido en el proyecto.
- **PostgreSQL** configurado localmente.
- **Redis** configurado localmente.

### **Pasos de Ejecución**

1. **Configurar la Base de Datos Local**
    - Asegúrate de que PostgreSQL esté en ejecución en el puerto `5432`.
    - Crea una base de datos llamada `todolist` con el siguiente comando:
      ```sql
      CREATE DATABASE todolist;
      ```

2. **Configurar Redis**
    - Verifica que Redis esté activo en el puerto `6379`:
      ```bash
      redis-server
      ```

3. **Compilar el Proyecto**
    - Construye el proyecto ejecutando:
      ```bash
      ./mvnw clean package
      ```

4. **Iniciar la Aplicación**
    - Ejecuta el comando:
      ```bash
      ./mvnw spring-boot:run
      ```

5. **Verificar la Ejecución**
    - Accede a la aplicación en [http://localhost:8080](http://localhost:8080).
    - Prueba los endpoints:
        - **GET**: `http://localhost:8080/api/tasks`
        - **POST**: `http://localhost:8080/api/tasks`

### **Problemas Comunes**
- **Error al conectar con PostgreSQL**: Asegúrate de que el servicio está activo y las credenciales son correctas.
- **Error al conectar con Redis**: Verifica que Redis esté corriendo en el puerto adecuado.
- **Conflicto de puertos**: Asegúrate de que los puertos `8080`, `5432` y `6379` no estén siendo utilizados por otros servicios.

---

## 2. Ejecución con Docker

### **Requisitos Previos**
- Tener instalado **Docker** y **Docker Compose**.

### **Pasos de Ejecución**

1. **Construir y Ejecutar con Docker Compose**
    - Ejecuta el siguiente comando:
      ```bash
      docker compose up -d
      ```
    - Este comando:
        - Construye la imagen de la aplicación.
        - Inicia PostgreSQL en el puerto `5434`.
        - Inicia Redis en el puerto `6379`.
        - Levanta la aplicación en el puerto `8080`.

2. **Verificar los Contenedores**
    - Comprueba el estado de los contenedores:
      ```bash
      docker compose ps
      ```
    - Deberías ver tres servicios activos:
        - `todolist_app` (aplicación principal).
        - `todolist_db` (PostgreSQL).
        - `redis` (Redis).

3. **Acceso y Logs**
    - Accede a la aplicación en [http://localhost:8080](http://localhost:8080).
    - Para monitorear los logs de la aplicación:
      ```bash
      docker compose logs -f todolist_app
      ```

4. **Detener los Servicios**
    - Para detener todos los contenedores:
      ```bash
      docker compose down
      ```

### **Problemas Comunes**
- **Puertos en uso**: Asegúrate de que los puertos `8080`, `5434` y `6379` no estén ocupados.
- **Errores de permisos**: Comprueba que tienes permisos para usar Docker.
- **Falta de memoria**: Asigna suficientes recursos a Docker en la configuración.

---

## 3. Ejecución de Pruebas

Puedes ejecutar las pruebas en los dos entornos disponibles:

### **Ejecución Local**
```bash
./mvnw test
