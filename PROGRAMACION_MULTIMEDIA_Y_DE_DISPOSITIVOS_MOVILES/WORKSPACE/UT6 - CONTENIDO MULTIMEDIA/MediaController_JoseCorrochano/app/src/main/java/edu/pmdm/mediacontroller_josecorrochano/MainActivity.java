package edu.pmdm.mediacontroller_josecorrochano;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.graphics.Insets;
import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.provider.OpenableColumns;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Atributos
    private static final String TAG = "MainActivity";
    private static final int REQUEST_CODE_SELECT_AUDIO = 100;
    private static final int REQUEST_PERMISSION_READ_EXTERNAL = 200;
    private static final int REQUEST_PERMISSION_NOTIFICATION = 300;
    private MediaPlayerService mediaService;
    private boolean bound = false;
    private TextView tvTime;
    private ImageButton btnPlay, btnPause, btnStop, btnNext, btnPrevious;
    private Button btnSelect;
    private Handler handler = new Handler();
    private Uri pendingAudioUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Solicitar permiso para notificaciones en Android 13 o superior
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},
                        REQUEST_PERMISSION_NOTIFICATION);
            }
        }

        // Inicializar vistas de la interfaz
        tvTime = findViewById(R.id.tvTime);
        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        btnStop = findViewById(R.id.btnStop);
        btnSelect = findViewById(R.id.btnSelect);
        btnNext = findViewById(R.id.btnNext);
        btnPrevious = findViewById(R.id.btnPrevious);

        // Configurar listener para el botón Play
        btnPlay.setOnClickListener(v -> {
            if (bound) {
                mediaService.play();
            }
        });

        // Configurar listener para el botón Pause
        btnPause.setOnClickListener(v -> {
            if (bound) {
                mediaService.pause();
            }
        });

        // Configurar listener para el botón Stop
        btnStop.setOnClickListener(v -> {
            if (bound) {
                mediaService.stopPlayback();
            }
        });

        // Configurar listener para el botón Next (siguiente pista)
        btnNext.setOnClickListener(v -> {
            if (bound) {
                mediaService.nextTrack();
            }
        });

        // Configurar listener para el botón Previous (pista anterior)
        btnPrevious.setOnClickListener(v -> {
            if (bound) {
                mediaService.previousTrack();
            }
        });

        // Configurar listener para el botón de seleccionar archivo de audio
        btnSelect.setOnClickListener(v -> {

            // Seleccionar el permiso adecuado según la versión de Android
            String permission = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) ?
                    Manifest.permission.READ_MEDIA_AUDIO :
                    Manifest.permission.READ_EXTERNAL_STORAGE;

            // Verificar si se ha concedido el permiso; si no, solicitarlo
            if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{permission}, REQUEST_PERMISSION_READ_EXTERNAL);
            } else {
                selectAudioFile();
            }

        });
    }

    // Gestionar la conexión y desconexión con el servicio.
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            // Obtener la instancia del servicio a partir del binder
            MediaPlayerService.LocalBinder binder = (MediaPlayerService.LocalBinder) service;
            mediaService = binder.getService();
            bound = true;

            // Actualizar la interfaz inmediatamente
            updateTime();

            // Si existe un URI pendiente, se procesa
            if (pendingAudioUri != null) {
                processSelectedAudio(pendingAudioUri);
                pendingAudioUri = null;
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bound = false;
        }

    };

    // Actualizar el tiempo y el título de la pista cada segundo
    private Runnable updateTimeRunnable = new Runnable() {
        @Override
        public void run() {
            updateTime();
            handler.postDelayed(this, 1000);
        }
    };

    // Formatear el tiempo a "mm:ss"
    private String formatTime(int millis) {
        int seconds = millis / 1000;
        int minutes = seconds / 60;
        seconds %= 60;
        return String.format("%02d:%02d", minutes, seconds);
    }


    // Actualizar el TextView del tiempo y el título de la pista actual
    private void updateTime() {
        if (bound && mediaService != null) {
            int current = mediaService.getCurrentPosition();
            int duration = mediaService.getDuration();
            tvTime.setText(formatTime(current) + " / " + formatTime(duration));
            TextView titleView = findViewById(R.id.textView);
            titleView.setText(mediaService.getCurrentTrackTitle());
        }
    }

    // Iniciar la actividad para seleccionar un archivo de audio desde el almacenamiento
    private void selectAudioFile() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("audio/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false);
        startActivityForResult(intent, REQUEST_CODE_SELECT_AUDIO);
    }


    // Se inicia y se enlaza el servicio, además de arrancar la actualización periódica de la UI
    @Override
    protected void onStart() {
        super.onStart();

        Intent serviceIntent = new Intent(this, MediaPlayerService.class);

        // Se inicia el servicio para que siga funcionando en segundo plano
        startService(serviceIntent);

        // Se enlaza el servicio a la actividad
        bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE);

        // Iniciar la ejecución periódica del Runnable para actualizar la interfaz
        handler.post(updateTimeRunnable);

    }

    // Se desconecta el servicio y se detiene la actualización de la interfaz.
    @Override
    protected void onStop() {
        super.onStop();

        if (bound) {
            unbindService(connection);
            bound = false;
        }

        // Remover callbacks para evitar fugas de memoria
        handler.removeCallbacks(updateTimeRunnable);
    }

    // Se procesa el resultado de la actividad de selección de audio
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SELECT_AUDIO && resultCode == RESULT_OK) {
            if (data != null) {
                Uri audioUri = data.getData();
                if (audioUri != null) {

                    // Solicitar permiso persistente para acceder al URI seleccionado
                    final int takeFlags = Intent.FLAG_GRANT_READ_URI_PERMISSION & Intent.FLAG_GRANT_READ_URI_PERMISSION;
                    getContentResolver().takePersistableUriPermission(audioUri, takeFlags);

                    if (bound && mediaService != null) {
                        processSelectedAudio(audioUri);
                    } else {
                        pendingAudioUri = audioUri;
                    }

                }

            }

        }

    }

    // Procesar el archivo de audio seleccionado
    private void processSelectedAudio(Uri audioUri) {

        // Verificar si la pista ya se encuentra en la lista de reproducción
        if (mediaService.getPlaylist().contains(audioUri)) {
            Toast.makeText(this, "Esta canción ya se encuentra en la lista de reproducción", Toast.LENGTH_SHORT).show();
            return;
        }

        // Agregar el archivo a la playlist
        mediaService.addToPlaylist(audioUri);

        // Inicializar el MediaPlayer con la nueva pista
        mediaService.initMediaPlayer(audioUri);

        // Forzar la actualización inmediata de la interfaz (tiempo y título)
        updateTime();

        // Obtener el nombre del archivo para notificar al usuario
        String fileName = getFileName(audioUri);
        Toast.makeText(this, "Archivo guardado correctamente", Toast.LENGTH_SHORT).show();

    }

    // Obtener el nombre del archivo a partir de su URI utilizando un Cursor
    private String getFileName(Uri uri) {

        String result = "";
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                    result = cursor.getString(nameIndex);
                }
            } finally {
                cursor.close();
            }
        }
        return result;

    }

    // Procesar el resultado de las solicitudes de permisos.
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_READ_EXTERNAL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                selectAudioFile();
            } else {
                Toast.makeText(this, "Permiso denegado para acceder a archivos", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_PERMISSION_NOTIFICATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permiso de notificaciones concedido", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No se otorgó permiso para notificaciones", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
