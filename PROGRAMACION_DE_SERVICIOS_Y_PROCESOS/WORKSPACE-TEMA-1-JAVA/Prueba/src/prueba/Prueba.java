package prueba;

import java.util.Random;

public class Prueba {

    public static void main(String[] args) {
        
        int[][] matriz = crearMatriz(8, 8, 10);
        
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] == -1) {
                    System.out.print("* "); // Representación visual de una mina
                } else {
                    System.out.print(matriz[i][j] + " ");
                }
            }
            System.out.println();
        }
        
    }
    
    public static int[][] crearMatriz(int numFilas, int numColumnas, int numMinas) {

        int[][] matriz = new int[numFilas][numColumnas];
        Random random = new Random();
        int minasColocadas = 0;

        // Paso 1: Colocar minas (-1) en posiciones aleatorias
        while (minasColocadas < numMinas) {
            int fila = random.nextInt(numFilas);
            int columna = random.nextInt(numColumnas);

            // Solo colocar mina si la posición está libre (es decir, si aún no tiene un -1)
            if (matriz[fila][columna] != -1) {
                matriz[fila][columna] = -1;
                minasColocadas++;

                // Paso 2: Incrementar el conteo en celdas adyacentes a la mina
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        int nuevaFila = fila + i;
                        int nuevaColumna = columna + j;

                        // Verificar que la posición está dentro de los límites de la matriz
                        // y que no es una mina
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
