package com.example.notificacionespersonalizadas;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnEnviarNotificacion = null;

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
        btnEnviarNotificacion = findViewById(R.id.buttonEnviarNotificacion);

        // Listener para cuando se pulse el botón de enviar la notificación
        btnEnviarNotificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Llamar al método para crear la notificación
                crearNotificacion("Notificacion de tu aplicación personalizada");

            }
        });

        // Comprobar que la palicación tiene permisos para enviar notificaciones
        if (checkSelfPermission("android.permission.RECEIVE_SMS") != PackageManager.PERMISSION_GRANTED) {

            // Pedirle permisos de notificaciones al usuario para usar en la aplicación
            requestPermissions(new String[]{"android.permission.RECEIVE_SMS"}, 1);

        }


    }

    private void crearNotificacion(String mensaje) {

        // Constructor de la notificación
        NotificationCompat.Builder constructorNotif = new NotificationCompat.Builder(this, "miCanal")
                .setSmallIcon(R.drawable.baseline_notifications_24)
                .setContentTitle("Recibiste una notificación")
                .setContentText(mensaje)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true); // Al pulsar en la notificación desaparece


        // Crear el intent
        Intent resultadoIntent = new Intent(MainActivity.this, NotificacionPulsada.class);

        TaskStackBuilder pila = TaskStackBuilder.create(this);
        pila.addParentStack(MainActivity.class);

        // Añade el Intent que comienza la Actividad al inicio de la pila
        pila.addNextIntent(resultadoIntent);

        // Especifica FLAG_IMMUTABLE junto con FLAG_UPDATE_CURRENT
        PendingIntent resultadoPendingIntent = pila.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        constructorNotif.setContentIntent(resultadoPendingIntent);

        // Crear el canal y enviar la notificación
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel canal = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            canal = new NotificationChannel("miCanal", "Canal de notificación de prueba",
                    NotificationManager.IMPORTANCE_DEFAULT);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(canal);
        }

        notificationManager.notify(1, constructorNotif.build());

    }
}