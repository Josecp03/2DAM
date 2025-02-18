package edu.pmdm.fotosvideos_joscorrochano;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FotoActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private static final int CAPTURA_IMAGEN = 100;
    private static final int RC_CAMERA_PERMISSION = 101;
    private int cropCount = 0;
    private String mRutaTemporal = "";

    // Controles de la UI
    ImageView mImageView;
    SeekBar seekBarBrillo;
    CheckBox checkBoxGris;
    CheckBox checkBoxInvertir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);

        // Referencias a la UI
        mImageView = findViewById(R.id.imageView);
        seekBarBrillo = findViewById(R.id.seekBarBrillo);
        seekBarBrillo.setOnSeekBarChangeListener(this);
        seekBarBrillo.setProgress(100);
        checkBoxGris = findViewById(R.id.checkBoxGris);
        checkBoxInvertir = findViewById(R.id.checkBoxInvertir);

        // Botones de rotar y recortar
        findViewById(R.id.buttonRotar).setOnClickListener(v -> rotarImagen());
        findViewById(R.id.buttonRecortar).setOnClickListener(v -> recortarImagen());

        // Solicitar el permiso de cámara en esta actividad
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    RC_CAMERA_PERMISSION);
        }
    }

    // Manejar la respuesta de la solicitud de permisos en FotoActivity
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RC_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permiso de cámara concedido", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void hacerFoto(View v) {

        // Verificar que se tenga el permiso de cámara antes de proceder
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Se necesita permiso de cámara para tomar la foto", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    RC_CAMERA_PERMISSION);
            return;
        }

        Intent intentFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA_ANY)) {
            File fileFoto = null;
            try {
                fileFoto = crearFicheroFotoTemporal();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (fileFoto != null) {
                mRutaTemporal = fileFoto.getAbsolutePath();
                Uri fotoUri = FileProvider.getUriForFile(this,
                        getApplicationContext().getPackageName() + ".provider",
                        fileFoto);
                intentFoto.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);
                startActivityForResult(intentFoto, CAPTURA_IMAGEN);
            }
        }
    }

    private File crearFicheroFotoTemporal() throws IOException {
        String tiempo = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String nombre_fichero = "TMP_JPEG_" + tiempo + "_";
        File directorioTemporal = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(nombre_fichero, ".jpg", directorioTemporal);
    }

    private File crearFicheroFotoPermanente() throws IOException {
        String tiempo = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String nombre_fichero = "JPEG_" + tiempo + "_";
        File directorioPublico = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(nombre_fichero, ".jpg", directorioPublico);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURA_IMAGEN && resultCode == RESULT_OK) {
            reescalaYAplicaEfectos();
        }
    }

    private void añadirAGaleria(String ruta) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(ruta);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        sendBroadcast(mediaScanIntent);
    }

    private void reescalaYAplicaEfectos() {
        if (mRutaTemporal == null || mRutaTemporal.isEmpty()) return;
        Bitmap bitmapOriginal = BitmapFactory.decodeFile(mRutaTemporal);
        int progBrillo = seekBarBrillo.getProgress();
        float offsetBrillo = progBrillo - 100;
        ColorMatrix cm = new ColorMatrix(new float[]{
                1, 0, 0, 0, offsetBrillo,
                0, 1, 0, 0, offsetBrillo,
                0, 0, 1, 0, offsetBrillo,
                0, 0, 0, 1, 0
        });
        if (checkBoxGris.isChecked()) {
            ColorMatrix cmGray = new ColorMatrix();
            cmGray.setSaturation(0f);
            cm.postConcat(cmGray);
        }
        if (checkBoxInvertir.isChecked()) {
            ColorMatrix cmInvert = new ColorMatrix(new float[]{
                    -1, 0,  0, 0, 255,
                    0, -1, 0, 0, 255,
                    0,  0, -1, 0, 255,
                    0,  0,  0, 1,   0
            });
            cm.postConcat(cmInvert);
        }
        Bitmap bitmapFinal = Bitmap.createBitmap(bitmapOriginal.getWidth(),
                bitmapOriginal.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapFinal);
        android.graphics.Paint paint = new android.graphics.Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        canvas.drawBitmap(bitmapOriginal, 0, 0, paint);
        mImageView.getLayoutParams().width = bitmapFinal.getWidth();
        mImageView.getLayoutParams().height = bitmapFinal.getHeight();
        mImageView.setImageBitmap(bitmapFinal);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        reescalaYAplicaEfectos();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) { }
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) { }

    private void rotarImagen() {
        mImageView.buildDrawingCache();
        Bitmap bitmapActual = mImageView.getDrawingCache();
        if (bitmapActual == null) return;
        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        Bitmap bitmapRotado = Bitmap.createBitmap(bitmapActual, 0, 0,
                bitmapActual.getWidth(), bitmapActual.getHeight(), matrix, true);
        mImageView.getLayoutParams().width = bitmapRotado.getWidth();
        mImageView.getLayoutParams().height = bitmapRotado.getHeight();
        mImageView.setImageBitmap(bitmapRotado);
        mImageView.destroyDrawingCache();
    }

    private void recortarImagen() {
        if (cropCount >= 2) {
            Toast.makeText(this, "No se pueden recortar más de 2 veces", Toast.LENGTH_SHORT).show();
            return;
        }
        cropCount++;
        mImageView.buildDrawingCache();
        Bitmap bitmapActual = mImageView.getDrawingCache();
        if (bitmapActual == null) return;
        int w = bitmapActual.getWidth();
        int h = bitmapActual.getHeight();
        int nuevoW = w / 2;
        int nuevoH = h / 2;
        int offsetX = (w - nuevoW) / 2;
        int offsetY = (h - nuevoH) / 2;
        Bitmap bitmapRecortado = Bitmap.createBitmap(bitmapActual, offsetX, offsetY, nuevoW, nuevoH);
        mImageView.getLayoutParams().width = bitmapRecortado.getWidth();
        mImageView.getLayoutParams().height = bitmapRecortado.getHeight();
        mImageView.setImageBitmap(bitmapRecortado);
        mImageView.destroyDrawingCache();
    }

    public void guardarFoto(View view) {
        mImageView.buildDrawingCache();
        Bitmap bitmapActual = mImageView.getDrawingCache();
        if (bitmapActual == null) {
            Toast.makeText(this, "No hay imagen para guardar", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            // Se crea un archivo permanente en el directorio público
            File fileGuardado = crearFicheroFotoPermanente();
            FileOutputStream fos = new FileOutputStream(fileGuardado);
            bitmapActual.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            Toast.makeText(this, "Foto guardada en " + fileGuardado.getAbsolutePath(), Toast.LENGTH_LONG).show();
            añadirAGaleria(fileGuardado.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al guardar la foto", Toast.LENGTH_SHORT).show();
        }
        mImageView.destroyDrawingCache();
    }
}
