# ClimaStock: Optimizador de Rotación de Stock por Clima Local

Este proyecto es una aplicación web que recomienda a las tiendas físicas de Inditex qué productos destacar según el clima local de su ciudad. El objetivo es optimizar la rotación de stock adaptando la oferta en tienda a las condiciones meteorológicas previstas.

![image](https://github.com/user-attachments/assets/cb6dc5e7-1242-47c7-8e23-e11b8dae02e2)

## ¿Qué ofrece ClimaStock?

- Gestión visual y sencilla de catálogo de productos y tiendas físicas.
- Consulta del clima en tiempo real para cada tienda (integración con Open-Meteo).
- Recomendaciones automáticas de productos según la previsión meteorológica (temperatura y lluvia).
- Optimización de la exposición de prendas y mejora de la experiencia en tienda.
- Carga y edición de productos con imágenes.
- Filtros por género y sección para recomendaciones más precisas.
- Interfaz web moderna y responsive con Thymeleaf y CSS personalizado.
- API REST para integración con otros sistemas.

## ¿Cómo funciona?

1. **Carga de Catálogo**  
   El usuario puede añadir y editar productos, cada uno con categoría, estación recomendada, género, sección, si es impermeable y una imagen asociada.
   ![image](https://github.com/user-attachments/assets/c1e1821c-9dd9-4077-a998-ba0d12574320)

2. **Gestión de Tiendas**  
   Se pueden crear, editar y eliminar tiendas físicas, cada una con su localización (latitud y longitud).

3. **Obtención de Datos Meteorológicos**  
   El sistema consulta Open-Meteo para obtener la previsión del clima (temperatura máxima y lluvia) en cada ciudad donde hay una tienda.
   ![image](https://github.com/user-attachments/assets/e196d0a7-27e8-499f-aa38-daff9564d097)

4. **Lógica de Recomendación**  
   Se aplican reglas automáticas:
    - Si la temperatura prevista es mayor a 25°C, se recomiendan productos de verano.
    - Si se prevé lluvia, se priorizan prendas impermeables.
    - Las reglas pueden adaptarse según las necesidades del negocio.

5. **Visualización de Recomendaciones**  
   Para cada tienda y día, el sistema genera una lista de productos recomendados en función del clima local, con filtros por género y sección.

6. **Exposición de Resultados**
    - **API REST**: Endpoint `/recomendaciones/{tiendaId}` que devuelve las recomendaciones en JSON para una tienda concreta.
    - **Interfaz Web**: Página `/recomendaciones` para seleccionar tienda, día y filtros, y ver los productos sugeridos de forma visual.
      ![image](https://github.com/user-attachments/assets/1b310103-075d-44ce-a353-ac5e8548e1d9)
      ![image](https://github.com/user-attachments/assets/0d6ee6d9-75fc-4bcd-b45b-272e260fdcc8)

## Arquitectura y Tecnologías

- **Java 17+**
- **Spring Boot** (backend y lógica de negocio)
- **Thymeleaf** (plantillas HTML)
- **CSS personalizado** (diseño visual y responsive)
- **API meteorológica**: Open-Meteo
- **Docker con MySQL** (persistencia de datos)
- **Gestión de imágenes**: subida y visualización de imágenes de productos

### Estructura de la aplicación
- **Controladores**:
    - `RecomendacionController` (`@RestController`):  
      Endpoint `/recomendaciones/{tiendaId}` para recomendaciones en JSON.
    - `RecomendacionesWebController` (`@Controller`):  
      Página `/recomendaciones` para recomendaciones visuales.
    - `ProductoController` y `TiendaController`: gestión CRUD de productos y tiendas.
- **Servicios**:
    - Servicio de recomendación (reglas de negocio).
    - Servicio de integración meteorológica.
- **Modelo de Datos**:
    - Productos, tiendas, categorías, estaciones, imágenes, etc.

## Ejemplo de Uso

1. **Gestionar catálogo y tiendas**:  
   Añade productos y tiendas desde la interfaz web.

2. **Consultar recomendaciones vía API**:
   ```
   GET /recomendaciones/{tiendaId}
   ```
   Respuesta:
   ```json
   [
     {
       "producto": "Camiseta básica",
       "categoria": "Verano",
       "motivo": "Temperatura alta"
     },
     ...
   ]
   ```

3. **Consultar recomendaciones vía Web**:  
   Accede a `/recomendaciones`, selecciona la tienda, el día y los filtros, y visualiza los productos sugeridos.

## Instalación y Ejecución

1. Clona el repositorio.
2. Configura las credenciales de la API meteorológica si es necesario.
3. Ejecuta la aplicación con:
   ```
   ./mvnw spring-boot:run
   ```
4. Accede a la interfaz web o consume la API REST.

## Personalización

- Puedes modificar las reglas de recomendación en el servicio correspondiente.
- Es posible cambiar la fuente de datos meteorológicos adaptando el servicio de integración.

## Autor

Nabid HZ
---
