package com.example.fragmentosbiblioteca;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BookDetailFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    public static BookDetailFragment newInstance(String param1, String param2) {
        BookDetailFragment fragment = new BookDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_book_detail, container, false);

        // Obtener los datos del libro desde los argumentos
        Book selectedBook = getArguments().getParcelable("selected_book");

        // Asignar valores XML
        TextView titleView = root.findViewById(R.id.bookTitle);
        TextView authorView = root.findViewById(R.id.bookAuthor);
        TextView yearView = root.findViewById(R.id.bookYear);
        TextView descriptionView = root.findViewById(R.id.bookDescription);

        // Añadir los valores
        titleView.setText(selectedBook.getTitulo());
        authorView.setText(selectedBook.getAutor());
        yearView.setText(String.valueOf(selectedBook.getAnioPublicacion()));
        descriptionView.setText(selectedBook.getDescricpion());

        return root;


    }
}