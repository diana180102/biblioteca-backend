# Library Management API

API REST desarrollada con Spring Boot para la gestión de una biblioteca. Permite administrar autores, libros, ejemplares, usuarios y préstamos, incluyendo el registro de préstamos, devoluciones y la consulta de ejemplares disponibles por ISBN.

---

## Requisitos

- Java 21
- Docker
- Docker Compose
- Maven 3.9+

---

## Configuración

Crear un archivo `.env` en la raíz del proyecto con el siguiente contenido:

```env
POSTGRES_DB=library_db
POSTGRES_USER=postgres
POSTGRES_PASSWORD=18
DB_EXTERNAL_PORT=5432

DB_URI=jdbc:postgresql://db:5432/library_db
DB_USER=postgres
DB_PASSWORD=18
DB_DRIVER=org.postgresql.Driver

SERVER_PORT=8080
API_EXTERNAL_PORT=8080
```

---

## Ejecución

### 1. Clonar el repositorio

```bash
git clone https://github.com/diana180102/biblioteca-backend.git
cd biblioteca-backend
```

### 2. Levantar los contenedores

```bash
docker compose up -d
```

### 3. Verificar que la aplicación esté en ejecución

La API estará disponible en:

```text
http://localhost:8080
```

---

## Base de datos

El proyecto incluye un respaldo de la base de datos con datos de prueba en:

```text
dump/library.dump
```

Si deseas restaurar el respaldo manualmente, ejecuta:

```bash
docker compose exec db psql -U postgres -c "CREATE DATABASE library_db;"
```

Luego restaura el respaldo:

```bash
docker compose exec -T db pg_restore -U postgres -d library_db < dump/library.dump
```