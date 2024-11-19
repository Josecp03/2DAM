package com.example.permisospeligrosos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public final int PERMISO_UBICACION = 1;
    private TextView txtInformacion = null;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Asignar valores XML
        txtInformacion = findViewById(R.id.textView);

        // Comprobar si ya se tiene permiso
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            // Llamar al método para calcular la ubicación
            obtenerUbicacion();

        } else {

            // Solicitar permiso
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISO_UBICACION);

        }
    }

    private void obtenerUbicacion() {

        try {
            // Obtener el servicio de ubicación
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            // Comprobar si el GPS está habilitado
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

                // Solicitar una actualización única de la ubicación
                locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, new LocationListener() {

                    @Override
                    public void onLocationChanged(@NonNull Location location) {

                        // Obtener la latitud y longitud
                        double latitud = location.getLatitude();
                        double longitud = location.getLongitude();

                        // Mostrar los datos en el TextView
                        txtInformacion.setText("Latitud: " + latitud + "\nLongitud: " + longitud);

                    }

                }, null);

            } else {

                // Mostrar mensaje si el GPS está deshabilitado
                Toast.makeText(this, "Por favor habilita el GPS", Toast.LENGTH_LONG).show();

            }

        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISO_UBICACION) {

            // Comprobar si se han permitido los permisos
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED) {

                // Mostrar mensaje al usuario de confirmación
                Toast.makeText(this, "Se han concedido los permisos de ubicación en la aplicación", Toast.LENGTH_SHORT).show();

                // Llamar al método para obtener la latitud y lomgitud
                obtenerUbicacion();

            } else {

                // Mostrar mensaje de permisos denegados
                txtInformacion.setText("Permiso denegado");

            }
        }
    }
}
