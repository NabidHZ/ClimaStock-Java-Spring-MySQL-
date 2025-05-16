Optimizador de Rotación de Stock por Clima Local

La idea es crear una app que recomiende a las tiendas físicas de Inditex qué productos destacar según el clima de su ciudad. Por ejemplo: si en Sevilla va a hacer mucho calor, el sistema recomendaría priorizar camisetas o vestidos. Si en Bilbao va a llover, mostraría chubasqueros o prendas más abrigadas.

Cómo funciona:

— Tú cargas el catálogo de productos con sus categorías y estaciones (por ejemplo, primavera, verano...).
— El sistema usa una fuente de datos meteorológicos abierta (como Open-Meteo o WeatherAPI, algunas tienen modo sin API-key).
— Con una lógica simple (si temperatura > 25, sugerir ropa de verano...), el sistema genera un informe por tienda.
— Se muestra por una API REST que el equipo de logística podría usar.

Ambos controladores gestionan recomendaciones de productos por tienda, pero tienen propósitos distintos:  
RecomendacionController (@RestController): Expone un endpoint REST (/recomendaciones/{tiendaId}) que devuelve una lista de productos recomendados en formato JSON para una tienda concreta. Es útil para integraciones API o consumo por otras aplicaciones.  
RecomendacionesWebController (@Controller): Gestiona la vista web /recomendaciones usando Thymeleaf. Permite seleccionar una tienda desde un formulario y muestra los productos recomendados en una página HTML. Es para uso interactivo en la web.
