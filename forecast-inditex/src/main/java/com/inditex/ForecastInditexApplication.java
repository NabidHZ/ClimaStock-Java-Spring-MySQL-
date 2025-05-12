// src/main/java/com/inditex/ForecastInditexApplication.java
package com.inditex;

import com.inditex.model.Tienda;
import com.inditex.repository.TiendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ForecastInditexApplication implements CommandLineRunner {

    @Autowired
    private TiendaRepository tiendaRepo;

    public static void main(String[] args) {
        SpringApplication.run(ForecastInditexApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (tiendaRepo.count() == 0) {
            tiendaRepo.save(new Tienda("Zara Sevilla", "Sevilla", 37.3891, -5.9845));
            tiendaRepo.save(new Tienda("Bershka Madrid", "Madrid", 40.4168, -3.7038));
        }
    }
}