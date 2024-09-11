package PruebaNivel;

import java.util.Scanner;

public class Nivel1 {

	public static void main(String[] args) {
		
		// Inicializar variables
		Scanner sc = new Scanner(System.in);
		double resultado1 = 0;
		double resultado2 = 0;
		
		// Pedir las variables
		System.out.println("Introduzca el valor de a: ");
		double a = sc.nextInt();
		System.out.println("Intorduzca el valor de b: ");
		double b = sc.nextInt();
		System.out.println("Introduzca el valor de c");
		double c = sc.nextInt();
		
		// Comprobar el discriminante para valorar distintas soluciones
		double discriminante = (b*b) - (4*a*c);
		
		// Estructura condicional para mostrar las soluciones correctas
		if (discriminante < 0) {
			System.out.println("Solución compleja");
		} else {
			resultado1 = (-b + Math.sqrt(discriminante)) / (2 * a); // Calcular la primera solución
            resultado2 = (-b - Math.sqrt(discriminante)) / (2 * a); // Calcular la segunda solución
            
            // Mostrar las soluciones
			System.out.println("Las soluciones son: ");
			System.out.println("Solución 1: "+resultado1);
			System.out.println("Solución 1: "+resultado2);
		}
		
		// Cerrar el scanner
		sc.close();

	}

}
