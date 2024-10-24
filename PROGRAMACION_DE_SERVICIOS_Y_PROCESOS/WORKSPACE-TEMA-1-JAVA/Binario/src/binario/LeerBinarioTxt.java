package binario;

import java.util.Scanner;
import java.io.*;

public class LeerBinarioTxt {

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

                // Pasara a un string el número en binario
                String binario = Integer.toBinaryString(numero);

                // Mostrar por pantalla el resultado que aparecerá en el txt salida
                System.out.println("El numero en binario es: " + binario);

                // Crear la salida.txt
                PrintWriter p = new PrintWriter("salida.txt");
                p.write("El numero en binario es: " + binario);
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
}
