Rotación de Stock según el Clima Local
Proyecto desarrollado en Java/Spring Boot que ajusta dinámicamente el inventario de una tienda en función de la previsión meteorológica local.

Tabla de contenidos
¿Qué es este repositorio?

Requisitos

Software

API de Clima

Documentación

Guía de uso

Esquema del Hardware

Arquitectura del Software

Mejoras en un futuro

Autores

¿Qué es este repositorio?
Este proyecto —Rotación de Stock según el Clima Local— nace con el objetivo de optimizar la gestión de inventario en tiendas minoristas.
Mediante llamadas REST a una API meteorológica externa, calcula recomendaciones (p.ej., aumentar existencias de bebidas frías si se prevé calor) y sugiere acciones sobre el stock de productos.
Está implementado con Spring Boot, Spring Data JPA y una base de datos SQL, y ofrece una interfaz REST para consumo desde cualquier frontend.

Requisitos
Software
JDK 11+

Maven (o Gradle)

Base de datos relacional (MySQL, PostgreSQL o H2 en memoria)

IDE (IntelliJ IDEA, Eclipse, VSCode…)

API de Clima
OpenWeatherMap (u otro proveedor REST)

Clave de API válida

URL base configurada en application.properties

Documentación
✔️ UML del dominio y capas (ver /docs/Diagrama_UML.png)

✔️ Javadoc generado en /docs/javadoc/

✔️ Plan de pruebas unitarias e integrales (JUnit + Mockito)

✔️ README adicional con ejemplos de petición y respuestas JSON (/docs/API-usage.md)

Guía de uso
Clonar el repositorio

bash
Copiar
Editar
git clone https://github.com/NabidHZ/RotacionStock-ClimaLocal.git
cd RotacionStock-ClimaLocal
Configurar

Copiar src/main/resources/application-example.properties a application.properties

Rellenar datos de conexión a BD y API climatológica

Construir y ejecutar

bash
Copiar
Editar
mvn clean package
java -jar target/rotacion-stock-0.0.1-SNAPSHOT.jar
Probar endpoints

GET /api/productos — lista todos los productos

POST /api/productos — crea un producto

GET /api/recomendaciones?ciudad=Madrid — recibe sugerencias según temperatura

Para más ejemplos, consulta /docs/API-usage.md.

Esquema del Hardware
⚙️ Este proyecto no utiliza hardware físico, es una aplicación puramente software.
Mantener este apartado para la misma estructura: “Hardware = N/A”.

Arquitectura del Software

Capa Controller: expone endpoints REST (ProductController, WeatherController, RecommendationController).

Capa Service: lógica de negocio (ProductService, WeatherService, RecommendationService).

Capa Repository: persistencia con Spring Data JPA (ProductoRepository, PrediccionTiempoRepository, TiendaRepository).

Integración externa: llamada a la API de clima mediante RestTemplate o WebClient.

Base de datos: modelo relacional con tablas producto, tienda, prediccion_tiempo.

Mejoras en un futuro
➕ Incluir predicción de humedad y precipitación.

➕ Aprendizaje automático para calibrar recomendaciones según ventas históricas.

➕ Frontend SPA (Angular/React) con gráficos de rotación.

➕ Caché de peticiones climatológicas para reducir latencia.

➕ Autenticación OAuth2 / JWT para roles y permisos avanzados.

Autores
Nombre Apellido – @TuUsuarioGitHub

NahidHZ – @NabidHZ

