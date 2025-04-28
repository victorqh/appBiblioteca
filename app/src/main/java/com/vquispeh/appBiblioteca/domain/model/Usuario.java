package com.vquispeh.appBiblioteca.domain.model;

public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private String password;

    // Constructor para lectura (id ya existe)
    public Usuario(int id, String nombre, String email, String password) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    // Constructor para creación (id se asigna en la BD)
    public Usuario(String nombre, String email, String password) {
        this(0, nombre, email, password);
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
