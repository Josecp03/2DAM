package edu.pmdm.fotosvideos_joscorrochano;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity implements MediaController.MediaPlayerControl {

    private static final int REQUEST_VIDEO = 123;
    private VideoView videoView;
    private MediaController mediaController;
    private Handler handler;
    private MediaPlayer mediaPlayer;
    private float currentSpeed = 1.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoView = findViewById(R.id.videoView);

        // El MediaController se asocia a esta Activity y controlará 'videoView'
        mediaController = new MediaController(this);
        mediaController.setMediaPlayer(this);

        // Vincula el controller a la vista de Video
        videoView.setMediaController(mediaController);

        // Un Handler para mostrar u ocultar el controller cuando el video se prepare
        handler = new Handler();

        // Listener de cuando el video esté listo para reproducirse
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer = mp;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    mp.setPlaybackParams(mp.getPlaybackParams().setSpeed(currentSpeed));
                }
                mediaController.show(0);  // 0 -> que no desaparezca automáticamente
                videoView.start();
            }
        });
    }

    public void capturarVideo(View v) {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(intent, REQUEST_VIDEO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_VIDEO && resultCode == RESULT_OK && data != null) {
            Uri videoUri = data.getData();
            if (videoUri != null) {
                // Asigna el URI al VideoView
                videoView.setVideoURI(videoUri);

            }
        }
    }

    @Override
    public void start() {
        if (!videoView.isPlaying()) {
            videoView.start();
        }
    }

    @Override
    public void pause() {
        if (videoView.isPlaying()) {
            videoView.pause();
        }
    }

    @Override
    public int getDuration() {
        return videoView.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        return videoView.getCurrentPosition();
    }

    @Override
    public void seekTo(int pos) {
        videoView.seekTo(pos);
    }

    @Override
    public boolean isPlaying() {
        return videoView.isPlaying();
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        return videoView.getAudioSessionId();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (!mediaController.isShowing()) {
                mediaController.show(0);
            } else {
                mediaController.hide();
            }
        }
        return false;
    }

    public void velocidadMedia(View v) {
        cambiarVelocidad(0.5f);
    }

    public void velocidadNormal(View v) {
        cambiarVelocidad(1.0f);
    }

    public void velocidadDoble(View v) {
        cambiarVelocidad(2.0f);
    }

    private void cambiarVelocidad(float speed) {
        currentSpeed = speed;
        if (mediaPlayer != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                mediaPlayer.setPlaybackParams(
                        mediaPlayer.getPlaybackParams().setSpeed(speed));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}