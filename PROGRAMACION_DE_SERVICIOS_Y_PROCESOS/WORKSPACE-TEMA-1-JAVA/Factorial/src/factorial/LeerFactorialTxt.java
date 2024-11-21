package factorial;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class LeerFactorialTxt {

	public static void main(String[] args) {
		
		
    	// Inicializar el txt con el número 
    	File f = new File("numero.txt");

        try {
        	
        	// Inicializar la entrada
            Scanner entrada = new Scanner(new FileInputStream(f));

            // Comprobar que el txt contenga un número
            if (entrada.hasNextInt()) {
            	
            	// Guardar en una variable el número leído del txt
                int numero = entrada.nextInt();

                int factorial = calcularFactorial(numero);

                // Mostrar por pantalla el resultado que aparecerá en el txt salida
                System.out.println("El numero en binario es: " + factorial);

                // Crear la salida.txt
                PrintWriter p = new PrintWriter("salida.txt");
                p.write("El factorial de " + numero + " es " + factorial);
                p.flush();
                p.close();

            } else {
                
            	// Crear el error.txt
                PrintWriter error = new PrintWriter("error.txt");
                error.write("Error: No se ha introducido un número en el archivo.");
                error.flush();
                error.close();
                
                // Mostrar por pantalla el error que aparece en error.txt
                System.out.println("Error: No se ha introducido un número.");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		
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
