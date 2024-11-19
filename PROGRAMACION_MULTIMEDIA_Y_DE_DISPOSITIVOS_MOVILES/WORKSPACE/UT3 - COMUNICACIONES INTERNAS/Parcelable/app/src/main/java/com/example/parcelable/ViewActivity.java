package com.example.parcelable;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.parcelable.ClasesAlumno.Alumno;

public class ViewActivity extends AppCompatActivity {

    private TextView txtDatosNormales = null;
    private TextView txtDatosJSON = null;
    private Button btnVolver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Asignar valores XML
        txtDatosNormales = findViewById(R.id.textViewDatosAlumno);
        txtDatosJSON = findViewById(R.id.textViewDatosAlumnoJSON);
        btnVolver = findViewById(R.id.buttonVolver);

        // Crear Intent para recibir el objeto parcelable
        Intent i = getIntent();
        Alumno a = i.getParcelableExtra("obj1");

        // Asignar valores a los textview
        txtDatosNormales.setText(a.toString());
        txtDatosJSON.setText(a.toJson());

        // Listener para cuando se pulsa el botón de volver
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Crear el intent para volver a la main activity
                Intent i = new Intent(ViewActivity.this, MainActivity.class);

                // Lanzar el intent
                startActivity(i);

            }
        });

    }
}