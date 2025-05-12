package com.inditex.repository;

import com.inditex.model.PrediccionTiempo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PrediccionTiempoRepository extends JpaRepository<PrediccionTiempo, Long> {
    // Opcional: buscar predicciones por tienda y fecha
    List<PrediccionTiempo> findByTiendaIdAndFecha(Long tiendaId, LocalDate fecha);
}