package com.inditex.controller;

import com.inditex.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    // Endpoint de prueba: /weather/test?lat=37.39&lon=-5.99
    @GetMapping("/test")
    public ResponseEntity<String> testApi(
            @RequestParam String lat,
            @RequestParam String lon) {

        // Devuelve directamente el JSON completo de Open-Meteo
        String rawJson = weatherService.fetchRawForecast(lat, lon);
        return ResponseEntity.ok(rawJson);
    }

    // Endpoint real: /weather/temperature?lat=...&lon=...
    @GetMapping("/temperature")
    public ResponseEntity<Double> getMaxTemperature(
            @RequestParam String lat,
            @RequestParam String lon) {

        double temp = weatherService.obtenerTemperaturaMaxima(lat, lon);
        return ResponseEntity.ok(temp);
    }
}
