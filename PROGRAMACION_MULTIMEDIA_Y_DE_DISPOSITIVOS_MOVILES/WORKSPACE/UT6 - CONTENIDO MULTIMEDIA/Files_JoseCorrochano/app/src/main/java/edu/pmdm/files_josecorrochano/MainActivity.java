package edu.pmdm.files_josecorrochano;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import android.database.Cursor;
import android.provider.OpenableColumns;

import edu.pmdm.files_josecorrochano.adapters.FileLineAdapter;

public class MainActivity extends AppCompatActivity {

    // Atributos
    private static final int PICK_FILE_REQUEST = 1;
    private Button btnSelectFiles = null;
    private Button btnInsertLine = null;
    private Button btnSaveFile = null;
    private EditText edtPath = null;
    private EditText edtInsertLine = null;
    private TextView txtCountWords = null;
    private RecyclerView recyclerView = null;
    private FileLineAdapter adapter;
    private Uri selectedFileUri;

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

        // Asignar valores del UI
        btnSelectFiles = findViewById(R.id.buttonSelectFile);
        btnInsertLine = findViewById(R.id.buttonInsertLine);
        btnSaveFile = findViewById(R.id.buttonSaveFile);
        edtPath = findViewById(R.id.editTextTextPath);
        edtInsertLine = findViewById(R.id.editTextTextInsertLine);
        recyclerView = findViewById(R.id.recyclerView);
        txtCountWords = findViewById(R.id.textViewCountWords);

        // Click cuando se pulsa el botón de seleccionar archivo
        btnSelectFiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Crear un Intent para abrir el selector de documentos
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

                // Filtrar solo archivos .txt
                intent.setType("text/plain");

                // Agregar flags para otorgar permisos de lectura y escritura sobre la URI seleccionada
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

                // Iniciar la actividad y esperar el resultado, identificándola con PICK_FILE_REQUEST
                startActivityForResult(intent, PICK_FILE_REQUEST);

            }
        });

        // Click cuando se pulsa el botón de insertar línea
        btnInsertLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Guardar en una variable lo que escribe el usuario en el editText
                String newLine = edtInsertLine.getText().toString().trim();

                // Comprobar si la línea está vacía o no
                if (!newLine.isEmpty()) {

                    // Añadir la línea al RecyclerView
                    adapter.addItem(newLine);

                    // Limpiar el editText
                    edtInsertLine.setText("");

                } else {

                    // Mostrar mensaje de error
                    Toast.makeText(MainActivity.this, "Por favor ingresa una línea", Toast.LENGTH_SHORT).show();

                }
            }
        });

        // Click cuando se pulsa el botón de guardar archivo
        btnSaveFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // LLamada para guardar el archivo
                saveFile();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Comoprobar que el código de solicitud corresponde a la selección de archivo y que el resultado fue satisfactorio
        if (requestCode == PICK_FILE_REQUEST && resultCode == RESULT_OK) {

            // Guardamos la URI original del archivo seleccionado
            selectedFileUri = data.getData();

            // Mostrar la ruta completa del archivo en el editText
            edtPath.setText(selectedFileUri.toString());

            // Llamada para leer el archivo
            readFile(selectedFileUri);

        }
    }

    // Leer el archivo seleccionado
    private void readFile(Uri fileUri) {

        // Crear una lista para almacenar las líneas leídas del archivo
        List<String> lines = new ArrayList<>();

        // Inicializar el contador de palabras en 0
        int wordCount = 0;

        // Intentar abrir y leer el archivo utilizando un InputStream y BufferedReader
        try (InputStream inputStream = getContentResolver().openInputStream(fileUri);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            // Declarar el atributo que contiene la línea
            String line;

            // Recorrer cada una de las líneas del archivo
            while ((line = br.readLine()) != null) {

                // Comprobar que la línea no esté vacía, después de eliminar espacios en blanco
                if (!line.trim().isEmpty()) {

                    // Añadir la línea a la lista
                    lines.add(line);

                    // Incrementar el contador de palabras sumando la cantidad de palabras en la línea, separadas por espacios
                    wordCount += line.split("\\s+").length;

                }

            }

        } catch (IOException e) {

            // Mostrar mensajes de error
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Error al leer el archivo", Toast.LENGTH_SHORT).show();
        }

        // Establecer un LinearLayoutManager
        recyclerView.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(this));


        // Inicializar el adaptador del RecyclerView
        adapter = new FileLineAdapter(lines, position -> adapter.removeItem(position), txtCountWords);

        // Asignar el adaptador al RecyclerView
        recyclerView.setAdapter(adapter);

        // Actualizar el TextView con el número total de palabras calculado
        txtCountWords.setText("Words: " + wordCount);

    }

    // Guardar el archivo
    private void saveFile() {

        // Intentar abrir un OutputStream para la URI seleccionada en modo escritura
        try (OutputStream outputStream = getContentResolver().openOutputStream(selectedFileUri, "wt")) {

            // Recorrer todas las líneas
            for (String line : adapter.getLines()) {

                // Escribir la línea seguida de un salto de línea en el OutputStream
                outputStream.write((line + "\n").getBytes());

            }

            // Mostrar un mensaje de éxito al usuario
            Toast.makeText(MainActivity.this, "Archivo actualizado correctamente", Toast.LENGTH_SHORT).show();


        } catch (IOException e) {

            // Mostrar mensajes de error
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Error al guardar el archivo: " + e.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }

}
