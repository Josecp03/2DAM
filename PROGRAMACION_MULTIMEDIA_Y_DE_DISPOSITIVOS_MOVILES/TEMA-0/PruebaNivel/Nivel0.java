package PruebaNivel;

import java.util.Scanner;

public class Nivel0 {

	public static void main(String[] args) {
		
		// Inicializar el Scanner
		Scanner sc = new Scanner(System.in);
		
		// Inicializar las variables
		double n = Integer.MIN_VALUE;
		
		// Pedir las millas
		while (n < 0) {
			System.out.println("Introduzca el número de millas marinas: ");
			n = sc.nextDouble();
		}
		
		// Mostrar por pantalla los km correspondientes
		System.out.println(n+" millas son "+(n*1852)/1000+" km");
		
		// Cerrar el Scanner
		sc.close();
		
	}

}
