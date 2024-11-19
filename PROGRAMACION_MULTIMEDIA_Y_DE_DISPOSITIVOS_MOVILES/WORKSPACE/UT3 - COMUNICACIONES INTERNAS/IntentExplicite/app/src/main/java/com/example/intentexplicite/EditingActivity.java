package com.example.intentexplicite;

import android.content.Intent;
import android.os.Bundle;
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

public class EditingActivity extends AppCompatActivity {

    private EditText edtURL = null;
    private EditText edtCorreo = null;
    private EditText edtAsunto = null;
    private EditText edtMensaje = null;
    private Button btnGuardarURL = null;
    private Button btnGuardarCorreo = null;
    private Button btnVolver = null;
    private String url = "";
    private String correo = "";
    private String asunto = "";
    private String mensaje = "";
    private Intent intentDatos;
    private boolean urlGuardada = false;
    private boolean datosCorreoGuardados = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editing);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Asignar valores XML
        edtURL = findViewById(R.id.editTextURL);
        edtCorreo = findViewById(R.id.editTextTextCorreo);
        edtAsunto = findViewById(R.id.editTextTextAsunto);
        edtMensaje = findViewById(R.id.editTextTextMensaje);
        btnGuardarURL = findViewById(R.id.buttonGuardarURL);
        btnGuardarCorreo = findViewById(R.id.buttonGuardarCorreo);
        btnVolver = findViewById(R.id.buttonVolver);

        // Inicializar intentDatos
        intentDatos = new Intent(EditingActivity.this, MainActivity.class);

        // Listener cuando pulsa el botón de guardar la URL
        btnGuardarURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Comprobar que se hayan rellenado los campos
                if (!edtURL.getText().toString().isEmpty()) {

                    // Asignar valores a las variables
                    url = edtURL.getText().toString();

                    // Preparar los datos para luego mandarlos a la otra actividad
                    intentDatos.putExtra("url", url);

                    // Mostrar mensaje de confirmación
                    Toast.makeText(EditingActivity.this, "Datos de la URL guardados con éxito", Toast.LENGTH_SHORT).show();

                    // Actualizar la variable booleana
                    urlGuardada = true;

                } else {

                    // Mostrar mensaje de error
                    Toast.makeText(EditingActivity.this, "Introudce una url por favor", Toast.LENGTH_SHORT).show();

                }

            }
        });

        // Listener para cuando se pulse el botón de guardar correo
        btnGuardarCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Comprobar que estén rellenos todos los campos
                if (!(edtAsunto.getText().toString().isEmpty() || edtCorreo.getText().toString().isEmpty() || edtMensaje.getText().toString().isEmpty())) {

                    // Asignar valores
                    correo = edtCorreo.getText().toString();
                    asunto = edtAsunto.getText().toString();
                    mensaje = edtMensaje.getText().toString();

                    // Comprobar que el correo que se introduce es válido
                    if (verificarDireccionCorreo(correo)) {

                        // Preparar los datos para el intent
                        intentDatos.putExtra("direccionCorreo", correo);
                        intentDatos.putExtra("asunto", asunto);
                        intentDatos.putExtra("mensaje", mensaje);

                        // Mostrar mensaje de confirmación
                        Toast.makeText(EditingActivity.this, "Datos del correo guardados con éxito", Toast.LENGTH_SHORT).show();

                        // Actualizar la variable booleana
                        datosCorreoGuardados = true;

                    } else {

                        // Mostrar mensaje de correo no válido
                        Toast.makeText(EditingActivity.this, "Ingrese un correo válido", Toast.LENGTH_SHORT).show();

                    }

                } else {

                    // Mostrar mensaje de error
                    Toast.makeText(EditingActivity.this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();

                }



            }
        });

        // Listener para cuando pulse el botón de volver, que vuelva a la actividad principal
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Comprobar que se hayan rellenado y guardado todos los campos
                if(urlGuardada && datosCorreoGuardados) {

                    // Lanzar el intent
                    startActivity(intentDatos);

                } else {

                    // Mostrar mensaje de error
                    Toast.makeText(EditingActivity.this, "Por favor, introduzca todos los datos", Toast.LENGTH_SHORT).show();

                }


            }
        });

    }

    // Método que verifica mediante una expresión regular si es una dirección de correo válida
    public boolean verificarDireccionCorreo (String direccionCorreo) {
        return direccionCorreo.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");
    }

}