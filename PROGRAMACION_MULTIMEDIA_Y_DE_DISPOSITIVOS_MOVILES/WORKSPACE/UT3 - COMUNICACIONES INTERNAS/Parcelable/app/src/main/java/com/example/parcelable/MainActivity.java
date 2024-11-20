package com.example.parcelable;

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

import com.example.parcelable.ClasesAlumno.Alumno;
import com.example.parcelable.ClasesAlumno.Asignatura;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText edtNombre = null;
    private EditText edtEdad = null;
    private EditText edtNotaMedia = null;
    private Button btnNuevaAsignatura = null;
    private EditText edtNombreAsignatura = null;
    private EditText edtNotaAsignatura = null;
    private Button btnVisualizarDatos = null;
    private String nombre = "";
    private int edad = 0;
    private float notaMedia = 0;
    private ArrayList<Asignatura> asignaturas = new ArrayList<>();
    private int contadorAsignaturas = 0;
    private boolean visualizarDatos = false;

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
        edtNombre = findViewById(R.id.editTextNombre);
        edtEdad = findViewById(R.id.editTextEdad);
        edtNotaMedia = findViewById(R.id.editTextNotaMedia);
        btnNuevaAsignatura = findViewById(R.id.buttonNuevaAsignatura);
        edtNombreAsignatura = findViewById(R.id.editTextNombreAsignatura);
        edtNotaAsignatura = findViewById(R.id.editTextNotaAsignatura);
        btnVisualizarDatos = findViewById(R.id.buttonVisualizarDatos);

        // Borrar los campos de los datos de la asignatura hasta que se introduzcan los datos del alumno
        edtNombreAsignatura.setVisibility(View.GONE);
        edtNotaAsignatura.setVisibility(View.GONE);

        // Listener para cuando se quiera añadir una nueva asignatura
        btnNuevaAsignatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Comprobar que los datos del alumno no estén vacíos
                if (edtNombre.getText().toString().isEmpty() || edtEdad.getText().toString().isEmpty() || edtNotaMedia.getText().toString().isEmpty()) {

                    // Mostrar mensaje de error
                    Toast.makeText(MainActivity.this, "Rellene los datos del alumno antes de añadir asignaturas", Toast.LENGTH_SHORT).show();

                } else {

                    // Comprobar la edad que mete el usuario
                    if (Integer.parseInt(edtEdad.getText().toString()) > 0 && Integer.parseInt(edtEdad.getText().toString()) < 120)  {

                        // Comprobar que la nota media sea una nota válida
                        if (Float.parseFloat(edtNotaMedia.getText().toString()) >= 0 && Float.parseFloat(edtNotaMedia.getText().toString()) <= 10) {

                            // Desactivar los edit text para que no pueda modificar los datos del alumno
                            edtNombre.setEnabled(false);
                            edtEdad.setEnabled(false);
                            edtNotaMedia.setEnabled(false);

                            // Actualizar valor variable booleana
                            visualizarDatos = true;

                            // Hacer aparecer los campos para rellenar los datos de la asignatura
                            edtNombreAsignatura.setVisibility(View.VISIBLE);
                            edtNotaAsignatura.setVisibility(View.VISIBLE);

                            // Asignar los valores a las variables
                            nombre = edtNombre.getText().toString();
                            edad = Integer.parseInt(edtEdad.getText().toString());
                            notaMedia = Float.parseFloat(edtNotaMedia.getText().toString());

                            // Comprobar el número de asignaturas que se han añadido
                            if (contadorAsignaturas < 3) {

                                // Verificar los datos de la asignatura
                                if (!edtNombreAsignatura.getText().toString().isEmpty() && !edtNotaAsignatura.getText().toString().isEmpty()) {

                                    // Comprobar que la nota de la asignatura sea válida
                                    if (Float.parseFloat(edtNotaAsignatura.getText().toString()) >= 0 && Float.parseFloat(edtNotaAsignatura.getText().toString()) <= 10) {

                                        // Asignar a una variable el nombre de la asignatura
                                        String nombreAsignatura = edtNombreAsignatura.getText().toString();

                                        // Inicializar variable booleana para comprobar si esas asignatura existe ya
                                        boolean asignaturaRepetida = false;

                                        // Recorrer todas las asignaturas
                                        for (Asignatura a: asignaturas) {

                                            // Comprobar el nombre de la asignatura actual
                                            if (a.getNombre().equals(nombreAsignatura)) {
                                                asignaturaRepetida = true;
                                            }

                                        }

                                        // Comprobar que la asignatura no esté repetida
                                        if (!asignaturaRepetida) {

                                            // Asignar a una variable el valor de la nota de la asignatura
                                            float notaAsignatura = Float.parseFloat(edtNotaAsignatura.getText().toString());

                                            // Crear la asignatura y añadirla al arraylist
                                            Asignatura asignatura = new Asignatura(nombreAsignatura, notaAsignatura);
                                            asignaturas.add(asignatura);
                                            Toast.makeText(MainActivity.this, "Asignatura añadida con éxito", Toast.LENGTH_SHORT).show();

                                            // Aumentar el contador de las asignaturas
                                            contadorAsignaturas++;

                                            // Comprobar si ha llegado al límite de asignaturas
                                            if(contadorAsignaturas == 3) {

                                                // Hacer desaparecer lo relacionado con las asignaturas cuando se llega al límite
                                                edtNombreAsignatura.setVisibility(View.GONE);
                                                edtNotaAsignatura.setVisibility(View.GONE);
                                                btnNuevaAsignatura.setVisibility(View.GONE);

                                            } else {

                                                // Limpiar campos de la asignatura para añadir otra
                                                edtNombreAsignatura.setText("");
                                                edtNotaAsignatura.setText("");

                                            }

                                        } else {

                                            // Mostrar mensaje de error
                                            Toast.makeText(MainActivity.this, "No se puede ingresar la misma asignatura dos veces", Toast.LENGTH_SHORT).show();

                                        }

                                    } else {

                                        // Mostrar mensaje de error
                                        Toast.makeText(MainActivity.this, "Introduzca una nota media válida (0-10)", Toast.LENGTH_SHORT).show();

                                    }

                                } else {

                                    // Mostrar mensaje de error
                                    Toast.makeText(MainActivity.this, "Complete los datos de la asignatura", Toast.LENGTH_SHORT).show();

                                }

                            } else {

                                // Mostrar mensaje al usuario de que no se pueden añadir más asignaturas
                                Toast.makeText(MainActivity.this, "Límite de asignaturas alcanzado. Solo se pueden añadir 3 asignaturas", Toast.LENGTH_SHORT).show();

                            }

                        } else {

                            // Mostrar mensaje de error
                            Toast.makeText(MainActivity.this, "Introduzca una nota media válido (0-10)", Toast.LENGTH_SHORT).show();

                        }

                    } else {

                        // Mostrar mensaje de error
                        Toast.makeText(MainActivity.this, "Introduzca una edad razonable por favor", Toast.LENGTH_SHORT).show();

                    }

                }

            }
        });

        // Listener para cuando se pulse el botón de visualizar los datos
        btnVisualizarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (visualizarDatos) {

                    Alumno a = new Alumno(nombre, asignaturas, edad, notaMedia);

                    Intent i = new Intent(MainActivity.this, ViewActivity.class);
                    i.putExtra("obj1", a);
                    startActivity(i);

                } else {

                    // Mostrar mensaje de error
                    Toast.makeText(MainActivity.this, "Añade primero los datos del alumno", Toast.LENGTH_SHORT).show();

                }



            }
        });


    }
}