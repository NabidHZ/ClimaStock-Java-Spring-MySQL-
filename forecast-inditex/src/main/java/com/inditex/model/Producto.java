package com.inditex.model;

import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @Column(nullable = false, unique = true)
    private Long id; // Número de referencia manual

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private String estacion;

    // src/main/java/com/inditex/model/Producto.java
    @Column(nullable = false)
    private Boolean impermeable;

    public Boolean getImpermeable() {
        return impermeable;
    }

    public void setImpermeable(Boolean impermeable) {
        this.impermeable = impermeable;
    }

    // Getters y setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEstacion() {
        return estacion;
    }

    public void setEstacion(String estacion) {
        this.estacion = estacion;
    }


}