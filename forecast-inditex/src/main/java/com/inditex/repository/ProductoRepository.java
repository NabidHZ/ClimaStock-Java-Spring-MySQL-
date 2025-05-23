// src/main/java/com/inditex/repository/ProductoRepository.java
package com.inditex.repository;

import com.inditex.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByEstacion(String estacion);
    List<Producto> findByGenero(String genero);
    List<Producto> findByGeneroAndSeccion(String genero, String seccion);
}