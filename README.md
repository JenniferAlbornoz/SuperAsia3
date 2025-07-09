# SuperAsia3 🍜

## Descripción
SuperAsia3 es una aplicación de microservicios full-stack para la gestión de un restaurante asiático. El sistema permite gestionar pedidos, carrito de compras, inventario y más funcionalidades relacionadas con la operación de un restaurante.

## 🚀 Características Principales

- **Arquitectura de Microservicios**: Sistema distribuido y escalable
- **Gestión de Carrito**: Funcionalidad completa de carrito de compras
- **API RESTful**: Endpoints bien documentados y estructurados
- **Manejo de Excepciones**: Sistema robusto de manejo de errores
- **Base de Datos**: Integración con base de datos para persistencia
- **Configuración Flexible**: Configuración centralizada y fácil de mantener

## 🛠️ Tecnologías Utilizadas

### Backend
- **Java**: Lenguaje principal de desarrollo
- **Spring Boot**: Framework para desarrollo de aplicaciones Java
- **Spring Web**: Para crear APIs RESTful
- **Maven**: Gestión de dependencias y construcción del proyecto

### Base de Datos
- **JPA/Hibernate**: ORM para manejo de base de datos

### Herramientas de Desarrollo
- **Git**: Control de versiones
- **Maven**: Automatización de construcción
- **IDE**:  VS Code

## 📁 Ejemplo De Estructura del Proyecto

```
superasia3/
├── carrito/                    # Microservicio de carrito
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── prueba3/
│   │   │   │           └── carrito/
│   │   │   │               ├── CarritoApplication.java
│   │   │   │               └── config/
│   │   │   │                   └── GlobalExceptionHandler.java
│   │   │   └── resources/
│   │   └── test/
│   └── pom.xml
├── README.md
└── .gitignore
```

## 🚦 Instalación y Configuración

### Prerrequisitos
- Java 17 superior
- Maven 3.2 o superior
- Base de datos oracle sql (cloude)
- Git

### Pasos de Instalación

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/JenniferAlbornoz/SuperAsia3.git
   cd SuperAsia3
   ```

2. **Instalar dependencias**
   ```bash
   cd carrito
   mvn clean install
   ```

3. **Ejecutar la aplicación**
   ```bash
   mvn spring-boot:run
   ```
4.**La base de datos es configurable en aplicacion.propieties
## ⚙️ Configuración

### Base de Datos
Edita el archivo `src/main/resources/application.properties`:

```properties
spring.application.name=usuario
# Puerto del microservicio usuario
server.port=8081

# Configuración de la base de datos Oracle Cloud
spring.datasource.url=jdbc:oracle:thin:@tcps://adb.sa-santiago-1.oraclecloud.com:1522/g0124d9d856f5e7_pruebafullstack_high.adb.oraclecloud.com?retry_count=20&retry_delay=3
spring.datasource.username=ADMIN
spring.datasource.password=contraseña en codigo
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.OracleDialect

jwt.secret=contraseña en codigo
jwt.expiration=86400000

# Swagger/OpenAPI
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

## 📚 API Endpoints

### Carrito de Compras

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/carrito` | Obtener todos los items del carrito |
| POST | `/api/carrito` | Agregar item al carrito |
| PUT | `/api/carrito/{id}` | Actualizar item del carrito |
| DELETE | `/api/carrito/{id}` | Eliminar item del carrito |
| GET | `/api/carrito/total` | Obtener total del carrito |

## 🧪 Testing

### Ejecutar Tests
```bash
mvn test
```

### Ejecutar Tests con Cobertura
```bash
mvn test jacoco:report
```

## 🚀 Despliegue

### Desarrollo Local
```bash
mvn spring-boot:run
```

### Producción
```bash
mvn clean package
java -jar target/carrito-0.0.1-SNAPSHOT.jar
```


## 🤝 Contribución

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/miramadev`)
3. Commit tus cambios (`git commit -m 'aporte dev'`)
4. Push a la rama (`git push origin feature/miramadev`)
5. Abre un Pull Request

## 📝 Convenciones de Código

- Usar camelCase para variables y métodos
- Usar PascalCase para clases
- Comentar código complejo
- Seguir las convenciones de Spring Boot
- Escribir tests para nuevas funcionalidades


## 👥 Autores

- **Jennifer Albornoz** - *Desarrollo Principal* - [JenniferAlbornoz](https://github.com/JenniferAlbornoz)

## 🙏 Agradecimientos

- Spring Boot community
- Contribuidores del proyecto
- Inspiración de proyectos similares

## 📞 Contacto

- **Email**: jc.albornoz.ortiz@gmail.com
- **GitHub**: [@JenniferAlbornoz](https://github.com/JenniferAlbornoz)

## 🔄 Changelog

### v1.0.0 (2024-07-08)
- ✨ Implementación inicial del microservicio de carrito
- 🛠️ Configuración de Spring Boot
- 📝 Documentación inicial
- 🔧 Manejo global de excepciones

---

⭐ **¡Si te gusta este proyecto, dale una estrella!** ⭐
