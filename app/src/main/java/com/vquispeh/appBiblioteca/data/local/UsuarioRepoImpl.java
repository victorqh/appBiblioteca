package com.vquispeh.appBiblioteca.data.local;

import com.vquispeh.appBiblioteca.domain.model.Usuario;
import com.vquispeh.appBiblioteca.domain.repository.UsuarioRepo;

public class UsuarioRepoImpl implements UsuarioRepo {
    private final appDB db;

    public UsuarioRepoImpl(appDB db) {
        this.db = db;
    }

    @Override
    public boolean register(Usuario u) {
        return db.insertUsuario(u);
    }

    @Override
    public boolean login(String email, String password) {
        return db.checkUsuario(email, password);
    }
}
