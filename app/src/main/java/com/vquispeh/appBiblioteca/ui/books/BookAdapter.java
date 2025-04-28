package com.vquispeh.appBiblioteca.ui.books;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.vquispeh.appBiblioteca.R;
import com.vquispeh.appBiblioteca.data.local.appDB;
import com.vquispeh.appBiblioteca.domain.model.Libro;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private final List<Libro> items;

    public BookAdapter(List<Libro> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Libro libro = items.get(position);
        holder.tvTitle.setText(libro.getTitulo());
        holder.tvAuthor.setText(libro.getAutor());
        holder.tvAvailable.setText(
                libro.isDisponible()
                        ? holder.itemView.getContext().getString(R.string.label_available)
                        : holder.itemView.getContext().getString(R.string.label_unavailable)
        );

        //SOLICITAMOS LIBRO SI ESTA DISPO
        holder.itemView.setOnClickListener(v -> {
            if (!libro.isDisponible()) {
                Toast.makeText(v.getContext(), "Este libro no está disponible", Toast.LENGTH_SHORT).show();
            } else {
                //ACTUALIZAR
                int rows = new appDB(v.getContext()).updateDisponibilidad(libro.getId(), false);
                if (rows > 0) {
                    libro.setDisponible(false);
                    notifyItemChanged(position);
                    Toast.makeText(v.getContext(),
                            "Has solicitado «" + libro.getTitulo() + "»", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(v.getContext(),
                            "Error al solicitar el libro", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class BookViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvAuthor, tvAvailable;

        BookViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            tvAvailable = itemView.findViewById(R.id.tvAvailable);
        }
    }
}
