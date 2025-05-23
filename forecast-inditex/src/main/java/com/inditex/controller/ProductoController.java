package com.inditex.controller;

import com.inditex.model.Producto;
import com.inditex.repository.ProductoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoRepository productoRepository;

    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @GetMapping
    public String listarProductos(
            @RequestParam(value = "genero", required = false) String genero,
            @RequestParam(value = "seccion", required = false) String seccion,
            Model model) {
        List<Producto> productos;
        if (genero != null && !genero.isEmpty()) {
            if (seccion != null && !seccion.isEmpty()) {
                productos = productoRepository.findByGeneroAndSeccion(genero, seccion);
            } else {
                productos = productoRepository.findByGenero(genero);
            }
        } else {
            productos = productoRepository.findAll();
        }
        model.addAttribute("productos", productos);
        model.addAttribute("generoSeleccionado", genero);
        model.addAttribute("seccionSeleccionada", seccion);
        return "lista-productos";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("producto", new Producto());
        return "nuevo-producto";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute Producto producto, @RequestParam("imagenFile") MultipartFile imagenFile) throws IOException {
        if (!imagenFile.isEmpty()) {
            String extension = "";
            String originalName = imagenFile.getOriginalFilename();
            int i = originalName.lastIndexOf('.');
            if (i > 0) {
                extension = originalName.substring(i);
            }
            String nombreArchivo = System.currentTimeMillis() + extension;
            Path ruta = Paths.get("uploads/img/" + nombreArchivo);
            Files.copy(imagenFile.getInputStream(), ruta, StandardCopyOption.REPLACE_EXISTING);
            producto.setImagen(nombreArchivo);
        }
        Producto guardado = productoRepository.save(producto);
        return "redirect:/productos?exito&id=" + guardado.getId();
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Producto producto = productoRepository.findById(id).orElseThrow();
        model.addAttribute("producto", producto);
        return "editar-producto";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarProducto(@PathVariable Long id, @ModelAttribute Producto producto, @RequestParam(value = "imagenFile", required = false) MultipartFile imagenFile) throws IOException {
        Producto productoExistente = productoRepository.findById(id).orElseThrow();
        producto.setId(id);

        // Mantener la imagen anterior si no se sube una nueva
        if (imagenFile != null && !imagenFile.isEmpty()) {
            String extension = "";
            String originalName = imagenFile.getOriginalFilename();
            int i = originalName.lastIndexOf('.');
            if (i > 0) {
                extension = originalName.substring(i);
            }
            String nombreArchivo = System.currentTimeMillis() + extension;
            Path ruta = Paths.get("uploads/img/" + nombreArchivo);
            Files.copy(imagenFile.getInputStream(), ruta, StandardCopyOption.REPLACE_EXISTING);
            producto.setImagen(nombreArchivo);
        } else {
            producto.setImagen(productoExistente.getImagen());
        }

        productoRepository.save(producto);
        return "redirect:/productos?editado";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoRepository.deleteById(id);
        return "redirect:/productos?eliminado";
    }
}