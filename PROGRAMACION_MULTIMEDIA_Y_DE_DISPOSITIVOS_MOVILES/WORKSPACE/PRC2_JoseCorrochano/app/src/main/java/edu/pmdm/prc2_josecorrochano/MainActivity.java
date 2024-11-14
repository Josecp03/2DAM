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
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
    private int numFilas = 8;
    private int numColumnas = 8;
    private int numMinas = 10;
    private int bombaSeleccionada = R.mipmap.ic_bomba_menu;
    private int[][] matriz = new int[8][8];
    private RadioButton rbPrincipiante = null;
    private RadioButton rbAmateur = null;
    private RadioButton rbAvanzado = null;

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

        // Obtener el GridLayout del XML
        g = findViewById(R.id.tablero);

        // Configurar las propiedades del GridLayout
        configurarLayout(g);

        // Crear la matriz de minas y números
        matriz = crearMatriz(numFilas, numColumnas, numMinas);

        // Rellenar la matriz
        rellenarMatriz(matriz, g);

    }

    private void configurarLayout(GridLayout g) {

        g.setRowCount(numFilas);
        g.setColumnCount(numColumnas);
        g.setUseDefaultMargins(true);
        g.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
    }

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

    private void rellenarMatriz(int[][] matriz, GridLayout g) {
        g.removeAllViews(); // Limpiar el tablero

        // Crear una matriz de botones para gestionar estados
        Button[][] botones = new Button[numFilas][numColumnas];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                GridLayout.LayoutParams gridParams = new GridLayout.LayoutParams();
                gridParams.width = 0; // Para ocupar todo el espacio disponible
                gridParams.height = 0; // Para ocupar todo el espacio disponible
                gridParams.rowSpec = GridLayout.spec(i, 1, 1f);
                gridParams.columnSpec = GridLayout.spec(j, 1, 1f);

                Button button = new Button(this);
                button.setLayoutParams(gridParams);
                button.setBackgroundColor(Color.LTGRAY); // Color inicial de los botones

                final int fila = i, columna = j; // Variables finales para usar en el listener

                // Listener para manejar el clic del botón
                button.setOnClickListener(v -> {
                    if (matriz[fila][columna] == -1) {
                        // Es una mina, fin del juego
                        button.setBackgroundResource(bombaSeleccionada); // Usar la imagen seleccionada
                        mostrarToast("¡Has perdido!");
                        deshabilitarBotones(botones);
                    } else {
                        // Descubrir casilla
                        descubrirCasilla(fila, columna, matriz, botones);
                        if (verificarVictoria(matriz, botones)) {
                            mostrarToast("¡Has ganado!");
                            deshabilitarBotones(botones);
                        }
                    }
                });

                botones[i][j] = button;
                g.addView(button);
            }
        }
    }


    private void descubrirCasilla(int fila, int columna, int[][] matriz, Button[][] botones) {
        // Si está fuera de los límites o ya está descubierto, no hacer nada
        if (fila < 0 || columna < 0 || fila >= numFilas || columna >= numColumnas || !botones[fila][columna].isEnabled()) {
            return;
        }

        int valor = matriz[fila][columna];
        botones[fila][columna].setEnabled(false); // Desactivar botón
        botones[fila][columna].setBackgroundColor(Color.WHITE); // Cambiar color

        if (valor == 0) {
            botones[fila][columna].setText(""); // Sin texto para casillas vacías
            // Descubrir las celdas adyacentes
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    descubrirCasilla(fila + i, columna + j, matriz, botones);
                }
            }
        } else {
            botones[fila][columna].setText(String.valueOf(valor)); // Mostrar número
        }
    }

    private void deshabilitarBotones(Button[][] botones) {
        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numColumnas; j++) {
                botones[i][j].setEnabled(false);
            }
        }
    }

    private boolean verificarVictoria(int[][] matriz, Button[][] botones) {
        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numColumnas; j++) {
                if (matriz[i][j] != -1 && botones[i][j].isEnabled()) {
                    // Si queda alguna casilla sin descubrir que no sea una mina, no hay victoria
                    return false;
                }
            }
        }
        return true; // Todas las casillas descubiertas correctamente
    }

    private void mostrarToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    private void cambiarDificultad(int filas, int columnas, int minas) {
        // Actualizar configuración
        numFilas = filas;
        numColumnas = columnas;
        numMinas = minas;

        // Limpiar el tablero actual
        g.removeAllViews();

        // Configurar el GridLayout con las nuevas dimensiones
        g.setRowCount(numFilas);
        g.setColumnCount(numColumnas);

        // Crear una nueva matriz y rellenar el tablero
        matriz = crearMatriz(numFilas, numColumnas, numMinas);
        rellenarMatriz(matriz, g);
    }

    private AlertDialog crearDiaogoDificultad() {

        // Inicializar Variables
        AlertDialog dialogo = null;

        // Convertir el archivo XML del diseño del diálogo en un objeto View para poder utilizarlo
        View alertCustomDialog = LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_dialog_dificultad, null);

        // Constructor del diálogo
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

        // Establecer la vista personalizada (alertCustomDialog) como el contenido del diálogo
        alertDialog.setView(alertCustomDialog);

        // Encontrar los RadioButtons dentro del diseño del diálogo
        rbPrincipiante = alertCustomDialog.findViewById(R.id.radioButtonNivelPrincipiante);
        rbAmateur = alertCustomDialog.findViewById(R.id.radioButtonNivelAmateur);
        rbAvanzado = alertCustomDialog.findViewById(R.id.radioButtonNivelAvanzado);

        rbPrincipiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarDificultad(8, 8, 10);
            }
        });

        rbAmateur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarDificultad(12, 12, 30);
            }
        });

        rbAvanzado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarDificultad(16, 16, 60);
            }
        });

        // Inicializar variables
        cancelButton = alertCustomDialog.findViewById(R.id.cancelID);

        // Crear el Diálogo
        dialogo = alertDialog.create();

        // Establecer fondo del diálogo transparente
        dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // Añadir evento cuando se pulsa el icono de salir
        AlertDialog finalDialogo = dialogo;
        cancelButton.setOnClickListener(v -> finalDialogo.dismiss());

        // Devolver el diálogo creado
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

        if (items.getSpinnerText().equals("Mina Clásica")) {
            bombaSeleccionada = R.mipmap.ic_bomba_menu;
        }

        if (items.getSpinnerText().equals("Mina Bomber")) {
            bombaSeleccionada = R.mipmap.ic_bomba_bomber;
        }

        if (items.getSpinnerText().equals("Dinamita")) {
            bombaSeleccionada = R.mipmap.ic_dinamita;
        }

        if (items.getSpinnerText().equals("Granada")) {
            bombaSeleccionada = R.mipmap.ic_granada;
        }

        if (items.getSpinnerText().equals("Mina Submarina")) {
            bombaSeleccionada = R.mipmap.ic_bomba_submarina;
        }

        if (items.getSpinnerText().equals("Coctel Molotov")) {
            bombaSeleccionada = R.mipmap.ic_coctel_molotov;
        }

        rellenarMatriz(matriz, g);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }







}