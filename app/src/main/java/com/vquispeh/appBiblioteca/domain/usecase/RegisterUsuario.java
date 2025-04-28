package com.vquispeh.appBiblioteca.domain.usecase;

import com.vquispeh.appBiblioteca.domain.model.Usuario;
import com.vquispeh.appBiblioteca.domain.repository.UsuarioRepo;

public class RegisterUsuario {
    private final UsuarioRepo repo;

    public RegisterUsuario(UsuarioRepo repo) {
        this.repo = repo;
    }

    public boolean execute(String nombre, String email, String pass) {
        Usuario u = new Usuario(nombre, email, pass);
        return repo.register(u);
    }
}