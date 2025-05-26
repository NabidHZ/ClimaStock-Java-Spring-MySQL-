package com.inditex.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;
import org.json.JSONArray;



@Service
public class WeatherService {

    private final RestTemplate restTemplate;

    public WeatherService() {
        this.restTemplate = new RestTemplate();
    }

    public String fetchRawForecast(String lat, String lon) {
        String url = String.format(
                "https://api.open-meteo.com/v1/forecast?latitude=%s&longitude=%s&daily=temperature_2m_max,precipitation_sum&timezone=Europe/Madrid",
                lat, lon);
        return restTemplate.getForObject(url, String.class);
    }

    public double obtenerTemperaturaMaxima(String lat, String lon) {
        String rawJson = fetchRawForecast(lat, lon);
        JSONObject json = new JSONObject(rawJson);
        JSONArray temps = json.getJSONObject("daily").getJSONArray("temperature_2m_max");
        return temps.getDouble(0); // Primer día
    }

    // En WeatherService.java
    // src/main/java/com/inditex/service/WeatherService.java
    public boolean hayPrevisionLluvia(String lat, String lon, java.time.LocalDate fecha) {
        String rawJson = fetchRawForecast(lat, lon);
        org.json.JSONObject json = new org.json.JSONObject(rawJson);
        org.json.JSONArray rain = json.getJSONObject("daily").getJSONArray("precipitation_sum");
        org.json.JSONArray fechas = json.getJSONObject("daily").getJSONArray("time");
        for (int i = 0; i < fechas.length(); i++) {
            if (fecha.toString().equals(fechas.getString(i))) {
                return rain.getDouble(i) > 0;
            }
        }
        // Si no se encuentra la fecha, devuelve la del primer día
        return rain.getDouble(0) > 0;
    }

    //Recomendaciones en 7 dias
    public double obtenerTemperaturaMaxima(String lat, String lon, java.time.LocalDate fecha) {
        String rawJson = fetchRawForecast(lat, lon);
        org.json.JSONObject json = new org.json.JSONObject(rawJson);
        org.json.JSONArray temps = json.getJSONObject("daily").getJSONArray("temperature_2m_max");
        org.json.JSONArray fechas = json.getJSONObject("daily").getJSONArray("time");
        for (int i = 0; i < fechas.length(); i++) {
            if (fecha.toString().equals(fechas.getString(i))) {
                return temps.getDouble(i);
            }
        }
        // Si no se encuentra la fecha, devuelve la del primer día
        return temps.getDouble(0);


    }
}