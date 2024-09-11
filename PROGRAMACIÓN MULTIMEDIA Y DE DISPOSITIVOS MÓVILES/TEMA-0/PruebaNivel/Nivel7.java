package PruebaNivel;

import java.util.Arrays;

public class Nivel7 {

	public static void main(String[] args) {
		
		// Inicializar la Matriz
		int[][] matriz = {
			    {1, 2, 3},
			    {4, 5, 6},
			    {7, 8, 9}
		};

		// Inicializar las variables
		int[] vector = new int[3];
		int sumaFila = 0;
		
		// Recorrer la matriz
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				sumaFila += matriz[i][j]; // Sumar los elementos de la fila
			}
			
			vector[i] = sumaFila; // Añadir al vectro la suma total de la fila
			sumaFila = 0; // Iniciar la suma de la fila en 0 para la siguiente fila
		}
		
		// Mostrar el resultado por pantalla
		System.out.println("Las sumas de las filas de la matriz:");
		System.out.println(Arrays.toString(vector));

	}

}
