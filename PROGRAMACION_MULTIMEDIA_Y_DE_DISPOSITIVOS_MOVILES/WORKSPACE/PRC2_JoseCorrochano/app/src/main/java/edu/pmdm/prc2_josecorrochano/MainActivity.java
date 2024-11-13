package edu.pmdm.prc2_josecorrochano;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import edu.pmdm.prc2_josecorrochano.clasesSpinner.CustomAdapter;
import edu.pmdm.prc2_josecorrochano.clasesSpinner.CustomItems;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ImageButton cancelButton = null;
    private AlertDialog dialogoInstrucciones = null;
    private AlertDialog dialogoDificultad = null;
    private AlertDialog dialogoBombas = null;
    private Toolbar toolbarl = null;
    private ImageView ic_bomba = null;
    private Spinner customSpinner = null;
    private GridLayout g = null;
    private int numFilas = 0;
    private int numColumnas = 0;
    private int numMinas = 0;

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

        // Configurar el menu
        toolbarl = (Toolbar) findViewById(R.id.toolbar);
        toolbarl.setTitleTextColor(getResources().getColor(R.color.white));
        toolbarl.setOverflowIcon(getDrawable(R.drawable.ic_menu_white));
        setSupportActionBar(toolbarl);

        // Crear los dialogos
        dialogoInstrucciones = crearDiaogoInstrucciones();
        dialogoDificultad = crearDiaogoDificultad();
        dialogoBombas = crearDiaogoBombas();


        ic_bomba = findViewById(R.id.imageViewBomba);

        ic_bomba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoBombas.show();
            }
        });

        // Obtener el ConstraintLayout principal
        ConstraintLayout constraintLayout = findViewById(R.id.main);

        // Crear el GridLayout programáticamente
        g = new GridLayout(this);
        g.setLayoutParams(new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
        ));

        // Configurar las propiedades del GridLayout
        numFilas = 8;
        numColumnas = 8;
        numMinas = 10;
        g.setRowCount(numFilas);
        g.setColumnCount(numColumnas);
        g.setUseDefaultMargins(true);
        g.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        // Agregar restricciones al GridLayout para que ocupe todo el espacio
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) g.getLayoutParams();
        params.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        params.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        params.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
        params.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        g.setLayoutParams(params);

        // Crear la matriz de minas y números
        int[][] matriz = crearMatriz(numFilas, numColumnas, numMinas);

        // Añadir botones al GridLayout según la matriz
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                GridLayout.LayoutParams gridParams = new GridLayout.LayoutParams();
                gridParams.width = 0; // Para ocupar todo el espacio disponible
                gridParams.height = 0; // Para ocupar todo el espacio disponible
                gridParams.rowSpec = GridLayout.spec(i, 1, 1f);
                gridParams.columnSpec = GridLayout.spec(j, 1, 1f);

                if (matriz[i][j] == -1) {
                    ImageButton imageButton = new ImageButton(this);
                    imageButton.setImageResource(R.drawable.ic_launcher_background);
                    imageButton.setLayoutParams(gridParams);
                    imageButton.setScaleType(ImageButton.ScaleType.CENTER_CROP);
                    g.addView(imageButton);
                } else {
                    Button button = new Button(this);
                    button.setText(String.valueOf(matriz[i][j]));
                    button.setLayoutParams(gridParams);
                    g.addView(button);
                }
            }
        }

        // Añadir el GridLayout al ConstraintLayout principal
        constraintLayout.addView(g);




    }

    private AlertDialog crearDiaogoDificultad() {

        // Inicializar Variables
        AlertDialog dialogo = null;

        // Convertir el archivo XML del diseño del diálogo en un objeto View para poder utilizarlo
        View alertCustomDialog = LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_dialog_dificultad,null);

        // Constructor del diálogo
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

        // Establecer la vista personalizada (alertCustomDialog) como el contenido del diálogo
        alertDialog.setView(alertCustomDialog);

        // Inicializar variables
        cancelButton = (ImageButton) alertCustomDialog.findViewById(R.id.cancelID);

        // Crear el Diálogo
        dialogo = alertDialog.create();

        // Establecer fondo del diálogo transparente
        dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // Añadir evento cuando se pulsa el icono de salir
        AlertDialog finalDialogo = dialogo;
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalDialogo.cancel();
            }
        });

        // Devolver el dialogo creado
        return dialogo;

    }

    private AlertDialog crearDiaogoInstrucciones() {

        // Inicializar Variables
        AlertDialog dialogo = null;

        // Convertir el archivo XML del diseño del diálogo en un objeto View para poder utilizarlo
        View alertCustomDialog = LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_dialog_instrucciones,null);

        // Constructor del diálogo
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

        // Establecer la vista personalizada (alertCustomDialog) como el contenido del diálogo
        alertDialog.setView(alertCustomDialog);

        // Inicializar variables
        cancelButton = (ImageButton) alertCustomDialog.findViewById(R.id.cancelID);

        // Crear el Diálogo
        dialogo = alertDialog.create();

        // Establecer fondo del diálogo transparente
        dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // Añadir evento cuando se pulsa el icono de salir
        AlertDialog finalDialogo = dialogo;
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalDialogo.cancel();
            }
        });

        // Devolver el dialogo creado
        return dialogo;

    }

    private AlertDialog crearDiaogoBombas() {

        // Inicializar Variables
        AlertDialog dialogo = null;

        // Convertir el archivo XML del diseño del diálogo en un objeto View para poder utilizarlo
        View alertCustomDialog = LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_dialog_bombas, null);

        // Constructor del diálogo
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

        // Establecer la vista personalizada (alertCustomDialog) como el contenido del diálogo
        alertDialog.setView(alertCustomDialog);

        // Inicializar variables
        cancelButton = (ImageButton) alertCustomDialog.findViewById(R.id.cancelID);
        customSpinner = alertCustomDialog.findViewById(R.id.spinner); // <- Cambio aquí

        // Crear el Diálogo
        dialogo = alertDialog.create();

        // Establecer fondo del diálogo transparente
        dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // Configurar el adaptador para el Spinner
        ArrayList<CustomItems> customList = new ArrayList<>();
        customList.add(new CustomItems("Mina Clásica", R.mipmap.ic_bomba_menu));
        customList.add(new CustomItems("Mina Bomber", R.mipmap.ic_bomba_bomber));
        customList.add(new CustomItems("Dinamita", R.mipmap.ic_dinamita));
        customList.add(new CustomItems("Granada", R.mipmap.ic_granada));
        customList.add(new CustomItems("Mina Submarina", R.mipmap.ic_bomba_submarina));
        customList.add(new CustomItems("Coctel Molotov", R.mipmap.ic_coctel_molotov));

        CustomAdapter customAdapter = new CustomAdapter(this, customList);

        if (customSpinner != null) {
            customSpinner.setAdapter(customAdapter);
            customSpinner.setOnItemSelectedListener(this);
        }

        // Añadir evento cuando se pulsa el icono de salir
        AlertDialog finalDialogo = dialogo;
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalDialogo.cancel();
            }
        });

        // Devolver el dialogo creado
        return dialogo;
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        // Inicializar la variable id para idnetificar cada uno de los items del menú
        int id = item.getItemId();

        // Comprobar el item que se ha seleccionado
        if (id == R.id.item1) {

            // Mostrar el diálogo de instrucciones
            dialogoInstrucciones.show();

        } else if (id == R.id.item2) {
            Toast.makeText(this, "Clicaste el boton 2", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.item3) {

            // Mostrar el diálogo de dificultad
            dialogoDificultad.show();

        }

        return super.onOptionsItemSelected(item);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        CustomItems items = (CustomItems) parent.getSelectedItem();
        Toast.makeText(this, items.getSpinnerText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    // Método para crear la matriz con minas y números
    public int[][] crearMatriz(int numFilas, int numColumnas, int numMinas) {
        int[][] matriz = new int[numFilas][numColumnas];
        int minasColocadas = 0;

        // Colocar minas (-1) en posiciones aleatorias
        while (minasColocadas < numMinas) {
            int fila = (int) (Math.random() * numFilas);
            int columna = (int) (Math.random() * numColumnas);

            // Colocar mina si la posición está libre
            if (matriz[fila][columna] != -1) {
                matriz[fila][columna] = -1;
                minasColocadas++;

                // Incrementar el conteo en celdas adyacentes
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        int nuevaFila = fila + i;
                        int nuevaColumna = columna + j;

                        // Verificar los límites y que no sea mina
                        if (nuevaFila >= 0 && nuevaFila < numFilas &&
                                nuevaColumna >= 0 && nuevaColumna < numColumnas &&
                                matriz[nuevaFila][nuevaColumna] != -1) {
                            matriz[nuevaFila][nuevaColumna]++;
                        }
                    }
                }
            }
        }

        return matriz;
    }

}