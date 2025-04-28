package com.vquispeh.appBiblioteca.domain.repository;

import com.vquispeh.appBiblioteca.domain.model.Usuario;

public interface UsuarioRepo {
    boolean register(Usuario u);
    boolean login(String email, String password);
}
