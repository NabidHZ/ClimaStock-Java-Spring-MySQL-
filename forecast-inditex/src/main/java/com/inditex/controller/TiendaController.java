// src/main/java/com/inditex/controller/TiendaController.java
package com.inditex.controller;

import com.inditex.model.Tienda;
import com.inditex.repository.TiendaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tiendas")
public class TiendaController {

    private final TiendaRepository tiendaRepository;

    public TiendaController(TiendaRepository tiendaRepository) {
        this.tiendaRepository = tiendaRepository;
    }

    @GetMapping
    public String listarTiendas(Model model) {
        model.addAttribute("tiendas", tiendaRepository.findAll());
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