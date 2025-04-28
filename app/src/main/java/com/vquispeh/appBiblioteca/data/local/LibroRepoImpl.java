package com.vquispeh.appBiblioteca.data.local;

import android.database.Cursor;
import com.vquispeh.appBiblioteca.domain.model.Libro;
import com.vquispeh.appBiblioteca.domain.repository.LibroRepo;
import java.util.ArrayList;
import java.util.List;

public class LibroRepoImpl implements LibroRepo {
    private final appDB db;

    public LibroRepoImpl(appDB db) {
        this.db = db;
    }

    @Override
    public List<Libro> getAll() {
        Cursor c = db.getAllLibros();
        List<Libro> lista = new ArrayList<>();

        //Indices de columnas una vez
        int idxId     = c.getColumnIndex(appDB.C_LIBRO_ID);
        int idxTitulo = c.getColumnIndex(appDB.C_LIBRO_TITULO);
        int idxAutor  = c.getColumnIndex(appDB.C_LIBRO_AUTOR);
        int idxDisp   = c.getColumnIndex(appDB.C_LIBRO_DISP);

        while (c.moveToNext()) {
            int id        = c.getInt(idxId);
            String titulo = c.getString(idxTitulo);
            String autor  = c.getString(idxAutor);
            boolean dispo = c.getInt(idxDisp) == 1;
            lista.add(new Libro(id, titulo, autor, dispo));
        }
        c.close();
        return lista;
    }
}
