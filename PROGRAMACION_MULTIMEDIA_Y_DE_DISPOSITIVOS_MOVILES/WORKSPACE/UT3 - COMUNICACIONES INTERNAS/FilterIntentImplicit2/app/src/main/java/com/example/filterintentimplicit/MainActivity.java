package com.example.filterintentimplicit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

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

        // Asignar valores del XML
        ImageView imagenCompartida = findViewById(R.id.ImageViewImagenCompartida);

        // Obtenemos el Intent que inició la actividad
        Intent i = getIntent();
        String action = i.getAction();
        String type = i.getType();

        // Comprobar que la acción sea ACTION_SEND
        if (i.ACTION_SEND.equals(action) && type != null) {

            // Comprobar que el tipo de dato sea de imagen
            if (type.startsWith("image/")) {

                // Llamar al método para manejar la imagen compartida
                try {
                    handleSendImage(i, imagenCompartida);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }

        }
    }


    // Método para manejar la imagen compartida
    private void handleSendImage(Intent i, ImageView receivedImage) throws FileNotFoundException {

        // Obtener la iamgen del intent
        Uri imagenRecibida = i.getParcelableExtra(Intent.EXTRA_STREAM);

        // Comprobar primero si se ha recibido una imagen
        if (imagenRecibida != null) {

            // Convertimos el URI en un Bitmap para mostrarlo
            InputStream imageStream = getContentResolver().openInputStream(imagenRecibida);
            Bitmap bitmap = BitmapFactory.decodeStream(imageStream);

            // Muestrar la imagen en el ImageView
            receivedImage.setImageBitmap(bitmap);

        }
    }
}