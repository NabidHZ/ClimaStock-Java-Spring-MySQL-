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
    public String mostrarRecomendaciones(
            @RequestParam(value = "tiendaId", required = false) Long tiendaId,
            @RequestParam(value = "genero", required = false) String genero,
            @RequestParam(value = "seccion", required = false) String seccion,
            Model model) {
        List<Tienda> tiendas = tiendaRepository.findAll();
        model.addAttribute("tiendas", tiendas);
        model.addAttribute("generoSeleccionado", genero);
        model.addAttribute("seccionSeleccionada", seccion);

        if (tiendaId != null) {
            Tienda tienda = tiendaRepository.findById(tiendaId).orElse(null);
            if (tienda != null) {
                boolean lluvia = weatherService.hayPrevisionLluvia(
                        String.valueOf(tienda.getLatitud()), String.valueOf(tienda.getLongitud()));
                double temperatura = weatherService.obtenerTemperaturaMaxima(
                        String.valueOf(tienda.getLatitud()), String.valueOf(tienda.getLongitud()));
                List<Producto> productos = recomendacionService.recomendarProductos(tienda);
                // Filtrado por género y sección igual que en lista-productos
                if (genero != null && !genero.isEmpty()) {
                    productos = productos.stream().filter(p -> genero.equals(p.getGenero())).collect(java.util.stream.Collectors.toList());
                    if (seccion != null && !seccion.isEmpty()) {
                        productos = productos.stream().filter(p -> seccion.equals(p.getSeccion())).collect(java.util.stream.Collectors.toList());
                    }
                }
                List<Producto> impermeables = productos.stream()
                        .filter(p -> Boolean.TRUE.equals(p.getImpermeable()))
                        .collect(java.util.stream.Collectors.toList());
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

