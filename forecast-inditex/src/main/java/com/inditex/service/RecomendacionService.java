package com.inditex.service;

import com.inditex.model.Producto;
import com.inditex.model.Tienda;
import com.inditex.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RecomendacionService {
    private final ProductoRepository productoRepository;
    private final WeatherService weatherService;

    public RecomendacionService(ProductoRepository productoRepository, WeatherService weatherService) {
        this.productoRepository = productoRepository;
        this.weatherService = weatherService;
    }

    public List<Producto> recomendarProductos(Tienda tienda) {
        double temp = weatherService.obtenerTemperaturaMaxima(
                String.valueOf(tienda.getLatitud()), String.valueOf(tienda.getLongitud())
        );
        String estacion;
        if (temp > 25) {
            estacion = "verano";
        } else if (temp < 15) {
            estacion = "invierno";
        } else {
            estacion = "primavera";
        }
        return productoRepository.findByEstacion(estacion);
    }
}