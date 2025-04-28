package com.vquispeh.appBiblioteca.domain.usecase;

import com.vquispeh.appBiblioteca.domain.model.Libro;
import com.vquispeh.appBiblioteca.domain.repository.LibroRepo;
import java.util.List;

public class GetAllLibros {
    private final LibroRepo repo;

    public GetAllLibros(LibroRepo repo) {
        this.repo = repo;
    }

    public List<Libro> execute() {
        return repo.getAll();
    }
}