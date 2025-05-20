# ClimaStock

Aplicación web para la gestión y recomendación de productos en tiendas físicas de Inditex, basada en la predicción meteorológica local. Desarrollada en Java, Spring Boot, Thymeleaf y MySQL.

## Tabla de contenidos

- [¿Qué es este repositorio?](#qué-es-este-repositorio)
- [Requisitos](#requisitos)
- [Instalación](#instalación)
- [Guía de uso](#guía-de-uso)
- [Estructura del proyecto](#estructura-del-proyecto)
- [Mejoras futuras](#mejoras-futuras)
- [Autores](#autores)

---

## ¿Qué es este repositorio?

ClimaStock recomienda a las tiendas físicas de Inditex qué productos destacar según el clima previsto en su ciudad. Por ejemplo, si en Sevilla va a hacer calor, el sistema sugiere priorizar camisetas o vestidos; si en Bilbao va a llover, recomienda chubasqueros o prendas impermeables.

El sistema permite:
- Cargar y gestionar el catálogo de productos (con categorías, estación, sección, si es impermeable, imagen, etc.).
- Gestionar tiendas físicas con su localización.
- Obtener recomendaciones automáticas de productos por tienda, según la previsión meteorológica (temperatura y lluvia) usando la API de Open-Meteo.
- Consultar recomendaciones tanto por web (Thymeleaf) como por API REST.

## Requisitos

- Java 17 o superior
- Maven 3.8+
- MySQL 8.x
- Navegador web moderno

## Instalación

1. Clona este repositorio:
   ```bash
   git clone https://github.com/NabidXD/ClimaStock.git
   cd ClimaStock
