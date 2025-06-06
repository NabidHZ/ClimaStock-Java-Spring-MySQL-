// src/main/java/com/inditex/service/RecomendacionService.java
package com.inditex.service;

import com.inditex.model.Producto;
import com.inditex.model.Tienda;
import com.inditex.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecomendacionService {
    private final ProductoRepository productoRepository;
    private final WeatherService weatherService;

    public RecomendacionService(ProductoRepository productoRepository, WeatherService weatherService) {
        this.productoRepository = productoRepository;
        this.weatherService = weatherService;
    }

    // Método original (hoy)
    public List<Producto> recomendarProductos(Tienda tienda) {
        double temp = weatherService.obtenerTemperaturaMaxima(
                String.valueOf(tienda.getLatitud()), String.valueOf(tienda.getLongitud())
        );
        List<Producto> productos = productoRepository.findAll();
        return filtrarPorTemperatura(productos, temp);
    }

    // NUEVO: método para una fecha concreta
    public List<Producto> recomendarProductos(Tienda tienda, LocalDate fecha) {
        double temp = weatherService.obtenerTemperaturaMaxima(
                String.valueOf(tienda.getLatitud()), String.valueOf(tienda.getLongitud()), fecha
        );
        List<Producto> productos = productoRepository.findAll();
        return filtrarPorTemperatura(productos, temp);
    }

    // Lógica de filtrado por temperatura
    private List<Producto> filtrarPorTemperatura(List<Producto> productos, double temp) {
        return productos.stream()
                .filter(p -> {
                    String estacion = p.getEstacion().toLowerCase();
                    if (estacion.equals("invierno") && temp < 10) return true;
                    if (estacion.equals("otoño") && temp >= 10 && temp < 15) return true;
                    if (estacion.equals("primavera") && temp >= 15 && temp <= 25) return true;
                    if (estacion.equals("verano") && temp > 25) return true;
                    return false;
                })
                .collect(Collectors.toList());
    }
}