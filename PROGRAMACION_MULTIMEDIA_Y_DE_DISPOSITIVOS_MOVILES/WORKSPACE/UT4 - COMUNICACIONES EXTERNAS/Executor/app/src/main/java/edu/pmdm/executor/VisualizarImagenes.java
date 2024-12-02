package edu.pmdm.executor;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VisualizarImagenes extends AppCompatActivity {

    private LinearLayout contenedorImagenes;
    private Button btnVolver;
    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_imagenes);

        // Asignar valores XML
        contenedorImagenes = findViewById(R.id.contenedorImagenes);
        btnVolver = findViewById(R.id.btnVolver);

        // Inicializar ExecutorService
        executorService = Executors.newFixedThreadPool(4);

        // Obtener la lista de las URls dle intent
        ArrayList<String> listaImagenes = getIntent().getStringArrayListExtra("listaImagenes");

        // Descargar y mostrar cada imagen llamando a un método
        for (String url : listaImagenes) {
            descargarImagen(url);
        }

        // Listener para el botón de volver
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Finaliza la actividad actual para volver a MainActivity
                finish();

            }
        });

    }

    private void descargarImagen(String url) {

        executorService.execute(() -> {

            try {

                // Intentar obtener el Bitmap desde la URL
                Bitmap bitmap = obtenerImagenDeUrl(url);

                // Mostrar la imagen en la interfaz de usuario
                runOnUiThread(() -> mostrarImagen(bitmap));

            } catch (Exception e) {

                e.printStackTrace();

                // Mostrar un mensaje de error en la interfaz de usuario
                runOnUiThread(() -> mostrarError(url));

            }

        });
    }


    private Bitmap obtenerImagenDeUrl(String urlString) throws Exception {

        // Crear un objeto URL a partir del string proporcionado
        URL url = new URL(urlString);

        // Abrir una conexión HTTP a la URL especificada
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Configurar el tiempo máximo que esperará por una respuesta al intentar leer datos
        connection.setReadTimeout(10000);

        // Configurar el tiempo máximo que esperará al intentar establecer la conexión
        connection.setConnectTimeout(15000);

        // Definir el método de solicitud HTTP como "GET"
        connection.setRequestMethod("GET");

        // Habilitar la lectura de datos desde la conexión
        connection.setDoInput(true);

        // Establecer la conexión con el servidor
        connection.connect();

        // Obtener un flujo de entrada desde la conexión
        InputStream inputStream = connection.getInputStream();

        // Usar BitmapFactory para decodificar los datos del flujo de entrada en un objeto Bitmap
        return BitmapFactory.decodeStream(inputStream);

    }



    private void mostrarImagen(Bitmap bitmap) {

        // Crear un ImageView
        ImageView imageView = new ImageView(this);
        imageView.setImageBitmap(bitmap);

        // Configurar los parámetros del layout
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        // Establecer los márgenes
        layoutParams.setMargins(15, 15, 15, 15);
        imageView.setLayoutParams(layoutParams);

        // Añadir la vista d ela iagen al layout
        contenedorImagenes.addView(imageView);

    }

    private void mostrarError(String url) {

        // Crear un TextView con un mensaje de error
        TextView textView = new TextView(this);
        textView.setText("No se pudo obtener la imagen de: " + url);
        textView.setTextColor(getResources().getColor(R.color.red));
        textView.setTextSize(16);

        // Configurar los márgenes del TextView
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(15, 15, 15, 15);
        textView.setLayoutParams(layoutParams);

        // Añadir el mensaje de error al contenedor
        contenedorImagenes.addView(textView);

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Cerrar el ExecutorService
        executorService.shutdown();

    }
}
