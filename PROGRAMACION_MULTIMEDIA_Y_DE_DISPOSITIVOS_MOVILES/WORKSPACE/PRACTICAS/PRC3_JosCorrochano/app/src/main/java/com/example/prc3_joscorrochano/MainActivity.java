package com.example.prc3_joscorrochano;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import com.example.prc3_joscorrochano.databinding.ActivityMainBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "modo_preferencias";
    private static final String KEY_MODE = "modo_claro_oscuro";
    private ActivityMainBinding binding;
    private FusedLocationProviderClient clienteUbicacion;
    private ActivityResultLauncher<String[]> solicitudPermisoUbicacion;
    private Boolean correoGuardado = false;
    private Boolean codigoPostalGuardado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflar el layout y asociarlo con el binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Leer el modo guardado en SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int nightMode = sharedPreferences.getInt(KEY_MODE, AppCompatDelegate.MODE_NIGHT_NO);
        AppCompatDelegate.setDefaultNightMode(nightMode);

        // Inicializar el cliente de ubicación para acceder a los servicios de localización proporcionados por Google
        clienteUbicacion = LocationServices.getFusedLocationProviderClient(this);

        // Registrar el lanzador de permisos
        solicitudPermisoUbicacion = registerForActivityResult(
                new ActivityResultContracts.RequestMultiplePermissions(),
                permisos -> {

                    // Comprobar si se han aceptado los permisos de ubicacion precisa
                    if (Boolean.TRUE.equals(permisos.get(Manifest.permission.ACCESS_FINE_LOCATION))) {

                        // Llamar al método para obtener la ubicación actual
                        obtenerUbicacionActual();

                    } else if (Boolean.TRUE.equals(permisos.get(Manifest.permission.ACCESS_COARSE_LOCATION))) {

                        // Llamar al método para obtener la ubicación actual
                        obtenerUbicacionActual();

                    } else {

                        // Llamar al método para obtener la ubicación actual
                        Toast.makeText(this, "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show();

                    }
                });

        // Listener para el botón que activa el modo claro
        binding.buttonClaro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Cambiar la interfaz al modo claro
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                // Guardar la preferencia del modo actual para que se mantenga después de reiniciar la aplicación
                guardarPreferenciaModo(AppCompatDelegate.MODE_NIGHT_NO);

            }
        });

        // Listener para el botón que activa el modo oscuro
        binding.buttonOscuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Cambiar la interfaz al modo oscuro
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                // Guardar la preferencia del modo actual para que se mantenga después de reiniciar la aplicación
                guardarPreferenciaModo(AppCompatDelegate.MODE_NIGHT_YES);

            }
        });

        // Listener para el botón de ubicación
        binding.imageButtonPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Llamar al método para solicitar los permisos
                solicitarPermisoUbicacion();
            }
        });

        // Listener para el botón del correo
        binding.imageButtonMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Obtener el correo que se introduce del edit text
                String correo = binding.editTextMail.getText().toString();

                // Comprobar que el correo sea válido
                if (correoValido(correo)) {

                    // Actualizar variable booleana
                    correoGuardado = true;

                    // Mostrar mensaje de que se ha agregado con éxito el correo
                    Toast.makeText(MainActivity.this, "Correo agregado con éxito", Toast.LENGTH_SHORT).show();

                } else if (correo.isEmpty()) {

                    // Mostrar mensaje correspondiente al usuario
                    Toast.makeText(MainActivity.this, "No se puede agregar correo, está vacío", Toast.LENGTH_SHORT).show();

                } else {

                    // Mostrar mensaje correspondiente al usuario
                    Toast.makeText(MainActivity.this, "Introduzca un correo válido", Toast.LENGTH_SHORT).show();

                }

            }
        });

        // Listener para el botón de Login
        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Comprobar que la variable booleana de correo guardado sea veradera antes de pasar a la siguiente actividad
                if (correoGuardado) {

                    // Comprobar que la variable booleana de código postal guardado sea veradera antes de pasar a la siguiente actividad
                    if (codigoPostalGuardado) {

                        // Crear el Intent
                        Intent i = new Intent(MainActivity.this, BikeActivity.class);

                        // Lanzar el Intent
                        startActivity(i);

                        // Realizar transición entre actividades
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                    } else {

                        // Mostrar mensaje de error si no se ha guardado el código postal correctamente
                        Toast.makeText(MainActivity.this, "Guarda antes el código postal pulsando en el icono", Toast.LENGTH_SHORT).show();

                    }
                } else {

                    // Mostrar mensaje de error si no se ha guardado el correo correctamente
                    Toast.makeText(MainActivity.this, "Guarda antes el correo correctamente pulsando en el icono", Toast.LENGTH_SHORT).show();

                }

            }
        });

        // Comprobar que la aplicación tiene permisos para recibir SMS
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // Comprobar si la versión es API 23 o superior
            if (checkSelfPermission(Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
                // Pedirle permisos de notificaciones al usuario para usar en la aplicación
                requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS}, 1);
            }
        }

    }

    private void solicitarPermisoUbicacion() {

        // Lanzar la solicitud de permisos
        solicitudPermisoUbicacion.launch(new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        });
    }

    private void obtenerUbicacionActual() {

        // Comprobar que se han aceptado los permisos
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            // Obtener la última ubicación
            clienteUbicacion.getLastLocation()
                    .addOnSuccessListener(ubicacion -> {

                        // Comprobar que la ubicación no sea nula
                        if (ubicacion != null) {

                            // Llamar al método para procesar la ubicación
                            procesarUbicacion(ubicacion);

                        } else {

                            // Mostrar mensaje de error cuando la ubicación sea null
                            Toast.makeText(MainActivity.this, "No se pudo obtener la ubicación", Toast.LENGTH_SHORT).show();

                        }
                    });
        }
    }

    private void procesarUbicacion(Location ubicacion) {

        // Obtener la latitud y la longitud
        double latitud = ubicacion.getLatitude();
        double longitud = ubicacion.getLongitude();

        // Abrir Google Maps con la ubicación actual
        abrirGoogleMaps(latitud, longitud);

        // Actualizar el TextView con el código postal
        actualizarCodigoPostal(latitud, longitud);

    }

    private void abrirGoogleMaps(double latitud, double longitud) {

        // Inicializar el String que le paso luego al intent
        String geoUri = "geo:" + latitud + "," + longitud + "?q=" + latitud + ", " + longitud;

        // Crear el Intent
        Intent intent = new Intent(Intent.ACTION_VIEW, android.net.Uri.parse(geoUri));

        intent.setPackage("com.google.android.apps.maps");

        // Lanzar el intent
        startActivity(intent);

    }

    private void actualizarCodigoPostal(double latitud, double longitud) {

        // Crear el geoCoder
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {

            // Lista de posibles direcciones cercanas a las coordenadas dadas
            List<Address> direcciones = geocoder.getFromLocation(latitud, longitud, 1);

            // Comprobar que se haya obtenido alguna direccion
            if (direcciones != null && !direcciones.isEmpty()) {

                // Asignar a una variable el código postal
                String codigoPostal = direcciones.get(0).getPostalCode();

                // Asignar el valor del código postal al edit text
                binding.textViewPlace.setText(codigoPostal);

                // Actualizar variable booleana
                codigoPostalGuardado = true;

            } else {

                // Asignar mensaje de error en el edittext
                binding.textViewPlace.setText("Dirección no encontrada");

            }
        } catch (IOException e) {

            // Mostrar mensaje de error si hubiese ocurrido cualquier otro problema
            binding.textViewPlace.setText("Error al obtener el código postal");

        }
    }

    private boolean correoValido(String cadena) {

        // Devolver verdadero o falso dependiendo si se cumple o no la expresión regular
        return cadena.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

    }

    private void guardarPreferenciaModo(int modo) {

        // Obtener las preferencias compartidas
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Crear el editor de preferencias
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Guardar el modo en las preferencias
        editor.putInt(KEY_MODE, modo);
        editor.apply();

        // Mostrar mensaje al usuario
        Toast.makeText(this, "Modo cambiado y guardado", Toast.LENGTH_SHORT).show();

    }

}
