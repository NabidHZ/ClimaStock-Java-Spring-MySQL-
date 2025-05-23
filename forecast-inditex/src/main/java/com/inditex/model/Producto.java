package com.inditex.model;

import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private String estacion;

    // src/main/java/com/inditex/model/Producto.java
    @Column(nullable = false)
    private Boolean impermeable;


    @Column(name = "imagen")
    private String imagen; // Guardará el nombre del archivo

    @Column(nullable = false)
    private String seccion;

    @Column(nullable = false)
    private String genero;

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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public Boolean getImpermeable() {
        return impermeable;
    }

    public void setImpermeable(Boolean impermeable) {
        this.impermeable = impermeable;
    }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

}