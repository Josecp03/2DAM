package com.example.fragmentosbiblioteca;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class BookAdapter {

    private ArrayList<Book> books;
    private Context context;
    private LinearLayout container;
    private View.OnClickListener onClickListener;

    // Constructor
    public BookAdapter(Context context, ArrayList<Book> books, LinearLayout container, View.OnClickListener onClickListener) {
        this.context = context;
        this.books = books;
        this.container = container;
        this.onClickListener = onClickListener;
    }

    // Método para mostrar todos los libros en el contenedor
    public void mostrarLibros() {

        // Limpiar el contenedor antes de agregar vistas
        container.removeAllViews();

        // Recorrer cada uno de los libros
        for (Book book : books) {

            // Llamada al método para agregar la nueva vista
            nuevaVistaLibro(book);

        }
    }

    // Método para agregar una vista para un libro específico
    private void nuevaVistaLibro(Book libro) {

        // Crear un TextView dinámicamente para el libro
        TextView textView = new TextView(context);
        textView.setText(libro.getTitulo());
        textView.setTextSize(25);
        textView.setPadding(16, 16, 16, 16);
        textView.setTextColor(Color.WHITE);

        // Asignar el listener directo al TextView
        textView.setTag(libro); // Guardar el libro como etiqueta
        textView.setOnClickListener(onClickListener);

        // Agregar el TextView al contenedor
        container.addView(textView);

    }
}
