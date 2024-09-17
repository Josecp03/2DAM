package PruebaNivel;

import java.util.Scanner;

public class Nivel4 {

	public static void main(String[] args) {
		
		// Inicializar variables
		double n = Integer.MIN_VALUE;
		Scanner sc = new Scanner(System.in);
		int contadorPares = 0;
		int contadorPositivos = 0;
		
		// Estructura de bucle para pedir el número hasta introducir un 0
		while (n != 0) {
			
			// Pedir el número
			System.out.println("Introduzca un número: ");
			n = sc.nextDouble();
			
			// Comprobar que el número es par
			if (n % 2 == 0 && n!= 0) {
				contadorPares++;
			} 
			
			// Comprobar que el número es positivo
			if (n > 0) {
				contadorPositivos++;
			}
			
		}
		
		// Mostrar los resultados
		System.out.println("Se han introducido "+contadorPares+" pares");
		System.out.println("Se han introducido "+contadorPositivos+" positivos");
		
		// Cerrar el scanner
		sc.close();
	}

}
