package com.vquispeh.appBiblioteca.domain.repository;

import com.vquispeh.appBiblioteca.domain.model.Libro;

import java.util.List;

public interface LibroRepo {
    List<Libro> getAll();
}

