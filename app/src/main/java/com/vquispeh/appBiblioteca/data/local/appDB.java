package com.vquispeh.appBiblioteca.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.vquispeh.appBiblioteca.domain.model.Usuario;

public class appDB extends SQLiteOpenHelper {
    private static final String NAME = "biblioteca.db";
    private static final int VERSION = 2;

    // Tablas y columnas
    public static final String T_USUARIO       = "usuario";
    public static final String C_USUARIO_ID    = "id";
    public static final String C_USUARIO_NOMBRE= "nombre";
    public static final String C_USUARIO_EMAIL = "email";
    public static final String C_USUARIO_PASS  = "password";

    public static final String T_LIBRO         = "libro";
    public static final String C_LIBRO_ID      = "id";
    public static final String C_LIBRO_TITULO  = "titulo";
    public static final String C_LIBRO_AUTOR   = "autor";
    public static final String C_LIBRO_DISP    = "disponible";

    public appDB(Context ctx) {
        super(ctx, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + T_USUARIO + " (" +
                C_USUARIO_ID    + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                C_USUARIO_NOMBRE+ " TEXT NOT NULL, " +
                C_USUARIO_EMAIL + " TEXT NOT NULL UNIQUE, " +
                C_USUARIO_PASS  + " TEXT NOT NULL"+
                ");");

        db.execSQL("CREATE TABLE " + T_LIBRO + " (" +
                C_LIBRO_ID     + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                C_LIBRO_TITULO + " TEXT NOT NULL, " +
                C_LIBRO_AUTOR  + " TEXT NOT NULL, " +
                C_LIBRO_DISP   + " INTEGER NOT NULL DEFAULT 1"+
                ");");

        ContentValues cv = new ContentValues();

        // Precarga de libros
        cv.clear();
        cv.put(C_LIBRO_TITULO, "Harry Potter y la piedra filosofal");
        cv.put(C_LIBRO_AUTOR,  "J.K. Rowling");
        db.insert(T_LIBRO, null, cv);

        cv.clear();
        cv.put(C_LIBRO_TITULO, "Cien años de soledad");
        cv.put(C_LIBRO_AUTOR,  "Gabriel García Márquez");
        db.insert(T_LIBRO, null, cv);

        cv.clear();
        cv.put(C_LIBRO_TITULO, "Harry Potter y la cámara secreta");
        cv.put(C_LIBRO_AUTOR,  "J.K. Rowling");
        db.insert(T_LIBRO, null, cv);

        cv.clear();
        cv.put(C_LIBRO_TITULO, "World of Warcraft: Guerra de los Ancestros");
        cv.put(C_LIBRO_AUTOR,  "Christie Golden");
        db.insert(T_LIBRO, null, cv);

        cv.clear();
        cv.put(C_LIBRO_TITULO, "La Guerra del Pacífico");
        cv.put(C_LIBRO_AUTOR,  "Francisco García Calderón");
        db.insert(T_LIBRO, null, cv);

        cv.clear();
        cv.put(C_LIBRO_TITULO, "Recuerdos de la Guerra del Pacífico");
        cv.put(C_LIBRO_AUTOR,  "Ricardo Palma");
        db.insert(T_LIBRO, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + T_LIBRO);
        onCreate(db);
    }

    // Métodos de Usuario
    public boolean insertUsuario(Usuario u) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(C_USUARIO_NOMBRE, u.getNombre());
        cv.put(C_USUARIO_EMAIL,  u.getEmail());
        cv.put(C_USUARIO_PASS,   u.getPassword());
        long id = db.insert(T_USUARIO, null, cv);
        return id != -1;
    }

    public boolean checkUsuario(String email, String pass) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(
                T_USUARIO,
                new String[]{C_USUARIO_ID},
                C_USUARIO_EMAIL + "=? AND " + C_USUARIO_PASS + "=?",
                new String[]{email, pass},
                null, null, null);
        boolean ok = c.moveToFirst();
        c.close();
        return ok;
    }

    // Métodos de Libro
    public Cursor getAllLibros() {
        return getReadableDatabase().query(
                T_LIBRO, null, null, null, null, null,
                C_LIBRO_TITULO + " ASC");
    }

    // Actualiza disponibilidad de un libro
    public int updateDisponibilidad(int idLibro, boolean disponible) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(C_LIBRO_DISP, disponible ? 1 : 0);
        return db.update(T_LIBRO,
                cv,
                C_LIBRO_ID + "=?",
                new String[]{String.valueOf(idLibro)});
    }
}