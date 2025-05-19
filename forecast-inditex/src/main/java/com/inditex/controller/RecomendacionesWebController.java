package com.inditex.controller;

import com.inditex.model.Producto;
import com.inditex.model.Tienda;
import com.inditex.repository.TiendaRepository;
import com.inditex.service.RecomendacionService;
import com.inditex.service.WeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/recomendaciones")
public class RecomendacionesWebController {
    private final TiendaRepository tiendaRepository;
    private final RecomendacionService recomendacionService;
    private final WeatherService weatherService;

    public RecomendacionesWebController(TiendaRepository tiendaRepository, RecomendacionService recomendacionService, WeatherService weatherService) {
        this.tiendaRepository = tiendaRepository;
        this.recomendacionService = recomendacionService;
        this.weatherService = weatherService;
    }

    @GetMapping
    public String mostrarRecomendaciones(@RequestParam(value = "tiendaId", required = false) Long tiendaId, Model model) {
        List<Tienda> tiendas = tiendaRepository.findAll();
        model.addAttribute("tiendas", tiendas);

        if (tiendaId != null) {
            Tienda tienda = tiendaRepository.findById(tiendaId).orElse(null);
            if (tienda != null) {
                boolean lluvia = weatherService.hayPrevisionLluvia(
                        String.valueOf(tienda.getLatitud()), String.valueOf(tienda.getLongitud()));
                double temperatura = weatherService.obtenerTemperaturaMaxima(
                        String.valueOf(tienda.getLatitud()), String.valueOf(tienda.getLongitud()));
                List<Producto> productos = recomendacionService.recomendarProductos(tienda);
                List<Producto> impermeables = productos.stream()
                        .filter(p -> Boolean.TRUE.equals(p.getImpermeable()))
                        .collect(Collectors.toList());
                model.addAttribute("productos", productos);
                model.addAttribute("lluvia", lluvia);
                model.addAttribute("temperatura", temperatura);
                model.addAttribute("impermeables", impermeables);
                model.addAttribute("selectedTiendaId", tiendaId);
            }
        }
        return "recomendaciones";
    }
}