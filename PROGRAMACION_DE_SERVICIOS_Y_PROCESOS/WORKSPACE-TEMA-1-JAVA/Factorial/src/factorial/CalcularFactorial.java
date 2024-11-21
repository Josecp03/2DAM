package factorial;
import java.util.Scanner;

public class CalcularFactorial {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// Inicializar el número
		int numero = 0;
		
		// Pedir el número hasta que se intriduzca un número positivo
		do {
			System.out.println("Introduzca un numero positivo: ");
			numero = sc.nextInt();
		} while (numero < 0);

		int factorial = calcularFactorial(numero);
        
        // Mostrar por pantalla el número en binario
        System.out.println("El factorial de " + numero + " es " + factorial);
        
        // Cerrar el scanner
        sc.close();
		
	}
	
	
    public static int calcularFactorial(int n) {
        
    	if (n < 0) {
            throw new IllegalArgumentException("El número debe ser no negativo.");
        }

        int factorial = 1; 
        
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        
        return factorial;
        
    }
	
}
