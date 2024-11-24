package com.example.fragmentosbiblioteca;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnAccion;
    private static ArrayList<Book> books = new ArrayList<>();
    private boolean enDetallesLibro = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Asignar valores XML
        btnAccion = findViewById(R.id.buttonAccion);

        // Inicializar la lista de libros si está vacía
        if (books.isEmpty()) {
            inicializarLibrosEjemplo();
        }

        // Obtener el libro desde el intent
        if (getIntent().hasExtra("obj1")) {
            Book newBook = getIntent().getParcelableExtra("obj1");

            // Comprobar si el libro no es null antes de añadirlo
            if (newBook != null) {
                books.add(newBook);
            }
        }

        // Mostrar el fragmento inicial con la lista de libros
        mostrarFragmentoListaLibros();

        // Listener para cuando se pulse el botón de acción
        btnAccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Comprobar si actualmente estoy en el fragmento de detalles del libro
                if (enDetallesLibro) {

                    // Volver al anterior fragmento
                    getSupportFragmentManager().popBackStack();

                    // Enseñar la lista de fragmentos
                    mostrarFragmentoListaLibros();

                } else {

                    // Comprobar el número de libros que se han añadido ya
                    if (books.size() >= 7) {

                        // Mostrar mensaje de error cuando se intentan añadir más libros
                        Toast.makeText(MainActivity.this, "No se pueden agregar más libros.", Toast.LENGTH_SHORT).show();

                    } else {

                        // Crear el intent para iniciar la actividad de añadir libro
                        Intent intent = new Intent(MainActivity.this, AddBookActivity.class);

                        // Lanzar el intent
                        startActivity(intent);

                    }
                }

            }
        });

    }

    // Método para inicializar libros de ejemplo
    private void inicializarLibrosEjemplo() {
        books.add(new Book("El Quijote", "Miguel de Cervantes", 1605, "El Quijote es una novela escrita por Miguel de Cervantes que narra las aventuras de un hidalgo idealista..."));
        books.add(new Book("Cien Años de Soledad", "Gabriel García Márquez", 1967, "Cien Años de Soledad es una obra de Gabriel García Márquez que relata la historia de la familia Buendía..."));
        books.add(new Book("Orgullo y Prejuicio", "Jane Austen", 1813, "Orgullo y Prejuicio de Jane Austen es una novela clásica que explora las complejidades del amor..."));
    }

    // Método para mostrar la lista de los libros actual
    private void mostrarFragmentoListaLibros() {

        // Actualizar variable booleana
        enDetallesLibro = false;

        // Cambiar el texto del botón
        btnAccion.setText("Agregar Libro");

        // Crear el nuevo fragmento
        BookListFragment fragment = new BookListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("book_list", books);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView2, fragment)
                .commit();

    }

    // Método para mostrar los detalles del libro correspondiente
    public void mostrarFragmentoDetallesLibro(Book book) {

        // Actualizar variable booleana
        enDetallesLibro = true;

        // Cambiar el texto del botón
        btnAccion.setText("Volver");

        // Crear el fragmento
        BookDetailFragment detailFragment = new BookDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("selected_book", book);
        detailFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView2, detailFragment)
                .addToBackStack(null)
                .commit();

    }
}
