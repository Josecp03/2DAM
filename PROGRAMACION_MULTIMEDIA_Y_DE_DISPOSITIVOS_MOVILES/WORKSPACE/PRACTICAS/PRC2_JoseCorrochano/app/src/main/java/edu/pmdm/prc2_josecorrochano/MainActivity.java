package edu.pmdm.prc2_josecorrochano;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
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

    // Inicializar atributos de la clase
    private ImageButton cancelButton = null;
    private AlertDialog dialogoInstrucciones = null;
    private AlertDialog dialogoDificultad = null;
    private AlertDialog dialogoBombas = null;
    private Toolbar toolbar = null;
    private ImageView ic_bomba = null;
    private Spinner customSpinner = null;
    private GridLayout g = null;
    private int numFilas = 8;
    private int numColumnas = 8;
    private int numMinas = 10;
    private int bombaSeleccionada = R.mipmap.ic_bomba_menu;
    private int[][] matriz = new int[8][8];
    private boolean juegoComenzado = false;
    private RadioButton rbPrincipiante = null;
    private RadioButton rbAmateur = null;
    private RadioButton rbAvanzado = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // Fijar la orientación de la actividad actual a modo vertical
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Método para realizar las configuraciones iniciales tanto del ToolBar como del GridLayout
        configuracionesIniciales();

        // Crear la matriz de minas y números
        matriz = crearMatriz(numFilas, numColumnas, numMinas);

        // Rellenar la matriz
        rellenarMatriz(matriz, g);
    }

    /*
     Método para crear la matriz de números:
        - -1 = mina
        - 0 = sin minas adyacentes
        - n = n minas adyacentes
     */
    public int[][] crearMatriz(int numFilas, int numColumnas, int numMinas) {

        // Inicializar la matriz
        int[][] matriz = new int[numFilas][numColumnas];

        // Inicializar el número de minas colocadas
        int minasColocadas = 0;

        // Bucle hasta que se coloquen todas las minas necesarias
        while (minasColocadas < numMinas) {

            // Generar fila aleatoria
            int fila = (int) (Math.random() * numFilas);

            // Generar columna aleatoria
            int columna = (int) (Math.random() * numColumnas);

            // Comprobar que no se haya colocado ya una mina en las coordenadas generadas
            if (matriz[fila][columna] != -1) {

                // Asignar un 1 a la posición generada
                matriz[fila][columna] = -1;

                // Aumentar contador de minas colocadas
                minasColocadas++;

                // Recorrer las casillas adyacentes
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {

                        // Calcular la coordenada de la celda adyacente actual
                        int nuevaFila = fila + i;
                        int nuevaColumna = columna + j;

                        // Verificar los límites y que no haya mina
                        if (nuevaFila >= 0 && nuevaFila < numFilas && nuevaColumna >= 0 && nuevaColumna < numColumnas && matriz[nuevaFila][nuevaColumna] != -1) {

                            // Incrementa el valor de las celda para reflejar el número de minas adyacentes.
                            matriz[nuevaFila][nuevaColumna]++;

                        }
                    }
                }
            }
        }

        // Devolver la matriz con los valores correspondientes
        return matriz;

    }

    /*
     Método para rellenar la matriz y manejar lo que va sucediendo en el juego
        - Configura y añade los botones necesarios
        - Manejar cuando se haga un click corto en la casilla
        - Manejar cuando se haga un click largo en la casilla
     */
    private void rellenarMatriz(int[][] matriz, GridLayout g) {

        // Limpiar el tablero
        g.removeAllViews();

        // Reiniciar estado de juego
        juegoComenzado = false;

        // Inicializar estructuras auxiliares
        Button[][] botones = new Button[numFilas][numColumnas];
        boolean[][] marcados = new boolean[numFilas][numColumnas];
        int[] minasMarcadasCorrectamente = {0};

        // Recorrer todas las posiciones
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {

                // Configurar parámetros de diseño de cada botón
                GridLayout.LayoutParams gridParams = new GridLayout.LayoutParams();

                // Ocupar el layout entero
                gridParams.width = 0;
                gridParams.height = 0;

                // Especificar la fila y columna donde se colocará el botón con proporciones uniformes
                gridParams.rowSpec = GridLayout.spec(i, 1, 1f);
                gridParams.columnSpec = GridLayout.spec(j, 1, 1f);

                // Crear y personalizar el botón
                Button button = new Button(this);
                button.setLayoutParams(gridParams);
                button.setBackgroundColor(Color.LTGRAY);
                GradientDrawable drawable = new GradientDrawable();
                drawable.setColor(Color.LTGRAY);
                drawable.setStroke(4, Color.BLACK);

                // Asignar el drawable al fondo del botón
                button.setBackground(drawable);

                // Inicializar constantes para poder utilizarlas dentro de los eventos
                final int fila = i;
                final int columna = j;

                // Listener para manejar el clic corto del botón
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // Comprobar que el juego no esté comenzado ya
                        if (!juegoComenzado) {

                            // Cambiar el estado de la variable booleana que indica que el juego ha comenzado
                            juegoComenzado = true;

                        }

                        // Comprobar que la celda no esté marcada
                        if (!marcados[fila][columna]) {

                            // Comprobar si es una mina
                            if (matriz[fila][columna] == -1) {

                                // Mostrar la mina tachada en la casilla seleccionada
                                button.setBackgroundResource(getBombaTachada(bombaSeleccionada));

                                // Mostrar mensaje al usuario de que ha perdido
                                Toast.makeText(MainActivity.this, "¡Has perdido!", Toast.LENGTH_SHORT).show();

                                // Deshabilitar los botones para que el usuario no pueda seguir jugando
                                deshabilitarBotones(botones);

                                // Reproducir sonido de fin del juego
                                reproducirSonidoFinJuego();

                            } else {

                                // Llamar al método correspondiente a descubrir la casilla actual
                                descubrirCasilla(fila, columna, matriz, botones);

                                // Verificar si clickando esta casilla, ya ha completado el juego
                                if (verificarVictoria(matriz, botones, marcados)) {

                                    // Mostrar mensaje al usuario de confirmación
                                    Toast.makeText(MainActivity.this, "¡Has ganado!", Toast.LENGTH_SHORT).show();

                                    // Desactivar los botones para que no pueda realizar ninguna otra acción el usuario
                                    deshabilitarBotones(botones);

                                    // Reporducir sonido de victoria
                                    reproducirSonidoVictoria();

                                }
                            }

                        }

                    }
                });

                // Listener para manejar el click largo del botón
                button.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {

                        // Comprobar si el botón está activado
                        if (botones[fila][columna].isEnabled()) {

                            // Comprobar si la casilla ya está marcada
                            if (!marcados[fila][columna]) {

                                // Comprobar que la casilla sea una mina
                                if (matriz[fila][columna] == -1) {

                                    // Marcar la casilla
                                    marcados[fila][columna] = true;

                                    // Aumentar el contador de minas encontradas
                                    minasMarcadasCorrectamente[0]++;

                                    // Mostrar una bandera en el botón
                                    button.setBackgroundResource(R.mipmap.ic_bandera);

                                    // Mostrar al usuario las minas encontradas de momento
                                    Toast.makeText(MainActivity.this, "Minas marcadas: " + minasMarcadasCorrectamente[0] + "/" + numMinas, Toast.LENGTH_SHORT).show();

                                    // Reporducir sonido de mina encontrada
                                    reproducirSonidoMinaEncontrada();

                                } else {

                                    // Mostrar la bandera tachada
                                    button.setBackgroundResource(R.mipmap.ic_bandera_tachada);

                                    // Mostrar al usuario un mensaje de que ha perdido
                                    Toast.makeText(MainActivity.this, "Has perdido! Marcaste incorrectamente la casilla", Toast.LENGTH_SHORT).show();

                                    // Deshabilitar los botones para que el usuario no pueda realizar cualquier acción más
                                    deshabilitarBotones(botones);

                                    // Reproducir sonido de fin del juego
                                    reproducirSonidoFinJuego();

                                }

                            }

                            // Verificar la victoria después de marcar/desmarcar una bomba
                            if (verificarVictoria(matriz, botones, marcados)) {

                                // Mostrar toast de confirmación de victoria
                                Toast.makeText(MainActivity.this, "Has ganado!", Toast.LENGTH_SHORT).show();

                                // Desahbilitar los botones para que le usuario no pueda realizar ninguna acción despues de ganar
                                deshabilitarBotones(botones);

                                // Reproducir sonido de victoria
                                reproducirSonidoVictoria();

                            }

                        }

                        // Indicar que el evento se manejó
                        return true;
                    }
                });

                // Asignar a la matriz de botones el botón creado
                botones[i][j] = button;

                // Añadir el botón al layout
                g.addView(button);

            }
        }
    }

    /*
     Método para reiniciar el juego:
        - Crear una nueva matriz con valores aleatorios
        - Rellenar el tablero
        - Mostrar Toast con información al usuario
     */
    private void reiniciarJuego() {

        // Crear una nueva matriz con valores de nuevo aleatorios
        matriz = crearMatriz(numFilas, numColumnas, numMinas);

        // Rellenar el tablero con esa nueva matriz
        rellenarMatriz(matriz, g);

        // Mostrar un Toast al usuario con información
        Toast.makeText(this, "Juego reiniciado", Toast.LENGTH_SHORT).show();

    }

    /*
     Método para descubrir la casilla que se
     */
    private void descubrirCasilla(int fila, int columna, int[][] matriz, Button[][] botones) {

        // Comprobar si está dentro de los límites y el botón está habilitado
        if (fila >= 0 && columna >= 0 && fila < numFilas && columna < numColumnas && botones[fila][columna].isEnabled()) {

            // Obtener la posicion en la matriz de la casilla pasada como parámetro
            int valor = matriz[fila][columna];

            // Desactivar el botón
            botones[fila][columna].setEnabled(false);

            // Cambiar el color del fondo del botón
            botones[fila][columna].setBackgroundColor(Color.WHITE);

            // Comprobar si la casilla es 0 para comprobar posteriormente sus adyacentes
            if (valor == 0) {

                // Eliminar el texto de esas casillas que están vacías con un 0
                botones[fila][columna].setText("");

                // Recorrer las casillas adyacentes de forma recursiva
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        descubrirCasilla(fila + i, columna + j, matriz, botones);
                    }
                }

            } else {

                // Mostar el número correspondiente
                botones[fila][columna].setText(String.valueOf(valor));

                // Ajustar el tamaño del texto
                botones[fila][columna].setTextSize(18);

                // Ajustar el color del texto
                botones[fila][columna].setTextColor(Color.BLACK);

            }
        }

    }

    /*
     Método para deshabilitar cada uno de los botones
     */
    private void deshabilitarBotones(Button[][] botones) {

        // Recorrer el tablero
        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numColumnas; j++) {

                // Desactivar el botón actual
                botones[i][j].setEnabled(false);

            }
        }
    }

    /*
     Método para comprobar si el usuario ha ganado verificando:
        - Todas las casillas con minas están correctamente marcadas.
        - Todas las casillas sin minas están descubiertas.
     */
    private boolean verificarVictoria(int[][] matriz, Button[][] botones, boolean[][] marcados) {

        // Inicializar variables
        boolean todasCasillasDescubiertas = true;
        boolean todasMinasMarcadas = true;


        // Recorrer la matriz elemento a elemento
        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numColumnas; j++) {

                // Comprobar si la casilla actual contiene una mina y no está marcada como mina
                if (matriz[i][j] == -1 && !marcados[i][j]) {

                    // Si se cumplen esas condiciones todavía no se han marcado todas las minas
                    todasMinasMarcadas = false;

                }

                // Comprobar si la casilla actual no contiene una mina y el botón aún está habilitado
                if (matriz[i][j] != -1 && botones[i][j].isEnabled()) {

                    // Si se cumplen esas condiciones todavía no se han descubierto todas las casillas
                    todasCasillasDescubiertas = false;

                }
            }
        }

        // El usuario gana cuando se marcan todas las minas, y se descubren todas las casilla
        return todasCasillasDescubiertas && todasMinasMarcadas;
    }

    /*
     Método para cambiar la dificultad:
        - Actualiza número de filas, columnas y minas
        - Limpia el tablero actual para no sobreponer tableros
        - Configura el layout con las nuevas dimensiones
        - Crea y rellena el nuevo tablero
     */
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

        // Crear una nueva matriz y rellenar el nuevo tablero
        matriz = crearMatriz(numFilas, numColumnas, numMinas);
        rellenarMatriz(matriz, g);

    }

    /*
     Método para crear el diálogo donde se selecciona la dificultad:
        - Creación del diálogo
        - Eventos de cada uno de los radioButtons
        - Evento para salir del diálogo
     */
    private AlertDialog crearDiaogoDificultad() {

        // Inicializar Variables
        AlertDialog dialogo = null;

        // Convertir el archivo XML del diseño del diálogo en un objeto View para poder utilizarlo
        View alertCustomDialog = LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_dialog_dificultad, null);

        // Constructor del diálogo
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

        // Establecer la vista personalizada como el contenido del diálogo
        alertDialog.setView(alertCustomDialog);

        // Asignar elementos XML correspondientes
        rbPrincipiante = alertCustomDialog.findViewById(R.id.radioButtonNivelPrincipiante);
        rbAmateur = alertCustomDialog.findViewById(R.id.radioButtonNivelAmateur);
        rbAvanzado = alertCustomDialog.findViewById(R.id.radioButtonNivelAvanzado);

        // Evento cuando se pulsa el radioButton correspondiente a la dificultad principiante
        rbPrincipiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Llamar al método para establecer el número de filas y columnas (8x8) y el número de minas (10)
                cambiarDificultad(8, 8, 10);
            }
        });

        // Evento cuando se pulsa el radioButton correspondiente a la dificultad amateur
        rbAmateur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Llamar al método para establecer el número de filas y columnas (12x12) y el número de minas (30)
                cambiarDificultad(12, 12, 30);
            }
        });

        // Evento cuando se pulsa el radioButton correspondiente a la dificultad avanzado
        rbAvanzado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Llamar al método para establecer el número de filas y columnas (16x16) y el número de minas (60)
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

        // Evento cuando se pulsa el icono de salir
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalDialogo.cancel();
            }
        });

        // Devolver el diálogo creado
        return dialogo;
    }

    /*
    Método para crear un diálogo personalizado sobre las instrucciones del juego:
        - Creación del diálogo
        - Añadir evento cuando se pulsa el icono de salir
     */
    private AlertDialog crearDiaogoInstrucciones() {

        // Inicializar Variables
        AlertDialog dialogo = null;

        // Convertir el archivo XML del diseño del diálogo en un objeto View para poder utilizarlo
        View alertCustomDialog = LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_dialog_instrucciones,null);

        // Constructor del diálogo
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

        // Establecer la vista personalizada como el contenido del diálogo
        alertDialog.setView(alertCustomDialog);

        // Inicializar variables
        cancelButton = alertCustomDialog.findViewById(R.id.cancelID);

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

    /*
     Método para crear un diálogo personalizado sobre la selección de personajes del juego:
        - Creación del diálogo
        - Configuración del Spinner
        - Añadir evento cuando se pulsa el icono de salir
    */
    private AlertDialog crearDiaogoBombas() {

        // Inicializar Variables
        AlertDialog dialogo = null;

        // Convertir el archivo XML del diseño del diálogo en un objeto View para poder utilizarlo
        View alertCustomDialog = LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_dialog_bombas, null);

        // Constructor del diálogo
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

        // Establecer la vista personalizada como el contenido del diálogo
        alertDialog.setView(alertCustomDialog);

        // Asignar elementos XML correspondientes
        cancelButton = alertCustomDialog.findViewById(R.id.cancelID);
        customSpinner = alertCustomDialog.findViewById(R.id.spinner);

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

        // Crear adaptador personalizado para mostrar los contenidos de la lista
        CustomAdapter customAdapter = new CustomAdapter(this, customList);

        // Asignar el adaptador personalizado al Spinner
        customSpinner.setAdapter(customAdapter);

        // Asignar un listener para manejar los eventos de selección del Spinner
        customSpinner.setOnItemSelectedListener(this);

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

    /*
     Método para iniicalizar el menú
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }

    /*
     Método para identificar cual de los elementos del menu es seleccionado
        - item1 = mostrar instrucciones
        - item2 = reiniciar el juego
        - item3 = elegir dificultad
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Obtener el elemento que se ha seleccionado en el menú
        int id = item.getItemId();

        // Comprobar el elemento seleccionado para realizar la acción correspondiente
        if (id == R.id.item1) {

            // Mostrar el diálogo de las instrucciones
            dialogoInstrucciones.show();

        } else if (id == R.id.item2) {

            // Llamar al método para reiniciar el juego
            reiniciarJuego();

        } else if (id == R.id.item3) {

            // Mostrar el diálogo donde seleccionas la dificultad del juego
            dialogoDificultad.show();

        }

        // Llamar al método de la clase padre para manejar cualquier otra acción que no se haya gestionado
        return super.onOptionsItemSelected(item);

    }

    /*
     Método para comprobar que personaje/bomba se ha seleccionado
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        // Asignar en una variable el elemento seleccionado
        CustomItems items = (CustomItems) parent.getSelectedItem();

        // Comprobar el elemento seleccionado y asignar la bomba seleccionada correspondiente
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

        // Llamada al método de rellenar la matriz con la nueva bomba seleccionada
        rellenarMatriz(matriz, g);

    }

    /*
     Método de necesaria implementación pero que no se usa
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /*
     Método para identificar la bomba que se está utilizando para
     asignar esa misma bomba pero tachada
     */
    private int getBombaTachada(int bombaSeleccionada) {

        // Comprobar todas las posibles bombas y aisgnar el mismo recurso, pero con una cruz
        if (bombaSeleccionada == R.mipmap.ic_bomba_menu) {
            return R.mipmap.ic_bomba_tachada;
        } else if (bombaSeleccionada == R.mipmap.ic_bomba_bomber) {
            return R.mipmap.ic_bomba_bomber_tachada;
        } else if (bombaSeleccionada == R.mipmap.ic_dinamita) {
            return R.mipmap.ic_dinamita_tachada;
        } else if (bombaSeleccionada == R.mipmap.ic_granada) {
            return R.mipmap.ic_granada_tachada;
        } else if (bombaSeleccionada == R.mipmap.ic_bomba_submarina) {
            return R.mipmap.ic_bomba_submarina_tachada;
        } else if (bombaSeleccionada == R.mipmap.ic_coctel_molotov) {
            return R.mipmap.ic_coctel_molotov_tachado;
        }

        // Devolver la bomba inicial por defecto
        return R.mipmap.ic_bomba_tachada;
    }

    /*
     Método que realiza las configuraciones inciales de la aplicación:
        - Configurar el ToolBar
        - Crear los diálogos personalizados
        - Añadir Evento para seleccionar los personajes/bombas
        - Configurar el Layout donde va a estar el tablero del juego
     */
    private void configuracionesIniciales () {

        /*
        CONFIGURACIÓN TOOLBAR
         */

        // Asignar elemento XML correspondiente
        toolbar = findViewById(R.id.toolbar);

        // Cambiar el color del texto del ToolBar
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        // Cambiar el icono de los 3 puntitos por uno personalizado
        toolbar.setOverflowIcon(getDrawable(R.drawable.ic_menu_white));

        // Asignar el ToolBar a la actividad
        setSupportActionBar(toolbar);

        /*
        CREACIÓN DIÁLOGOS
         */

        // Crear diálogo de las instrucciones
        dialogoInstrucciones = crearDiaogoInstrucciones();

        // Crear el diálogo de los distintos niveles
        dialogoDificultad = crearDiaogoDificultad();

        // Crear el diálogo de seleccion de personajes/bombas
        dialogoBombas = crearDiaogoBombas();

        /*
        SELECICION PERSONAJES
         */

        // Asignar elemento XML correspondiente
        ic_bomba = findViewById(R.id.imageViewBomba);

        // Añadir evento cuando se pulsa el icono correpondiente a la selección de personajes
        ic_bomba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Comprobar la variabe booleana que nos indica si el juego ha comenzado, para que así el usuario no pueda cambiar de personaje en medio de la partida
                if (juegoComenzado) {

                    // Mostrar un aviso indicando al usuario que no se puede realizar la acción
                    Toast.makeText(MainActivity.this, "No puedes cambiar la bomba una vez comienza la partida", Toast.LENGTH_SHORT).show();

                } else {

                    // Mostrar el diálogo si todavía no ha comenzado la partida
                    dialogoBombas.show();

                }

            }
        });

        /*
        CONFIGURACIÓN LAYOUT
         */

        // Asignar elemento XML correspondiente
        g = findViewById(R.id.tablero);

        // Llamada a método para configurar el layout
        configurarLayout(g);

    }

    /*
     Método para configurar el layout
    */
    private void configurarLayout(GridLayout g) {

        // Establecer número de filas del layout
        g.setRowCount(numFilas);

        // Establecer número de columnas del layout
        g.setColumnCount(numColumnas);

        // Usar márgenes predeterminados
        g.setUseDefaultMargins(true);

        // Alinear adecuadamente los elementos del grid
        g.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

    }

    /*
     Método para reproducir sonido de victoria
     */
    public void reproducirSonidoVictoria() {

        // Inicializar el sonido
        MediaPlayer mpGong = MediaPlayer.create(getApplicationContext(), R.raw.victoria);

        // Reporducir el sonido
        mpGong.start();

    }

    /*
     Método para reproducir sonido de fin del juego cuando pierdes
     */
    public void reproducirSonidoFinJuego() {

        // Inicializar el sonido
        MediaPlayer mpGong = MediaPlayer.create(getApplicationContext(), R.raw.finjuego);

        // Reporducir el sonido
        mpGong.start();

    }

    /*
     Método para reproducir sonido cuando encuentras una mina correctamen
     */
    public void reproducirSonidoMinaEncontrada() {

        // Inicializar el sonido
        MediaPlayer mpGong = MediaPlayer.create(getApplicationContext(), R.raw.minaencontrada);

        // Reporducir el sonido
        mpGong.start();

    }

}