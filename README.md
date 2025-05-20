# Rotación de Stock según el Clima Local

Proyecto desarrollado en Java/Spring Boot que ajusta dinámicamente el inventario de una tienda en función de la previsión meteorológica local.

---

## Tabla de contenidos

1. [¿Qué es este repositorio?](#qué-es-este-repositorio)  
2. [Requisitos](#requisitos)  
   - [Software](#software)  
   - [API de Clima](#api-de-clima)  
3. [Documentación](#documentación)  
4. [Guía de uso](#guía-de-uso)  
5. [Esquema del Hardware](#esquema-del-hardware)  
6. [Arquitectura del Software](#arquitectura-del-software)  
7. [Mejoras en un futuro](#mejoras-en-un-futuro)  
8. [Autores](#autores)  

---

## ¿Qué es este repositorio?

Este proyecto —**Rotación de Stock según el Clima Local**— nace con el objetivo de optimizar la gestión de inventario en tiendas minoristas.  
Mediante llamadas REST a una API meteorológica externa, calcula recomendaciones (p.ej., aumentar existencias de bebidas frías si se prevé calor) y sugiere acciones sobre el stock de productos.  
Está implementado con **Spring Boot**, **Spring Data JPA** y una base de datos SQL, y ofrece una interfaz REST para consumo desde cualquier frontend.

---

## Requisitos

### Software

- JDK 11+  
- Maven (o Gradle)  
- Base de datos relacional (MySQL, PostgreSQL o H2 en memoria)  
- IDE (IntelliJ IDEA, Eclipse, VSCode…)  

### API de Clima

- **OpenWeatherMap** (u otro proveedor REST)  
- Clave de API válida  
- URL base configurada en `application.properties`  

---

## Documentación

- ✔️ **UML** del dominio y capas (ver `/docs/Diagrama_UML.png`)  
- ✔️ Javadoc generado en `/docs/javadoc/`  
- ✔️ Plan de pruebas unitarias e integrales (JUnit + Mockito)  
- ✔️ README adicional con ejemplos de petición y respuestas JSON (`/docs/API-usage.md`)

---

## Guía de uso

1. **Clonar el repositorio**  
   ```bash
   git clone https://github.com/NabidHZ/RotacionStock-ClimaLocal.git
   cd RotacionStock-ClimaLocal
