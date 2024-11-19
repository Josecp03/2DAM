package com.example.sharedcontext;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {

    // Inicializar atributos de la clase
    private LinearLayout linearLayout = null;
    private Button btnFondoRojo = null;
    private Button btnFondoAzul = null;
    private Button btnBotonesLetrasAzules = null;
    private Button btnBotonesLetrasRojas = null;
    private TextView txtTituloPrincipal = null;
    private TextView txtTituloFondo = null;
    private TextView txtTituloBotonesLetras = null;


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



        // Asignar elementos XML correspondientes
        linearLayout = findViewById(R.id.LinearLayout);
        btnFondoRojo = findViewById(R.id.buttonFondoRojo);
        btnFondoAzul = findViewById(R.id.buttonFondoAzul);
        btnBotonesLetrasAzules = findViewById(R.id.buttonBotonesLetrasAzules);
        btnBotonesLetrasRojas = findViewById(R.id.buttonBotonesLetrasRojas);
        txtTituloPrincipal = findViewById(R.id.textViewTitulo);
        txtTituloFondo = findViewById(R.id.textViewColorFondo);
        txtTituloBotonesLetras = findViewById(R.id.textViewColorBotones);

        // Inicializar mis preferncias
        SharedPreferences sharedPreferences = getSharedPreferences("MisPreferncias", Context.MODE_PRIVATE);

        // Obtener el color de fondo guardado o usar el color predeterminado si no existe
        int colorFondo = sharedPreferences.getInt("fondo", ContextCompat.getColor(MainActivity.this, R.color.fondoPredeterminado));
        linearLayout.setBackgroundColor(colorFondo);

        // Obtener el color de letras y botones guardado o usar el color predeterminado si no existe
        int colorBotonesLetras = sharedPreferences.getInt("botonesLetras", Color.WHITE);
        cambiarColorBotonesLetras(colorBotonesLetras);



        // Listener para cuando se pulse el botón para cambiar el fondo de rojo
        btnFondoRojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Inicializar el valor del color de fondo rojo
                int rojoFondo = ContextCompat.getColor(MainActivity.this, R.color.rojoFondo);

                // Cambiar el color de fondo del layout a rojo
                linearLayout.setBackgroundColor(rojoFondo);

                // Cambiar las preferencias
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("fondo", rojoFondo);
                editor.apply();

                // Avisar al usuairo que se han guardado los cambios
                Toast.makeText(MainActivity.this, "Color de fondo rojo guardado", Toast.LENGTH_SHORT).show();

            }
        });

        // Listener para cuando se pulse el botón para cambiar el fondo de azul
        btnFondoAzul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Inicializar el valor del color de fondo azul
                int azulFondo = ContextCompat.getColor(MainActivity.this, R.color.azulFondo);

                // Cambiar el color de fondo del layout a azul
                linearLayout.setBackgroundColor(azulFondo);

                // Cambiar las preferencias
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("fondo", azulFondo);
                editor.apply();

                // Avisar al usuairo que se han guardado los cambios
                Toast.makeText(MainActivity.this, "Color de fondo azul guardado", Toast.LENGTH_SHORT).show();

            }
        });

        btnBotonesLetrasAzules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Inicializar el valor del color de fondo azul
                int azulBotonesLetras = ContextCompat.getColor(MainActivity.this, R.color.azulBotonesLetras);

                cambiarColorBotonesLetras(azulBotonesLetras);

                // Cambiar las preferencias
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("botonesLetras", azulBotonesLetras);
                editor.apply();

                // Avisar al usuairo que se han guardado los cambios
                Toast.makeText(MainActivity.this, "Color de letras y botones azules guardados", Toast.LENGTH_SHORT).show();

            }
        });

        btnBotonesLetrasRojas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Inicializar el valor del color de fondo azul
                int rojoBotonesLetras = ContextCompat.getColor(MainActivity.this, R.color.rojoBotonesLetras);

                cambiarColorBotonesLetras(rojoBotonesLetras);

                // Cambiar las preferencias
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("botonesLetras", rojoBotonesLetras);
                editor.apply();

                // Avisar al usuairo que se han guardado los cambios
                Toast.makeText(MainActivity.this, "Color de letras y botones rojos guardados", Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void cambiarColorBotonesLetras (int color) {

        // Cambiar el color de los TextView
        txtTituloPrincipal.setTextColor(color);
        txtTituloFondo.setTextColor(color);
        txtTituloBotonesLetras.setTextColor(color);

        // Cambiar el color de fondo de los botones
        btnFondoAzul.setBackgroundColor(color);
        btnFondoRojo.setBackgroundColor(color);
        btnBotonesLetrasAzules.setBackgroundColor(color);
        btnBotonesLetrasRojas.setBackgroundColor(color);

        // Cambiar el texto de los botones a blanco para que se vea mejor
        btnBotonesLetrasRojas.setTextColor(Color.WHITE);
        btnBotonesLetrasAzules.setTextColor(Color.WHITE);
        btnFondoAzul.setTextColor(Color.WHITE);
        btnFondoRojo.setTextColor(Color.WHITE);

        // Cargar la fuente desde los assets
        Typeface tipoFuente = ResourcesCompat.getFont(this, R.font.archivo_black);

        // Establecer fuente a los botones
        btnFondoAzul.setTypeface(tipoFuente);
        btnFondoRojo.setTypeface(tipoFuente);
        btnBotonesLetrasRojas.setTypeface(tipoFuente);
        btnBotonesLetrasAzules.setTypeface(tipoFuente);

    }

}