package com.example.miprimeraactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import com.google.android.material.internal.EdgeToEdgeUtils;


public class MainActivity extends AppCompatActivity implements View.OnClickListener { // Implementar lo necesario

    // Referencias a los widgets añadidos en el layout
    Button miBoton;
    TextView miTexto;

    // Método para ejecutar el evento
    @Override
    public void onClick (View view) {
        miTexto = (TextView) findViewById(R.id.textView3);
        miTexto.setText("pulsado");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar los widgets
        miBoton = findViewById(R.id.button);
        miTexto = findViewById(R.id.textView3);

        // Asignar el listener al botón
        miBoton.setOnClickListener(this);

    }


}