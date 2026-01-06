# 📚 Challenge Literalura

Aplicación de consola desarrollada en Java con Spring Boot que permite buscar, registrar y gestionar libros y autores utilizando la API pública de [Gutendex](https://gutendex.com/).

## 🚀 Características

- ✅ Buscar libros por título desde la API de Gutendex
- ✅ Listar todos los libros registrados en la base de datos
- ✅ Listar autores registrados con sus libros
- ✅ Buscar autores vivos en un año determinado
- ✅ Filtrar libros por idioma (Español/Inglés)
- ✅ Persistencia de datos con PostgreSQL
- ✅ Interfaz de consola interactiva

## 🛠️ Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 4.0.1**
- **Spring Data JPA**
- **PostgreSQL**
- **Jackson** (para manejo de JSON)
- **Maven** (gestor de dependencias)

## 📋 Requisitos Previos

Antes de ejecutar la aplicación, asegúrate de tener instalado:

- Java 17 o superior
- PostgreSQL
- Maven (opcional, se incluye el wrapper `mvnw`)

## ⚙️ Configuración

### 1. Base de Datos

Crea una base de datos en PostgreSQL:

```sql
CREATE DATABASE literalura;
```

### 2. Configuración de Propiedades

Edita el archivo `src/main/resources/application.properties` con tus credenciales de PostgreSQL:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```

## 🏃 Ejecución

### Con Maven Wrapper (Linux/Mac):

```bash
./mvnw spring-boot:run
```

### Con Maven Wrapper (Windows):

```bash
mvnw.cmd spring-boot:run
```

### Con Maven instalado:

```bash
mvn spring-boot:run
```

## 📖 Uso de la Aplicación

Al iniciar la aplicación, se mostrará el siguiente menú:

```
Bienvenido a la app de Literalura!!!
Por favor selecciona una de las siguientes opciones:
1 - Buscar libro por titulo
2 - Listar libros registrados
3 - Listar autores registrados
4 - Listar autores vivos en un determinado anio
5 - Listar libros por idioma
0 - Salir
```

### Funcionalidades:

1. **Buscar libro por título**: Busca un libro en la API de Gutendex y lo guarda en la base de datos
2. **Listar libros registrados**: Muestra todos los libros almacenados localmente
3. **Listar autores registrados**: Muestra todos los autores con sus libros asociados
4. **Listar autores vivos en un determinado año**: Filtra autores que estaban vivos en el año especificado
5. **Listar libros por idioma**: Filtra libros por idioma (es: Español, en: Inglés)

## 📁 Estructura del Proyecto

```
src/main/java/com/example/challenge_literalura/
├── model/
│   ├── Autor.java          # Entidad Autor
│   └── Libro.java          # Entidad Libro
├── dto/
│   ├── DatosAutor.java     # DTO para datos de autor
│   ├── DatosLibro.java     # DTO para datos de libro
│   └── Results.java        # DTO para resultados de API
├── repository/
│   ├── AutorRepository.java
│   └── LibroRepository.java
├── service/
│   ├── AutorService.java
│   ├── LibroService.java
│   ├── ConsumoAPI.java     # Servicio para consumir API
│   ├── ConvierteDatos.java # Servicio para convertir JSON
│   ├── IConvierteDatos.java
│   └── Menu.java           # Menú de consola
└── ChallengeLiteraluraApplication.java
```

## 🗄️ Modelo de Datos

### Entidad Libro
- `id`: Identificador único
- `titulo`: Título del libro
- `lenguaje`: Idioma del libro
- `numeroDescargas`: Número de descargas en Gutendex
- `autores`: Lista de autores (relación One-to-Many)

### Entidad Autor
- `id`: Identificador único
- `nombre`: Nombre del autor
- `anioNacimiento`: Año de nacimiento
- `anioFallecimiento`: Año de fallecimiento
- `libro`: Libro asociado (relación Many-to-One)

## 🌐 API Utilizada

Este proyecto utiliza la API de [Gutendex](https://gutendex.com/), que proporciona acceso a más de 70,000 libros del Proyecto Gutenberg.

Endpoint utilizado:
```
https://gutendex.com/books?search={titulo}
```

## 👨‍💻 Autor

Desarrollado como parte del Challenge de Alura LATAM - ONE (Oracle Next Education)

## 📝 Licencia

Este proyecto es de código abierto y está disponible para fines educativos.
