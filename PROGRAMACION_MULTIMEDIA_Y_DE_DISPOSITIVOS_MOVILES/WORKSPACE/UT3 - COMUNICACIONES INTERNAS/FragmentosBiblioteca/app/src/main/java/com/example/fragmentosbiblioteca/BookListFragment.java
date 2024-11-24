package com.example.fragmentosbiblioteca;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

public class BookListFragment extends Fragment {

    private ArrayList<Book> books;

    public static BookListFragment newInstance(String param1, String param2) {
        BookListFragment fragment = new BookListFragment();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            books = getArguments().getParcelableArrayList("book_list");
        }

        if (books == null) {
            books = new ArrayList<>();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_book_list, container, false);

        // Contenedor dinámico para los libros
        LinearLayout linearLayoutContainer = root.findViewById(R.id.linearLayoutContainer);

        // Configurar el adaptador
        BookAdapter adapter = new BookAdapter(getContext(), books, linearLayoutContainer, view -> {
            // Recuperar el libro desde la etiqueta del TextView
            Book clickedBook = (Book) view.getTag();

            // Mostrar el fragmento de detalle para el libro seleccionado
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).mostrarFragmentoDetallesLibro(clickedBook);
            }
        });

        // Mostrar los libros
        adapter.mostrarLibros();

        return root;
    }



}
