package edu.pmdm.mediacontroller_josecorrochano;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.provider.OpenableColumns;
import androidx.core.app.NotificationCompat;
import androidx.media.app.NotificationCompat.MediaStyle;
import java.io.IOException;
import java.util.ArrayList;


public class MediaPlayerService extends Service {

    // Atributos
    public static final String CHANNEL_ID = "MediaPlayerChannel";
    public static final int NOTIFICATION_ID = 1;
    public static final String ACTION_PLAY = "edu.pmdm.mediacontroller_josecorrochano.ACTION_PLAY";
    public static final String ACTION_PAUSE = "edu.pmdm.mediacontroller_josecorrochano.ACTION_PAUSE";
    public static final String ACTION_STOP = "edu.pmdm.mediacontroller_josecorrochano.ACTION_STOP";
    public static final String ACTION_NEXT = "edu.pmdm.mediacontroller_josecorrochano.ACTION_NEXT";
    public static final String ACTION_PREVIOUS = "edu.pmdm.mediacontroller_josecorrochano.ACTION_PREVIOUS";
    private final ArrayList<Uri> playlist = new ArrayList<>();
    private int currentTrackIndex = 0;
    private SharedPreferences prefs;
    private static final String PREFS_NAME = "MediaPrefs";
    private static final String KEY_POSITION = "lastPosition";
    private static final String KEY_TRACK_INDEX = "currentTrackIndex";
    private static final String KEY_SELECTED_URI = "selectedAudioUri";
    private MediaPlayer mediaPlayer;
    private final IBinder binder = new LocalBinder();

    @Override
    public void onCreate() {
        super.onCreate();

        // Obtener la instancia de SharedPreferences
        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Llamada a crear el canal de notificación
        createNotificationChannel();

        // Llamada a inicializar la playlist con las pistas por defecto
        initDefaultPlaylist();

        // Llamada a restaurar el archivo externo guardado, si existía
        restoreUserSelectedTrack();

        // Llamada a restaura la última reproducción
        restoreLastPlayback();

    }

