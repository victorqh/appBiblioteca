package com.vquispeh.appBiblioteca.domain.model;

import java.time.LocalDate;

public class Prestamo {
    private int id;
    private int usuarioId;
    private int libroId;
    private LocalDate fechaPrestamo;

    public Prestamo(int id, int usuarioId, int libroId, LocalDate fechaPrestamo) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.libroId = libroId;
        this.fechaPrestamo = fechaPrestamo;
    }

    public Prestamo(int usuarioId, int libroId, LocalDate fechaPrestamo) {
        this(0, usuarioId, libroId, fechaPrestamo);
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public int getLibroId() {
        return libroId;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setLibroId(int libroId) {
        this.libroId = libroId;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }
}
