package com.inditex.controller;

import com.inditex.model.Producto;
import com.inditex.model.Tienda;
import com.inditex.repository.TiendaRepository;
import com.inditex.service.RecomendacionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recomendaciones")
public class RecomendacionController {
    private final TiendaRepository tiendaRepository;
    private final RecomendacionService recomendacionService;

    public RecomendacionController(TiendaRepository tiendaRepository, RecomendacionService recomendacionService) {
        this.tiendaRepository = tiendaRepository;
        this.recomendacionService = recomendacionService;
    }

    @GetMapping("/{tiendaId}")
    public List<Producto> recomendar(@PathVariable Long tiendaId) {
        Tienda tienda = tiendaRepository.findById(tiendaId).orElseThrow();
        return recomendacionService.recomendarProductos(tienda);
    }
}