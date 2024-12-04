package edu.pmdm.executor;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private EditText edtUrl = null;
    private Button btnAgregarImagen = null;
    private ArrayList<String> listaImagenes = new ArrayList<>();
    private final int LIMITE_IMAGENES = 5;

    // Elementos para la descarga
    private EditText edURL;
    private TextView txtDescarga;
    private ExecutorService executorService;

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
        edtUrl = findViewById(R.id.editTextTextUrl);
        btnAgregarImagen = findViewById(R.id.buttonAgregarImagen);
        Button btnReset = findViewById(R.id.buttonReset);

        txtDescarga = findViewById(R.id.txtDescarga);
        executorService = Executors.newSingleThreadExecutor();
        edURL = findViewById(R.id.edURL);

        Button btnDescargar = findViewById(R.id.buttonDescargar);
        btnDescargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Descargar(v);
            }
        });

        // TextWatcher para el EditText
        edtUrl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // Restablecer el texto inicial del botón cuando se detecte un cambio en el EditText
                if (!edtUrl.getText().toString().isEmpty()) {
                    btnAgregarImagen.setText("Agregar Imagen");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Listener para cuando se pulse el botón de agregra imagen
        btnAgregarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Guardar en una variable la url introducida en el EditText
                String urlImagen = edtUrl.getText().toString().trim();

                // Comprobar que la url esté vacía
                if (edtUrl.getText().toString().isEmpty()) {

                    // Mostrar mensaje de error
                    Toast.makeText(MainActivity.this, "La URL no puede estar vacía", Toast.LENGTH_SHORT).show();

                } else {

                    // Comprobar que el formato de la URL sea válido
                    if (esFormatoValido(urlImagen)) {


                        // Comprobar que no se añadan más imágenes del límite permitido
                        if (listaImagenes.size() < LIMITE_IMAGENES) {

                            // Añadir la imagen a la lista
                            listaImagenes.add(urlImagen);

                            // Limpiar el EditText
                            edtUrl.setText("");

                            // Cambiar texto del botón si ya hay imágenes
                            if (listaImagenes.size() > 0) {
                                btnAgregarImagen.setText("¿Quieres agregar otra foto?");
                            }

                            // Crear el intent
                            Intent intent = new Intent(MainActivity.this, VisualizarImagenes.class);

                            // Pasarle el arrayList de imágnes al intent
                            intent.putStringArrayListExtra("listaImagenes", listaImagenes);

                            // Lanzar el intent
                            startActivity(intent);

                        } else {

                            // Mostrar mensaje de error
                            Toast.makeText(MainActivity.this, "No se pueden añadir más de 5 imágenes", Toast.LENGTH_SHORT).show();

                        }

                    } else {

                        // Mostrar mensaje de error
                        Toast.makeText(MainActivity.this, "Formato no válido. Solo se permiten .png, .jpg o .jpeg", Toast.LENGTH_SHORT).show();

                    }

                }


            }
        });

        // Listener para cuando se pulse el botón de Reset
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Comprobar que el arrayList no esté vacío
                if (!listaImagenes.isEmpty()) {

                    // Limpiar el arrayList
                    listaImagenes.clear();

                    // Restablecer el texto inicial del botón
                    btnAgregarImagen.setText("Agregar Imagen");

                    // Mostrar mensaje de confirmación
                    Toast.makeText(MainActivity.this, "Se han borrado las imágenes.", Toast.LENGTH_SHORT).show();

                } else {

                    // Mostrar mensaje de error
                    Toast.makeText(MainActivity.this, "No hay imágenes cargadas por el momento.", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    // Método para validar el formato de la imagen
    private boolean esFormatoValido(String url) {
        return url.endsWith(".png") || url.endsWith(".jpg") || url.endsWith(".jpeg");
    }

    // Método que se invoca cuando se presiona el botón para descargar
    public void Descargar(View v) {
        txtDescarga.setMovementMethod(new ScrollingMovementMethod());

        if (isNetworkAvailable()) {
            executorService.execute(() -> {
                String result;
                try {
                    result = descargaUrl(edURL.getText().toString());
                } catch (IOException e) {
                    result = "Imposible cargar la web! URL mal formada";
                }
                final String finalResult = result;
                runOnUiThread(() -> txtDescarga.setText(finalResult));
            });
        } else {
            edURL.setText("No se ha podido establecer conexión a internet");
        }
    }

    // Método para verificar la conectividad de red
    private boolean isNetworkAvailable() {
        ConnectivityManager connMgr = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        if (connMgr != null) {
            Network network = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                network = connMgr.getActiveNetwork();
            }
            if (network != null) {
                NetworkCapabilities capabilities = connMgr.getNetworkCapabilities(network);
                return capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
            }
        }
        return false;
    }

    // Método para descargar contenido de una URL
    private String descargaUrl(String myurl) throws IOException {
        InputStream is = null;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            is = conn.getInputStream();
            return Leer(is);
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    // Método para leer el InputStream
    private String Leer(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while (i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }

}