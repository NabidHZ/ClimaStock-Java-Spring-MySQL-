package com.inditex.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prediccion_tiempo")
public class PrediccionTiempo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(name = "temp_max", nullable = false)
    private double temperaturaMaxima;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tienda_id", nullable = false)
    private Tienda tienda;

    // getters y setters
}
