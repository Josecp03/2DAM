package com.example.parcelable;

import android.content.Intent;
import android.os.Bundle;
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

        txtDatosNormales = findViewById(R.id.textViewDatosAlumno);
        txtDatosJSON = findViewById(R.id.textViewDatosAlumnoJSON);

        Intent i = getIntent();
        Alumno a = i.getParcelableExtra("obj1");

        txtDatosNormales.setText(a.toString());
        txtDatosJSON.setText(a.toJson());

    }
}