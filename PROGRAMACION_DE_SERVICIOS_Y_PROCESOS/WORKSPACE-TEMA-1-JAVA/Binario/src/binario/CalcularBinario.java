package binario;

import java.util.Scanner;

public class CalcularBinario {

	public static void main(String[] args) {
		
		// Inincializar el scanner
		Scanner sc = new Scanner(System.in);
		
		// Inicializar el número
		int numero = 0;
		
		// Pedir el número hasta que se intriduzca un número positivo
		do {
			System.out.println("Introduzca un numero positivo: ");
			numero = sc.nextInt();
		} while (numero < 0);
		
		// Inicializar la cadena
        String binario = "";
        
        // Comprobar si es 0 el número
        if (numero == 0) {
            binario = "0";
        } else {
        	
        	// Calcular el dígito
            while (numero > 0) {
                int residuo = numero % 2;
                binario = residuo + binario;
                numero = numero / 2;
            }
        }
        
        // Mostrar por pantalla el número en binario
        System.out.println("El numero en binario es: " + binario);
        
        // Cerrar el scanner
        sc.close();
		
	}

	
	
}
