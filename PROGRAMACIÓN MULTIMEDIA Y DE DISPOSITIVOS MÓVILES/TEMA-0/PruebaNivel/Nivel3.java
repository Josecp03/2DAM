package PruebaNivel;

import java.util.Scanner;

public class Nivel3 {

	public static void main(String[] args) {
		
		// Inicializar variables
		Scanner sc = new Scanner(System.in);
		
		// Pedir la nota
		System.out.println("Introduzca la nota:");
		double nota = sc.nextDouble();
		
		// Comprobar la nota
		if (nota > 0 && nota < 3) {
			System.out.println("Muy Deficiente");
		} else if (nota >= 3 && nota < 5) {
			System.out.println("Insuficiente");
		} else if (nota >= 5 && nota < 6) {
			System.out.println("Suficiente");
		} else if (nota >= 6 && nota < 7) {
			System.out.println("Bien");
		} else if (nota >= 7 && nota < 9) {
			System.out.println("Notable");
		} else if (nota >= 9 && nota <= 10) {
			System.out.println("Sobresaliente");
		} else {
			System.out.println("Nota incorrecta");
		}
		
		// Cerrar el Scanner
		sc.close();
	}

}
