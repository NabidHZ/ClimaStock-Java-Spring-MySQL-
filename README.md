# Optimizador de Rotación de Stock por Clima Local

Este proyecto es una aplicación que recomienda a las tiendas físicas de Inditex qué productos destacar según el clima local de su ciudad. El objetivo es optimizar la rotación de stock adaptando la oferta en tienda a las condiciones meteorológicas previstas.

![image](https://github.com/user-attachments/assets/cb6dc5e7-1242-47c7-8e23-e11b8dae02e2)


## ¿Cómo funciona?

1. **Carga de Catálogo**  
   El usuario carga un catálogo de productos, donde cada producto tiene asociada una categoría y una estación recomendada (por ejemplo: primavera, verano, otoño, invierno).

2. **Obtención de Datos Meteorológicos**  
   El sistema consulta una fuente de datos meteorológicos abierta (por ejemplo, Open-Meteo o WeatherAPI) para obtener la previsión del clima en cada ciudad donde hay una tienda.

3. **Lógica de Recomendación**  
   Se aplica una lógica sencilla basada en reglas, por ejemplo:
   - Si la temperatura prevista es mayor a 25°C, se recomiendan productos de verano (camisetas, vestidos, etc.).
   - Si se prevé lluvia, se priorizan prendas impermeables o de abrigo.
   - Las reglas pueden adaptarse según las necesidades del negocio.

4. **Generación de Informe por Tienda**  
   Para cada tienda, el sistema genera una lista de productos recomendados en función del clima local.

5. **Exposición de Resultados**  
   - **API REST**: El sistema expone un endpoint REST para que otros sistemas (por ejemplo, logística) puedan consultar las recomendaciones en formato JSON.
   - **Interfaz Web**: También dispone de una interfaz web donde se puede seleccionar una tienda y visualizar las recomendaciones de productos en una página HTML.

## Arquitectura

- **Spring Boot**: Framework principal de la aplicación.
- **Controladores**:
  - `RecomendacionController` (`@RestController`):  
    Expone el endpoint `/recomendaciones/{tiendaId}` que devuelve las recomendaciones en formato JSON para una tienda concreta. Pensado para integraciones y consumo por otras aplicaciones.
  - `RecomendacionesWebController` (`@Controller`):  
    Gestiona la vista web `/recomendaciones` usando Thymeleaf. Permite seleccionar una tienda desde un formulario y muestra los productos recomendados en una página HTML.
- **Servicios**:
  - Servicio de recomendación que implementa la lógica de negocio.
  - Servicio de integración con la API meteorológica.
- **Modelo de Datos**:
  - Productos, tiendas, categorías, estaciones, etc.

## Ejemplo de Uso

1. **Cargar catálogo**:  
   Sube un archivo con los productos y sus atributos (categoría, estación, etc.).

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
   Accede a `/recomendaciones`, selecciona la tienda y visualiza los productos sugeridos.

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

## Tecnologías utilizadas

- Java 17+
- Spring Boot
- Thymeleaf
- API meteorológica (Open-Meteo, WeatherAPI, etc.)

## Autor

Nabid HZ
---
