package edu.pmdm.informaciontrespantallas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SummaryActivity extends AppCompatActivity {

    private TextView txtNombre = null;
    private TextView txtCiudad = null;
    private TextView txtEdad = null;
    private TextView txtPreferencia = null;
    private Button btnRegresarGuardar = null;
    private Button btnEditar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_summary);

        // Declarar e inicializar el intent
        Intent intent = getIntent();

        // Asignar valores  a las variables
        txtNombre = findViewById(R.id.textViewNombre);
        txtEdad = findViewById(R.id.textViewEdad);
        txtCiudad = findViewById(R.id.textViewCiudad);
        txtPreferencia = findViewById(R.id.textViewPreferencias);
        btnRegresarGuardar = findViewById(R.id.buttonRegresarGuardar);
        btnEditar = findViewById(R.id.buttonEditar);

        String nombreIntent = intent.getStringExtra("nombre");
        String ciudadIntent = intent.getStringExtra("ciudad");
        String edadIntent = intent.getStringExtra("edad");
        String preferenciaIntent = intent.getStringExtra("preferencia");

        // Establecer valor a los textView
        txtNombre.setText(nombreIntent);
        txtCiudad.setText(ciudadIntent);
        txtEdad.setText(edadIntent);
        txtPreferencia.setText(preferenciaIntent);

        // Evento cuando se pulse el botón de Regresar y Guardar
        btnRegresarGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Declarar e inicializra el intent
                Intent i = new Intent(getApplicationContext(), MainActivity.class);

                // Lanzar la actividad
                startActivity(i);

                // Mostrar un Toast que indique al usuario que los datos se han guardado
                Toast.makeText(SummaryActivity.this, "Datos guardados con éxito", Toast.LENGTH_SHORT).show();

            }
        });

        // Evento cuando se pulse el botón de editar los datos
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Declarar e inicializra el intent
                Intent i = new Intent(getApplicationContext(), EditActivity.class);
                i.putExtra("nombre", nombreIntent);
                i.putExtra("ciudad", ciudadIntent);
                i.putExtra("edad", edadIntent);
                i.putExtra("preferencia", preferenciaIntent);

                // Lanzar la actividad
                startActivity(i);

            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



    }




}