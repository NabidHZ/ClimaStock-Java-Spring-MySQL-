package com.inditex.controller;

import com.inditex.model.Producto;
import com.inditex.model.Tienda;
import com.inditex.repository.TiendaRepository;
import com.inditex.service.RecomendacionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/recomendaciones")
public class RecomendacionesWebController {
    private final TiendaRepository tiendaRepository;
    private final RecomendacionService recomendacionService;

    public RecomendacionesWebController(TiendaRepository tiendaRepository, RecomendacionService recomendacionService) {
        this.tiendaRepository = tiendaRepository;
        this.recomendacionService = recomendacionService;
    }

    @GetMapping
    public String mostrarRecomendaciones(@RequestParam(value = "tiendaId", required = false) Long tiendaId, Model model) {
        List<Tienda> tiendas = tiendaRepository.findAll();
        model.addAttribute("tiendas", tiendas);

        if (tiendaId != null) {
            Tienda tienda = tiendaRepository.findById(tiendaId).orElse(null);
            if (tienda != null) {
                List<Producto> productos = recomendacionService.recomendarProductos(tienda);
                model.addAttribute("productos", productos);
            }
        }
        return "recomendaciones";
    }
}