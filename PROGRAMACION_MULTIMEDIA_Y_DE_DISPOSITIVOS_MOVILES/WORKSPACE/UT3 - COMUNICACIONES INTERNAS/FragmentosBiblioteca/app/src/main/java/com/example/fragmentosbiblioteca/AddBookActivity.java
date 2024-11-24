package com.example.fragmentosbiblioteca;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddBookActivity extends AppCompatActivity {

    private Button btnGuardar = null;
    private EditText edtTitulo = null;
    private EditText edtAutor = null;
    private EditText edtAnioPublicacion = null;
    private EditText edtDescripcion = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_book);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Asignar valores XML
        btnGuardar = findViewById(R.id.buttonGuardar);
        edtTitulo = findViewById(R.id.editTextTitulo);
        edtAutor = findViewById(R.id.editTextTextAutor);
        edtAnioPublicacion = findViewById(R.id.editTextAnioPublicacion);
        edtDescripcion = findViewById(R.id.editTextDescripcion);

        // Listener para cuando se pulsa el botón de guardar
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Comprobar que los campos estén vacíos
                if (edtTitulo.getText().toString().isEmpty() || edtAutor.getText().toString().isEmpty() || edtAnioPublicacion.getText().toString().isEmpty() || edtDescripcion.getText().toString().isEmpty()) {

                    // Mostrar mensaje de error
                    Toast.makeText(AddBookActivity.this, "Ingresa todos los datos del libro antes de guardar", Toast.LENGTH_SHORT).show();

                } else {

                    // Crear el Libro con los datos del Edit Text
                    Book b = new Book(edtTitulo.getText().toString(), edtAutor.getText().toString(), Integer.parseInt(edtAnioPublicacion.getText().toString()), edtDescripcion.getText().toString());

                    // Crear el intent
                    Intent i = new Intent(AddBookActivity.this, MainActivity.class);

                    // Añadir el objeto
                    i.putExtra("obj1", b);

                    // Lanzar la actividad
                    startActivity(i);

                }

            }
        });


    }
}