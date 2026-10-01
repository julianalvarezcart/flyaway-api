<div align="center">

# ✈️ Fly Away Travel — Flight Booking API

**Lab 07 · CS2031 Desarrollo Basado en Plataformas · Semana 7 · UTEC**

API REST de reservas de vuelos construida con **Spring Boot 4**, **PostgreSQL**, **JWT** y **Maven**.

![Java](https://img.shields.io/badge/Java-21%2B-ED8B00?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.1-6DB33F?logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-7.x-6DB33F?logo=springsecurity&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-4169E1?logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/Auth-JWT-000000?logo=jsonwebtokens&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.9.x-C71A36?logo=apachemaven&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Compose-2496ED?logo=docker&logoColor=white)
![Postman](https://img.shields.io/badge/Tests-43%20passed-FF6C37?logo=postman&logoColor=white)

</div>

---

## 📋 Tabla de Contenidos

1. [Descripción](#-descripción)
2. [Tecnologías Utilizadas](#-tecnologías-utilizadas)
3. [Estructura del Proyecto](#-estructura-del-proyecto)
4. [Requisitos Previos](#-requisitos-previos)
5. [Creación del Proyecto Paso a Paso](#-creación-del-proyecto-paso-a-paso)
6. [Dependencias del `pom.xml`](#-dependencias-del-pomxml)
7. [Configuración](#%EF%B8%8F-configuración)
8. [Cómo Ejecutar](#-cómo-ejecutar)
9. [Endpoints Implementados](#-endpoints-implementados)
10. [Misiones Implementadas](#-misiones-implementadas)
11. [Testing con Postman](#-testing-con-postman)
12. [Errores Comunes y Soluciones](#-errores-comunes-y-soluciones)
13. [Recursos Adicionales](#-recursos-adicionales)
14. [Autor](#-autor)

---

## 📖 Descripción

**Fly Away Travel** es una aerolínea ficticia que necesita un API REST para gestionar:

- ✈️ Creación de vuelos
- 👤 Registro de usuarios
- 🔐 Autenticación con JWT
- 🔍 Búsqueda de vuelos
- 📅 Reserva de vuelos
- 📧 Generación de emails de confirmación

El proyecto implementa **6 misiones** que cubren los conceptos de Spring Boot, JPA, Spring Security, JWT, DTOs, validaciones y reglas de negocio.

> 🏆 **Recompensa:** 1.0 punto adicional para la PC1.

---

## 🛠 Tecnologías Utilizadas

| Tecnología | Versión | Uso |
|---|---|---|
| **Java** | 21 / 26 | Lenguaje base |
| **Spring Boot** | 4.1.1 | Framework principal |
| **Spring Security** | 7.x | Autenticación y autorización |
| **Spring Data JPA** | – | Acceso a datos |
| **PostgreSQL** | 16 | Base de datos |
| **JWT (JJWT)** | 0.13.0 | Tokens de autenticación |
| **Lombok** | 1.18.46 | Reducción de boilerplate |
| **Maven** | 3.9.x | Build tool |
| **Docker** | – | Contenedor para PostgreSQL |
| **Postman** | – | Testing de endpoints |

---

## 📁 Estructura del Proyecto

```text
flyaway-api/
├── .mvn/                                   # Maven Wrapper
├── src/
│   ├── main/
│   │   ├── java/pe/edu/utec/flyaway/api/
│   │   │   ├── FlyawayApiApplication.java  # Clase principal
│   │   │   │
│   │   │   ├── application/                # Capa de servicios (lógica de negocio)
│   │   │   │   └── service/
│   │   │   │       ├── AuthService.java
│   │   │   │       ├── BookingService.java
│   │   │   │       ├── EmailService.java
│   │   │   │       ├── FlightService.java
│   │   │   │       └── UserService.java
│   │   │   │
│   │   │   ├── domain/                     # Capa de dominio (entidades)
│   │   │   │   └── model/
│   │   │   │       ├── Booking.java
│   │   │   │       ├── Flight.java
│   │   │   │       └── User.java
│   │   │   │
│   │   │   ├── dto/                        # Data Transfer Objects
│   │   │   │   ├── request/
│   │   │   │   │   ├── BookingRequest.java
│   │   │   │   │   ├── FlightRequest.java
│   │   │   │   │   ├── LoginRequest.java
│   │   │   │   │   └── RegisterRequest.java
│   │   │   │   └── response/
│   │   │   │       ├── BookingResponse.java
│   │   │   │       ├── FlightResponse.java
│   │   │   │       ├── LoginResponse.java
│   │   │   │       └── RegisterResponse.java
│   │   │   │
│   │   │   └── infrastructure/             # Capa de infraestructura
│   │   │       ├── config/
│   │   │       │   ├── JwtAuthFilter.java
│   │   │       │   ├── JwtService.java
│   │   │       │   └── SecurityConfig.java
│   │   │       ├── repository/
│   │   │       │   ├── BookingRepository.java
│   │   │       │   ├── FlightRepository.java
│   │   │       │   └── UserRepository.java
│   │   │       └── rest/
│   │   │           ├── AuthController.java
│   │   │           ├── BookingController.java
│   │   │           ├── FlightController.java
│   │   │           ├── GlobalExceptionHandler.java
│   │   │           └── UserController.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── tests/
│   └── postman/
│       ├── Fly-Away-Travel.postman_collection.json
│       └── Fly-Away-Travel.postman_environment.json
├── docker-compose.yaml
├── pom.xml
├── mvnw
├── mvnw.cmd
├── .gitignore
└── README.md
```

---

## ✅ Requisitos Previos

Antes de empezar, asegúrate de tener instalado:

- ☕ **Java 21+** → [Descargar Temurin 21](https://adoptium.net/temurin/releases/?version=21)
- 🐳 **Docker Desktop** → [Descargar Docker](https://www.docker.com/products/docker-desktop)
- 💡 **IntelliJ IDEA** → [Descargar IntelliJ](https://www.jetbrains.com/idea/download/)
- 📮 **Postman** → [Descargar Postman](https://www.postman.com/downloads/)

Verifica las instalaciones con:

```bash
java -version
docker --version
```

---

## 🚀 Creación del Proyecto Paso a Paso

### Paso 1 · Crear el proyecto en Spring Initializr

Ve a <https://start.spring.io> y configura:

| Campo | Valor |
|---|---|
| Project | Maven |
| Language | Java |
| Spring Boot | 4.1.1 |
| Group | `pe.edu.utec` |
| Artifact | `flyaway-api` |
| Name | `flyaway-api` |
| Package name | `pe.edu.utec.flyaway.api` |
| Packaging | Jar |
| Java | 21 |

Agrega todas las [dependencias](#-dependencias-del-pomxml) listadas abajo y haz clic en **GENERATE** para descargar el `.zip`.

### Paso 2 · Descomprimir y abrir en IntelliJ

1. Descomprime el `.zip`.
2. Mueve el contenido a `C:\Users\Usuario\IdeaProjects\flyaway-api-v2`.
3. **File → Open** → selecciona `pom.xml`.
4. Haz clic en **Open as Project**.

### Paso 3 · Cargar Maven

Si IntelliJ no carga las dependencias automáticamente:

- **View → Tool Windows → Maven** → clic en 🔄 **Reload All Maven Projects**
- O bien: **File → Invalidate Caches / Restart**
- O desde la terminal:

```bash
./mvnw clean compile
```

### Paso 4 · Estructura de paquetes

Crea los siguientes paquetes dentro de `pe.edu.utec.flyaway.api`:

```text
domain.model
dto.request
dto.response
application.service
infrastructure.config
infrastructure.repository
infrastructure.rest
```

> 💡 Clic derecho sobre `pe.edu.utec.flyaway.api` → **New → Package** → escribe el nombre.

### Paso 5 · Configuración de la base de datos

Ver sección [Configuración](#%EF%B8%8F-configuración).

### Paso 6 · Escribir el código

Ver sección [Misiones Implementadas](#-misiones-implementadas).

### Paso 7 · Probar con Postman

Ver sección [Testing con Postman](#-testing-con-postman).

---

## 📦 Dependencias del `pom.xml`

### Dependencias principales

#### 1. Spring Boot Starter Web

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

> Incluye Tomcat embebido, Spring MVC y Jackson. Es la base para crear APIs REST.

#### 2. Spring Boot Starter Data JPA

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

> Incluye Hibernate, Spring Data JPA y JDBC. Permite mapear entidades Java a tablas de PostgreSQL.

#### 3. Spring Boot Starter Security

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

> Incluye Spring Security. Permite autenticación con JWT y control de acceso a endpoints.

#### 4. Spring Boot Starter Validation

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

> Incluye Hibernate Validator. Permite usar `@NotBlank`, `@Email`, `@Pattern`, `@Size`, `@Min`, etc.

#### 5. PostgreSQL Driver

```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
```

> Driver JDBC para conectarse a PostgreSQL.

#### 6. Lombok

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```

> Genera getters, setters, constructores, builders, etc. con anotaciones (`@Getter`, `@Builder`, ...).

> ⚠️ **Importante:** Lombok requiere configuración adicional en el `pom.xml`:

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <configuration>
                <annotationProcessorPaths>
                    <path>
                        <groupId>org.projectlombok</groupId>
                        <artifactId>lombok</artifactId>
                    </path>
                </annotationProcessorPaths>
            </configuration>
        </plugin>
    </plugins>
</build>
```

#### 7. JJWT (JWT)

```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.13.0</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.13.0</version>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.13.0</version>
    <scope>runtime</scope>
</dependency>
```

> Genera y valida tokens JWT.

#### 8. Spring Boot Starter Test

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

> Incluye JUnit, Mockito y AssertJ para tests unitarios.

### Cómo agregar una dependencia

<details>
<summary><b>Opción A — Manualmente en <code>pom.xml</code></b></summary>

```xml
<dependency>
    <groupId>...</groupId>
    <artifactId>...</artifactId>
    <version>...</version>
</dependency>
```

</details>

<details>
<summary><b>Opción B — Spring Initializr</b></summary>

1. Ve a <https://start.spring.io>.
2. Clic en **ADD DEPENDENCIES** (`Ctrl + B`).
3. Busca y agrega la dependencia.
4. Regenera el proyecto.

</details>

<details>
<summary><b>Opción C — Desde IntelliJ</b></summary>

1. Abre `pom.xml`.
2. Escribe `<dependency>` y usa el autocompletado.

</details>

---

## ⚙️ Configuración

### 1. `application.properties`

```properties
spring.application.name=flyaway-api
server.port=8080

# PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5433/flyaway
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.open-in-view=false

# JWT
jwt.secret=flyaway-super-secret-key-for-lab-07-cs2031-utec-2026
jwt.expiration=86400000
```

### 2. `docker-compose.yaml`

```yaml
services:
  postgres:
    image: postgres:16-alpine
    container_name: flyaway-db
    environment:
      POSTGRES_DB: flyaway
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
    ports:
      - "5433:5432"
    volumes:
      - flyaway_data:/var/lib/postgresql/data

volumes:
  flyaway_data:
```

> ⚠️ Usamos el puerto **5433** para evitar conflictos con un PostgreSQL local.

---

## ▶️ Cómo Ejecutar

**1. Levantar PostgreSQL**

```bash
docker-compose up -d
docker ps
```

**2. Compilar**

```bash
./mvnw clean compile
```

**3. Arrancar la app**

```bash
./mvnw spring-boot:run
```

Debe mostrar:

```text
Started FlyawayApiApplication in X.XXX seconds
```

**4. Probar endpoints** → ver [Testing con Postman](#-testing-con-postman).

**5. Detener**

```bash
# Detener la app
Ctrl + C

# Detener PostgreSQL
docker-compose down
```

---

## 🔌 Endpoints Implementados

| # | Método | Endpoint | Protección | Descripción |
|:-:|:------:|----------|:----------:|-------------|
| 1 | `POST` | `/flights/create` | 🌐 Pública | Crear vuelo |
| 2 | `POST` | `/users/register` | 🌐 Pública | Registrar usuario |
| 3 | `POST` | `/auth/login` | 🌐 Pública | Login (JWT) |
| 4 | `GET` | `/flights/search` | 🔒 JWT | Buscar vuelos |
| 5 | `POST` | `/flights/book` | 🔒 JWT | Reservar vuelo |
| 5 | `GET` | `/flight/book/{id}` | 🔒 JWT | Ver reserva |
| 6 | – | `flight_booking_email_{id}.txt` | – | Email generado |

---

## 🎯 Misiones Implementadas

### ✅ Misión 1 — Crear Vuelo

**Endpoint:** `POST /flights/create`

**Constraints:**

- Todos los campos son requeridos.
- Número de vuelo: solo `A-Z` y `0-9`, máximo 6 caracteres.
- Hora de salida < hora de llegada.
- Asientos disponibles > 0.
- Números de vuelo únicos.

**Ejemplo:**

```bash
curl -X POST http://localhost:8080/flights/create \
  -H "Content-Type: application/json" \
  -d '{
    "flightNumber": "AA984",
    "airline": "Fly Away Travel",
    "departureTime": "2027-06-01T10:00:00",
    "arrivalTime": "2027-06-01T14:00:00",
    "availableSeats": 50
  }'
```

**Respuesta:** `201 Created`

---

### ✅ Misión 2 — Registro de Usuarios

**Endpoint:** `POST /users/register`

**Constraints:**

- Email válido.
- Nombre y apellido: mínimo 1 letra mayúscula.
- Contraseña: mínimo 8 caracteres, al menos 1 letra y 1 número.
- Respuesta solo con `id`.

**Ejemplo:**

```bash
curl -X POST http://localhost:8080/users/register \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Ana",
    "lastName": "Torres",
    "email": "ana@utec.edu.pe",
    "password": "Password123"
  }'
```

**Respuesta:** `201 Created` con `{ "id": "..." }`

---

### ✅ Misión 3 — Autenticación JWT

**Endpoint:** `POST /auth/login`

**Ejemplo:**

```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "ana@utec.edu.pe",
    "password": "Password123"
  }'
```

**Respuesta:** `200 OK` con `{ "token": "eyJhbGc..." }`

---

### ✅ Misión 4 — Búsqueda de Vuelos

**Endpoint:** `GET /flights/search` 🔒 *(protegido)*

**Filtros:**

| Parámetro | Descripción |
|---|---|
| `flightNumber` | Número de vuelo (parcial) |
| `airline` | Nombre de aerolínea (parcial) |
| `departureFrom` / `departureTo` | Rango de fechas de salida |

**Ejemplo:**

```bash
curl -X GET "http://localhost:8080/flights/search?flightNumber=AA" \
  -H "Authorization: Bearer <token>"
```

---

### ✅ Misión 5 — Reservar Vuelo

**Endpoints:**

- `POST /flights/book` 🔒
- `GET /flight/book/{id}` 🔒

**Ejemplo:**

```bash
curl -X POST http://localhost:8080/flights/book \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{"flightId": "<id-del-vuelo>"}'
```

---

### ✅ Misión 6 — Email de Confirmación

**Archivo generado:** `flight_booking_email_{booking_id}.txt`

**Contenido:**

```text
Passenger: Ana Torres
Flight: AA984
Departure: 2027-06-01T10:00:00
Arrival: 2027-06-01T14:00:00
Booking Date: 2026-09-30T18:50:14.4043434
```

---

## 🧪 Testing con Postman

**1. Importar la colección**

**File → Import** y sube:

- `tests/postman/Fly-Away-Travel.postman_collection.json`
- `tests/postman/Fly-Away-Travel.postman_environment.json`

**2. Seleccionar el environment**

Arriba a la derecha: **"Fly Away Travel - Local"**.

**3. Correr los tests**

Clic en la colección → ▶️ **Run** → **Run Fly Away Travel**.

**4. Resultado esperado**

```text
✅ 43 Passed
❌ 0 Failed
```

---

## 🐛 Errores Comunes y Soluciones

<details>
<summary><b>Error 1:</b> <code>Port 8080 was already in use</code></summary>

**Causa:** otra instancia de la app está corriendo.

**Solución:**

```bash
taskkill /IM java.exe /F
./mvnw spring-boot:run
```

</details>

<details>
<summary><b>Error 2:</b> <code>Connection to localhost:5433 refused</code></summary>

**Causa:** PostgreSQL no está corriendo.

**Solución:**

```bash
docker-compose up -d
docker ps
```

</details>

<details>
<summary><b>Error 3:</b> <code>cannot find symbol: method getX()</code></summary>

**Causa:** Lombok no está configurado como annotation processor.

**Solución:** agrega al `pom.xml`:

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <configuration>
        <annotationProcessorPaths>
            <path>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
            </path>
        </annotationProcessorPaths>
    </configuration>
</plugin>
```

</details>

<details>
<summary><b>Error 4:</b> <code>Cannot resolve symbol 'springframework'</code></summary>

**Causa:** Maven no ha cargado las dependencias.

**Solución:**

1. **View → Tool Windows → Maven**
2. Clic en 🔄 **Reload All Maven Projects**
3. O: **File → Invalidate Caches / Restart**

</details>

<details>
<summary><b>Error 5:</b> <code>Class 'X' is public, should be declared in a file named 'X.java'</code></summary>

**Causa:** el archivo se llama `x.java` (minúscula) pero la clase es `X` (mayúscula).

**Solución:** clic derecho → **Refactor → Rename File** → `X.java`.

</details>

<details>
<summary><b>Error 6:</b> <code>function lower(bytea) does not exist</code></summary>

**Causa:** JPQL no puede inferir el tipo del parámetro en PostgreSQL.

**Solución:** usa `nativeQuery = true` con `CAST`:

```java
@Query(value = """
    SELECT * FROM flights f
    WHERE (CAST(:flightNumber AS text) IS NULL
           OR LOWER(f.flight_number) LIKE LOWER(CONCAT('%', CAST(:flightNumber AS text), '%')))
    """, nativeQuery = true)
```

</details>

<details>
<summary><b>Error 7:</b> <code>401 Unauthorized</code> en endpoints públicos</summary>

**Causa:** Spring Security bloquea todo por defecto.

**Solución:** configura `SecurityConfig`:

```java
.requestMatchers("/flights/create", "/users/register", "/auth/login").permitAll()
.anyRequest().authenticated()
```

</details>

<details>
<summary><b>Error 8:</b> <code>403 Forbidden</code> con token válido</summary>

**Causa:** el header `Authorization` está mal escrito.

**Solución:** verifica que sea exactamente:

```text
Authorization: Bearer <token>
```

- `Bearer` con **B** mayúscula
- Un espacio entre `Bearer` y el token
- El token completo (sin cortes)

</details>

<details>
<summary><b>Error 9:</b> <code>Schema-validation: missing table</code></summary>

**Causa:** Hibernate no creó las tablas.

**Solución:** verifica en `application.properties`:

```properties
spring.jpa.hibernate.ddl-auto=update
```

</details>

<details>
<summary><b>Error 10:</b> <code>Cannot resolve symbol 'User'</code></summary>

**Causa:** el archivo se llama `user.java` (minúscula).

**Solución:** renombra el archivo a `User.java`.

</details>

---

## 📚 Recursos Adicionales

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/index.html)
- [Spring Security Documentation](https://docs.spring.io/spring-security/reference/index.html)
- [Spring Data JPA Documentation](https://docs.spring.io/spring-data/jpa/reference/)
- [JJWT Documentation](https://github.com/jwtk/jjwt)
- [Postman Learning Center](https://learning.postman.com/)


---

<div align="center">

© 2026 Departamento de Ciencia de la Computación · Universidad de Ingeniería y Tecnología

</div>
