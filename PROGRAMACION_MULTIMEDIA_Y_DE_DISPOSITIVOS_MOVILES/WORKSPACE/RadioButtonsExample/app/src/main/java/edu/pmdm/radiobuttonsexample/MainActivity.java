package edu.pmdm.radiobuttonsexample;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, CheckBox.OnCheckedChangeListener{

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

        // Registramos el listener del radiobutton
        RadioGroup r = (RadioGroup) findViewById(R.id.rgTeams);
        r.setOnCheckedChangeListener(this);

        // Registrarmos el listener del checkbox
        CheckBox miChk=(CheckBox)findViewById(R.id.chkFutbol);
        miChk.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        TextView t=(TextView)findViewById(R.id.txtMessage);
        if (checkedId == R.id.rbTala) { // Talavera C.F
            t.setText("Buena elección!: El Talavera promete!!");
        } else if (checkedId == R.id.rbMadrid) { // Madrid C.F
            t.setText("El rey de Europa!!");
        } else if (checkedId == R.id.rbBarca) { // F.C Barcelona
            t.setText("El peor de todos");
        } else if (checkedId == R.id.rbValencia) { // Valencia C.F
            t.setText("No está mal..");
        }


    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        TextView t=(TextView)findViewById(R.id.txtMessage);
        if(isChecked)
            t.setText("Te gusta el fútbol!!");
        else
            t.setText("No te gusta el fútbol?!??!!");

    }
}