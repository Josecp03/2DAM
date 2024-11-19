package edu.pmdm.informaciontrespantallas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnContinuar = null;
    private EditText edtNombre = null;
    private EditText edtCiudad = null;
    private EditText edtEdad = null;
    private RadioButton rbWindows = null;
    private RadioButton rbMacOs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Asignar valores a las variables
        btnContinuar = findViewById(R.id.buttonContinuar);
        edtNombre = findViewById(R.id.editTextTextNombre);
        edtCiudad = findViewById(R.id.editTextTextCiudad);
        edtEdad = findViewById(R.id.editTextEdad);
        rbWindows = findViewById(R.id.radioButtonWindows);
        rbMacOs = findViewById(R.id.radioButtonMacOs);

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre = String.valueOf(edtNombre.getText());
                String ciudad = edtCiudad.getText().toString();
                String edad = edtEdad.getText().toString();
                String preferencia = "";

                if (rbWindows.isChecked()) {
                    preferencia = "Windows";
                } else if (rbMacOs.isChecked()) {
                    preferencia = "MacOs";
                }

                if (!datosCorrectos(rbWindows, rbMacOs, nombre, ciudad, edad)) {
                    Toast.makeText(MainActivity.this, "Los campos no pueden estar vacíos", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(getApplicationContext(), SummaryActivity.class);
                    i.putExtra("nombre", nombre);
                    i.putExtra("ciudad", ciudad);
                    i.putExtra("edad", edad);
                    i.putExtra("preferencia", preferencia);
                    startActivity(i);
                }


            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    private boolean datosCorrectos(RadioButton rbWindows, RadioButton rbMacOs, String nombre, String ciudad, String edad) {

        return !nombre.isEmpty() && !ciudad.isEmpty() && !edad.isEmpty() && (rbWindows.isChecked() || rbMacOs.isChecked());

    }


}