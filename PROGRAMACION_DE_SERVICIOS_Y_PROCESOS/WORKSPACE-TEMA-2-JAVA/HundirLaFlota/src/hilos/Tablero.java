package hilos;

import java.util.Random;

public class Tablero {
    private char[][] tablero = new char[10][10];

    public Tablero() {
        // Inicializar el tablero con agua
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tablero[i][j] = 'O'; // 'O' representa agua
            }
        }
        colocarBarcos();
    }

    private void colocarBarcos() {
        Random random = new Random();
        int barcosColocados = 0;

        while (barcosColocados < 10) {
            int fila = random.nextInt(10);
            int columna = random.nextInt(10);

            if (tablero[fila][columna] == 'O') { // Solo colocar barco en agua
                tablero[fila][columna] = 'X'; // 'X' representa un barco
                barcosColocados++;
            }
        }
    }

    public String verificarPosicion(int fila, int columna) {
        if (tablero[fila][columna] == 'X') {
            tablero[fila][columna] = '*'; // '*' representa un barco hundido
            return "Hundido";
        } else {
            return "Agua";
        }
    }

    public boolean quedanBarcos() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (tablero[i][j] == 'X') { // Si hay barcos restantes
                    return true;
                }
            }
        }
        return false;
    }
}
