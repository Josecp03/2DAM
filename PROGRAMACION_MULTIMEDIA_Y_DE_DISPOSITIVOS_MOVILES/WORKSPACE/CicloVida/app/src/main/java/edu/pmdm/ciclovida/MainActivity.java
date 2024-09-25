package edu.pmdm.ciclovida;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    int i = 0;

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
        i++;
        Log.v("TAG CICLOVIDA", "CICLO DE VIDA: ONCREATE");

    }

    @Override
    protected void onStart() {
        super.onStart();
        i++;
        Log.d("TAG CICLOVIDA", "CICLO DE VIDA: ONSTART");
    }

    @Override
    protected void onResume() {
        super.onResume();
        i--;
        Log.w("TAG CICLOVIDA", "CICLO DE VIDA: ONRESUME");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("TAG CICLOVIDA", "CICLO DE VIDA: ONPAUSE");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("TAG CICLOVIDA", "CICLO DE VIDA: ONSTOP");
    }
}