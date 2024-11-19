package edu.pmdm.conversorgrados;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnCalculate;
    TextView txtResultF;
    TextView txtResultK;
    TextView txtResultR;
    EditText etxtNumber;


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

        // Inicializar los widgets
        btnCalculate = findViewById(R.id.btnCalculate);
        txtResultF = findViewById(R.id.txtResultF);
        txtResultK = findViewById(R.id.txtResultK);
        txtResultR = findViewById(R.id.txtResultR);
        etxtNumber = findViewById(R.id.etxtNumber);

        // Evento para cuando se pulse el botón
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Guardar en una variable el número que introduce el usuario
                String valor = String.valueOf(etxtNumber.getText());
                double valorNumerico = Double.valueOf(valor);

                // Calcular la temperatura en sus distintos valores
                double valorFahrenheit = valorNumerico * 9 / 5 + 32;
                double valorKelvin = valorNumerico + 273.15;
                double valorRankine = valorNumerico * 9 / 5 + 491.67;

                // Pasar a String esos valores
                String farenheit = String.valueOf(valorFahrenheit);
                String kelvin = String.valueOf(valorKelvin);
                String rankine = String.valueOf(valorRankine);

                // Establecer los valores calculados
                txtResultF.setText(String.format("%.2f", valorFahrenheit) + "ºF");
                txtResultK.setText(String.format("%.2f", valorKelvin) + "K");
                txtResultR.setText(String.format("%.2f", valorRankine) + "R");

            }
        });
    }
}