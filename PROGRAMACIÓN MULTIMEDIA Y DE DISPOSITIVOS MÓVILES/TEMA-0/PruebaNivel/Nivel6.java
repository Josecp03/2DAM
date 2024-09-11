package PruebaNivel;

import java.util.Scanner;

public class Nivel6 {

	public static void main(String[] args) {
		
		// Inicializar variables
		int n = Integer.MIN_VALUE;
		Scanner sc = new Scanner(System.in);
		int contadorActual = 0;
		int contadorConsecutivos = 0;
		int numeroBuscado = 0;
		int anterior = Integer.MIN_VALUE;
		
		// Estructura de bucle para pedir el número hasta introducir un 0
		while (n != 0) {
			
			// Pedir el número
			System.out.println("Introduzca un número: ");
			n = sc.nextInt();
			if (n != 0) {
				// Si el número es igual al anterior, aumentamos el contador actual
	            if (n == anterior) {
	                contadorActual++;
	                if (contadorActual > contadorConsecutivos) {
	                    contadorConsecutivos = contadorActual;
	                    numeroBuscado = n; // Actualizamos el número más repetido consecutivamente
	                }
	            } else {
	                contadorActual = 1;  // Reiniciamos el contador si el número es diferente
	            }

	            anterior = n;  // Actualizamos el número anterior para la siguiente comparación
			}
			
		}
		
		// Mostramos el resultado por pantalla
		if (numeroBuscado == 0) {
			System.out.println("No hay ningún número que se repita más que otro");
		} else {
			System.out.println("El "+numeroBuscado+" es el número que más se repite");
		}
		
		// Cerrar el scanner
		sc.close();
		
	}

}
