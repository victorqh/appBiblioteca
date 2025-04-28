package com.vquispeh.appBiblioteca.ui.books;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.vquispeh.appBiblioteca.R;
import com.vquispeh.appBiblioteca.data.local.appDB;
import com.vquispeh.appBiblioteca.data.local.LibroRepoImpl;
import com.vquispeh.appBiblioteca.domain.usecase.GetAllLibros;
import com.vquispeh.appBiblioteca.domain.model.Libro;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvBooks;
    private BookAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvBooks = findViewById(R.id.rvBooks);
        rvBooks.setLayoutManager(new LinearLayoutManager(this));

        // Capa de datos y caso de uso
        appDB db = new appDB(this);
        GetAllLibros getAll = new GetAllLibros(
                new LibroRepoImpl(db)
        );

        List<Libro> lista = getAll.execute();
        adapter = new BookAdapter(lista);
        rvBooks.setAdapter(adapter);
    }
}