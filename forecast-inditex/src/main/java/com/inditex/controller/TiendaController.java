package com.inditex.controller;

import com.inditex.model.Tienda;
import com.inditex.repository.TiendaRepository;
import com.inditex.service.WeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Controller
@RequestMapping("/tiendas")
public class TiendaController {

    private final TiendaRepository tiendaRepository;
    private final WeatherService weatherService;

    public TiendaController(TiendaRepository tiendaRepository, WeatherService weatherService) {
        this.tiendaRepository = tiendaRepository;
        this.weatherService = weatherService;
    }

    @GetMapping
    public String listarTiendas(Model model) {
        List<Tienda> tiendas = tiendaRepository.findAll();
        Map<Long, Boolean> lluviaPorTienda = new HashMap<>();
        for (Tienda tienda : tiendas) {
            boolean lluvia = weatherService.hayPrevisionLluvia(
                    String.valueOf(tienda.getLatitud()), String.valueOf(tienda.getLongitud())
            );
            lluviaPorTienda.put(tienda.getId(), lluvia);
        }
        model.addAttribute("tiendas", tiendas);
        model.addAttribute("lluviaPorTienda", lluviaPorTienda);
        return "lista-tiendas";
    }

    @GetMapping("/nueva")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tienda", new Tienda());
        return "nueva-tienda";
    }

    @PostMapping("/guardar")
    public String guardarTienda(@ModelAttribute Tienda tienda) {
        tiendaRepository.save(tienda);
        return "redirect:/tiendas?exito";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Tienda tienda = tiendaRepository.findById(id).orElseThrow();
        model.addAttribute("tienda", tienda);
        return "editar-tienda";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarTienda(@PathVariable Long id, @ModelAttribute Tienda tienda) {
        tienda.setId(id);
        tiendaRepository.save(tienda);
        return "redirect:/tiendas?editado";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarTienda(@PathVariable Long id) {
        tiendaRepository.deleteById(id);
        return "redirect:/tiendas?eliminado";
    }
}