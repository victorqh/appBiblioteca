package com.vquispeh.appBiblioteca.domain.usecase;

import com.vquispeh.appBiblioteca.domain.repository.UsuarioRepo;

public class LoginUsuario {
    private final UsuarioRepo repo;

    public LoginUsuario(UsuarioRepo repo) {
        this.repo = repo;
    }

    public boolean execute(String email, String pass) {
        return repo.login(email, pass);
    }
}