    // Inicializar la playlist con las pistas por defecto
    private void initDefaultPlaylist() {

        // Crear la URI para cada recurso añadirlas a la playlist
        playlist.add(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.song1));
        playlist.add(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.song2));

    }

    // Restaurar el archivo externo guardado, si existía
    private void restoreUserSelectedTrack() {

        // Obtener la cadena almacenada en las preferencias bajo la clave KEY_SELECTED_URI.
        String storedUri = prefs.getString(KEY_SELECTED_URI, null);

        // Comprobar si se obtuvo una cadena válida
        if (storedUri != null) {

            // Convertir la cadena en un objeto Uri
            Uri userUri = Uri.parse(storedUri);

            // Verificar que la URI no esté ya incluida en la playlist
            if (!playlist.contains(userUri)) {

                // Añadir la URI a la playlist
                playlist.add(userUri);

            }

        }

    }

    // Restaurar la última reproducción
    private void restoreLastPlayback() {

        // Obtener el índice de la última pista reproducida de SharedPreferences
        int index = prefs.getInt(KEY_TRACK_INDEX, 0);

        // Obtener la última posición de reproducción guardada
        int lastPosition = prefs.getInt(KEY_POSITION, 0);

        // Restaurar el índice de la pista actual con el valor guardado
        currentTrackIndex = index;

        // Inicializar el reproductor de medios con la pista correspondiente al índice restaurado
        initMediaPlayer(playlist.get(currentTrackIndex));

        // Comprobar si hay una posición de reproducción guardada mayor a 0
        if (lastPosition > 0) {

            // Mover el reproductor a esa posición
            mediaPlayer.seekTo(lastPosition);

        }

    }

    // Enlazar el servicio con la actividad
    public class LocalBinder extends Binder {
        public MediaPlayerService getService() {
            return MediaPlayerService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    // Inicializar el MediaPlayer con la URI proporcionada
    public void initMediaPlayer(Uri uri) {

        // Liberar cualquier instancia previa de MediaPlayer para evitar conflictos
        releaseMediaPlayer();

        // Crear una nueva instancia de MediaPlayer
        mediaPlayer = new MediaPlayer();

        try {

            // Comprobar si la URI corresponde a un contenido externo
            if ("content".equals(uri.getScheme())) {

                // Obtener un descriptor de archivo para acceder al contenido externo
                AssetFileDescriptor afd = getApplicationContext().getContentResolver().openAssetFileDescriptor(uri, "r");

                // Establecer la fuente de datos del MediaPlayer utilizando el descriptor de archivo
                mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());

                // Cerrar el descriptor de archivo para liberar recursos
                afd.close();

            } else {

                // Si la URI no es de contenido externo, establecer la fuente de datos directamente
                mediaPlayer.setDataSource(getApplicationContext(), uri);

            }

            // Preparar el MediaPlayer antes de iniciar la reproducción
            mediaPlayer.prepare();

            // Obtener la última posición de reproducción guardada
            int lastPosition = prefs.getInt(KEY_POSITION, 0);

            // Comprobar si hay una posición de reproducción guardada mayor a 0
            if (lastPosition > 0) {

                // Mover el reproductor a la posición guardada
                mediaPlayer.seekTo(lastPosition);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Configurar un listener para detectar cuando la pista actual finaliza
        mediaPlayer.setOnCompletionListener(mp -> nextTrack());
    }


    // Liberar el Media Player actual
    private void releaseMediaPlayer() {

        if (mediaPlayer != null) {

            // Comprobar si el MediaPlayer está reproduciendo
            if (mediaPlayer.isPlaying()) {

                // Detener la reproducción antes de liberarlo
                mediaPlayer.stop();

            }

            // Liberar los recursos asociados al MediaPlayer
            mediaPlayer.release();

            // Establecer la referencia del MediaPlayer a null para evitar fugas de memoria
            mediaPlayer = null;

        }


    }

    // Guardar la posición actual de reproducción en SharedPreferences
    private void saveCurrentPosition() {

        // Obtener la posición actual de reproducción
        int pos = mediaPlayer.getCurrentPosition();

        // Guardar la posición en SharedPreferences para restaurarla más tarde
        prefs.edit().putInt(KEY_POSITION, pos).apply();

    }

    // Iniciar la reproducción
    @SuppressLint("ForegroundServiceType")
    public void play() {

        // Comprobar si el MediaPlayer está inicializado y no se está reproduciendo
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {

            // Iniciar la reproducción de audio
            mediaPlayer.start();

            // Iniciar el servicio en primer plano con la notificación de reproducción
            startForeground(NOTIFICATION_ID, buildNotification());
        }

        // Actualizar la notificación para reflejar el estado actual de la reproducción
        updateNotification();

    }

    // Pausar la reproducción
    public void pause() {

        // Comprobar si el MediaPlayer está inicializado y no se está reproduciendo
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {

            // Pausar la reproducción de audio
            mediaPlayer.pause();

            // Guardar la posición actual de la reproducción en SharedPreferences
            saveCurrentPosition();

            // Actualizar la notificación para reflejar el estado de pausa
            updateNotification();

        }

    }

    // Detener la reproducción y restaurar a 0
    public void stopPlayback() {

        // Detener la reproducción actual
        mediaPlayer.stop();
        try {

            // Preparar nuevamente el MediaPlayer para futuras reproducciones
            mediaPlayer.prepare();

            // Reiniciar la posición de reproducción al inicio de la pista
            mediaPlayer.seekTo(0);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Guardar la posición actual en SharedPreferences
        saveCurrentPosition();

        // Detener el servicio en primer plano y eliminar la notificación
        stopForeground(true);

    }

    // Devolver la posición actual de reproducción
    public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }

    // Devolver la duración total del audio
    public int getDuration() {
        return mediaPlayer != null ? mediaPlayer.getDuration() : 0;
    }

    // Comprobar si el audio está reproduciendo
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    // Pasar a la siguiente pista de la playlist
    public void nextTrack() {

        // Comprobar si la lista de reproducción no está vacía
        if (!playlist.isEmpty()) {

            // Incrementar el índice de la pista actual para avanzar a la siguiente
            currentTrackIndex = (currentTrackIndex + 1) % playlist.size();

            // Guardar la nueva posición e índice de la pista actual en SharedPreferences
            prefs.edit()
                    .putInt(KEY_POSITION, 0) // Reiniciar la posición de reproducción a 0
                    .putInt(KEY_TRACK_INDEX, currentTrackIndex) // Guardar el nuevo índice de la pista
                    .apply(); // Aplicar los cambios en las preferencias

            // Inicializar el MediaPlayer con la nueva pista seleccionada
            initMediaPlayer(playlist.get(currentTrackIndex));

            // Comenzar la reproducción de la nueva pista
            play();

        }

    }

    // Pasar a la pista anterior de la playlist
    public void previousTrack() {

        // Comprobar si la lista de reproducción no está vacía
        if (!playlist.isEmpty()) {

            // Decrementar el índice de la pista actual para retroceder a la anterior
            currentTrackIndex = (currentTrackIndex - 1 + playlist.size()) % playlist.size();

            // Guardar la nueva posición e índice de la pista actual en SharedPreferences
            prefs.edit()
                    .putInt(KEY_POSITION, 0) // Reiniciar la posición de reproducción a 0
                    .putInt(KEY_TRACK_INDEX, currentTrackIndex) // Guardar el nuevo índice de la pista
                    .apply(); // Aplicar los cambios en las preferencias

            // Inicializar el MediaPlayer con la nueva pista seleccionada
            initMediaPlayer(playlist.get(currentTrackIndex));

            // Comenzar la reproducción de la nueva pista
            play();

        }

    }

    // Agregar la URI externa a la playlist
    public void addToPlaylist(Uri uri) {

        // Comprobar si la URI ya se encuentra en la lista para evitar duplicados
        if (!playlist.contains(uri)) {

            // Añadir la URI a la playlist
            playlist.add(uri);

            // Actualizar el índice de la pista actual para que el nuevo archivo añadido se convierta en la pista actual
            currentTrackIndex = playlist.size() - 1;

            // Guardar en SharedPreferences la URI y el índice actualizado de la pista actual
            prefs.edit()
                    .putString(KEY_SELECTED_URI, uri.toString()) // Almacenar la URI convertida a cadena bajo la clave KEY_SELECTED_URI
                    .putInt(KEY_TRACK_INDEX, currentTrackIndex) // Guardar el índice actualizado de la pista actual bajo la clave KEY_TRACK_INDEX
                    .apply(); // Aplicar los cambios de forma asíncrona

        }

    }

    // Devolver la playlist actual
    public ArrayList<Uri> getPlaylist() {
        return playlist;
    }

    // Devolver el título de la pista actual
    public String getCurrentTrackTitle() {

        // Obtener la URI de la pista actual en la lista de reproducción
        Uri currentUri = playlist.get(currentTrackIndex);

        // Comprobar si la URI pertenece a un recurso local en la carpeta raw
        if ("android.resource".equals(currentUri.getScheme())) {

            // Obtener el ID del recurso desde el último segmento de la URI
            int resId = Integer.parseInt(currentUri.getLastPathSegment());

            // Obtener el nombre del recurso usando el ID
            String resourceName = getResources().getResourceEntryName(resId);

            // Asignar nombres personalizados según el recurso
            if ("song1".equals(resourceName)) return "Shake It Off";
            if ("song2".equals(resourceName)) return "Waka Waka";

            // Si no coincide con los nombres predefinidos, devolver el nombre del recurso
            return resourceName;

        } else {

            // Obtener el nombre del archivo de la URI externa
            String displayName = getFileName(currentUri);

            // Comprobar si no se pudo obtener el nombre del archivo
            if (displayName == null || displayName.isEmpty()) {

                // Usar el último segmento de la URI como nombre
                displayName = currentUri.getLastPathSegment();

            }

            // Buscar la posición del último punto en el nombre del archivo
            int dotIndex = displayName.lastIndexOf('.');

            // Comprobar si hay un punto en el nombre del archivo
            if (dotIndex > 0) {

                // Devolver el nombre del archivo sin la extensión
                return displayName.substring(0, dotIndex);

            } else {

                // Si no hay punto, devolver el nombre completo del archivo
                return displayName;

            }

        }
    }


    // Obtener el nombre del archivo a partir de la URI usando un Cursor
    private String getFileName(Uri uri) {

        // Variable para almacenar el nombre del archivo
        String result = null;

        // Intentar obtener el nombre del archivo usando un Cursor
        try (Cursor cursor = getContentResolver().query(uri, null, null, null, null)) {

            // Comprobar si el cursor no es nulo y si contiene al menos una fila
            if (cursor != null && cursor.moveToFirst()) {

                // Obtener el índice de la columna DISPLAY_NAME
                int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);

                // Comprobar si el índice es válido
                if (nameIndex != -1) {

                    // Obtener el nombre del archivo
                    result = cursor.getString(nameIndex);

                }

            }
        }

        // Comprobar si no se obtuvo un nombre válido
        if (result == null) {

            // Devolver el último segmento de la URI como nombre del archivo
            return uri.getLastPathSegment();

        } else {

            // Devolver el nombre del archivo obtenido del Cursor
            return result;

        }

    }

    // Construir la notificación
    private Notification buildNotification() {

        // Crear un Intent para abrir la actividad principal cuando se toque la notificación
        Intent notificationIntent = new Intent(this, MainActivity.class);

        // Crear un PendingIntent asociado al Intent, para que la notificación pueda lanzar la actividad
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);

        // Declarar variables para la acción de Play/Pause en la notificación
        PendingIntent playPauseIntent;
        String playPauseTitle;
        int playPauseIcon;

        // Configurar la acción de Play/Pause según el estado actual de reproducción
        if (isPlaying()) {

            // Si se está reproduciendo, la acción será pausar la música
            playPauseIntent = PendingIntent.getService(this, 1,
                    new Intent(this, MediaPlayerService.class).setAction(ACTION_PAUSE),
                    PendingIntent.FLAG_IMMUTABLE);

            // Etiqueta del botón en la notificación
            playPauseTitle = "Pausar";

            // Icono para el botón de pausa
            playPauseIcon = android.R.drawable.ic_media_pause;

        } else {

            // Si no se está reproduciendo, la acción será reproducir la música
            playPauseIntent = PendingIntent.getService(this, 1,
                    new Intent(this, MediaPlayerService.class).setAction(ACTION_PLAY),
                    PendingIntent.FLAG_IMMUTABLE);

            // Etiqueta del botón en la notificación
            playPauseTitle = "Reproducir";

            // Icono para el botón de reproducción
            playPauseIcon = android.R.drawable.ic_media_play;
        }

        // Configurar la acción para avanzar a la siguiente pista
        PendingIntent nextIntent = PendingIntent.getService(this, 2,
                new Intent(this, MediaPlayerService.class).setAction(ACTION_NEXT),
                PendingIntent.FLAG_IMMUTABLE);

        // Configurar la acción para retroceder a la pista anterior
        PendingIntent previousIntent = PendingIntent.getService(this, 3,
                new Intent(this, MediaPlayerService.class).setAction(ACTION_PREVIOUS),
                PendingIntent.FLAG_IMMUTABLE);

        // Construcción de la notificación con controles multimedia
        return new NotificationCompat.Builder(this, CHANNEL_ID)

                // Título de la notificación
                .setContentTitle("Reproduciendo música")

                // Texto descriptivo de la notificación
                .setContentText("Controla la reproducción desde aquí")

                // Icono pequeño de la notificación
                .setSmallIcon(android.R.drawable.ic_media_play)

                // Asociar el PendingIntent para abrir la app al tocar la notificación
                .setContentIntent(pendingIntent)

                // Hacer que la notificación no pueda ser descartada mientras se reproduce música
                .setOngoing(true)

                // Establecer el color de acento de la notificación (verde en este caso)
                .setColor(Color.GREEN)

                // Agregar el botón de pista anterior con su icono y acción
                .addAction(android.R.drawable.ic_media_previous, "Anterior", previousIntent)

                // Agregar el botón de Play/Pause con su icono y acción
                .addAction(playPauseIcon, playPauseTitle, playPauseIntent)

                // Agregar el botón de pista siguiente con su icono y acción
                .addAction(android.R.drawable.ic_media_next, "Siguiente", nextIntent)

                // Aplicar el estilo MediaStyle para mostrar los controles multimedia
                .setStyle(new MediaStyle().setShowActionsInCompactView(0, 1, 2))

                // Construir y devolver la notificación
                .build();

    }

    // Actualizar la notificación en el NotificationManager.
    private void updateNotification() {

        // Construir la notificación
        Notification notification = buildNotification();

        // Obtener el servicio del sistema para manejar notificaciones
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Mostrar o actualizar la notificación con el ID especificado
        notificationManager.notify(NOTIFICATION_ID, notification);

    }

    @Override
    public void onDestroy() {
        saveCurrentPosition();
        releaseMediaPlayer();
        super.onDestroy();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) pause();
        stopSelf();
        super.onTaskRemoved(rootIntent);
    }

    // Crear el canal de notificación
    private void createNotificationChannel() {

        // Comprobar la versión de Android
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // Crear un canal de notificación con un identificador único y un nivel de importancia bajo
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Media Playback",
                    NotificationManager.IMPORTANCE_LOW);

            // Establecer una descripción para el canal de notificación
            channel.setDescription("Controles de reproducción");

            // Obtener el servicio del sistema que gestiona las notificaciones
            NotificationManager notificationManager = getSystemService(NotificationManager.class);

            // Crear el canal de notificación
            notificationManager.createNotificationChannel(channel);

        }


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.getAction() != null) {
            String action = intent.getAction();
            switch (action) {
                case ACTION_PLAY:
                    play();
                    break;
                case ACTION_PAUSE:
                    pause();
                    break;
                case ACTION_NEXT:
                    nextTrack();
                    break;
                case ACTION_PREVIOUS:
                    previousTrack();
                    break;
                case ACTION_STOP:
                    stopPlayback();
                    break;
                default:
                    // Puedes agregar un log para acciones no reconocidas
                    break;
            }
        }
        // START_STICKY para que el servicio se reinicie si es eliminado por el sistema
        return START_STICKY;
    }


}
