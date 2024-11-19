package com.example.intentexplicite;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnAbrirWeb = null;
    private Button btnEnviarCorreo = null;
    private Button btnTomarFoto = null;
    private Button btnConfigurar = null;
    private String url = "";
    private String direccionCorreo = "";
    private String asunto = "";
    private String mensaje = "";


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
        btnAbrirWeb = findViewById(R.id.buttonAbrirWeb);
        btnEnviarCorreo = findViewById(R.id.buttonEnviarCorreo);
        btnTomarFoto = findViewById(R.id.buttonTomarFoto);
        btnConfigurar = findViewById(R.id.buttonConfigurar);

        // Asignar valores del intent
        Intent i = getIntent();
        url = i.getStringExtra("url");
        direccionCorreo = i.getStringExtra("direccionCorreo");
        asunto = i.getStringExtra("asunto");
        mensaje = i.getStringExtra("mensaje");

        https://as.com

        // Listener para cuando se pulse el botón de abrir url personalizada
        btnAbrirWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Comprobar que se haya cargado correctamente la url
                if (url == null || url.isEmpty()) {

                    // Abrir google por defecto
                    url = "https://www.google.com/";

                } else {

                    // Si la URL no contiene un esquema, se interpreta como búsqueda
                    if (!url.startsWith("http://") && !url.startsWith("https://")) {
                        url = "https://www.google.com/search?q=" + Uri.encode(url);
                    }

                }

                // Crear el intent
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));

                // Lanzar el intent
                startActivity(i);

            }
        });


        // Listener para caundo se pulse el botón de tomar una foto
        btnTomarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Crear intent
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                // Lanzar intent
                startActivity(i);

            }
        });

        // Listener para cuando se pulse le botón de enviar correo personalizado
        btnEnviarCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Comprobar que se hayan asignado los datos del correo
                if ((direccionCorreo == null || direccionCorreo.isEmpty()) && (asunto == null || asunto.isEmpty()) && (mensaje == null || mensaje.isEmpty())) {

                    // Mostrar mensaje de error
                    Toast.makeText(MainActivity.this, "Debes configurar primero los datos del correo", Toast.LENGTH_SHORT).show();

                } else {

                    // Crear el intent para abrir el correo
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("message/rfc822");
                    intent.setPackage("com.google.android.gm");

                    // Asignar los datos que va a tener el correo
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{direccionCorreo});
                    intent.putExtra(Intent.EXTRA_SUBJECT, asunto);
                    intent.putExtra(Intent.EXTRA_TEXT, mensaje);

                    // Lanzar el intent
                    startActivity(intent);

                }


            }
        });

        // Listener para cuando se pulse el botón para ir a la otra aplicación
        btnConfigurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Crear intent
                Intent i = new Intent(MainActivity.this, EditingActivity.class);

                // Lanzar intent
                startActivity(i);

            }
        });





    }


}