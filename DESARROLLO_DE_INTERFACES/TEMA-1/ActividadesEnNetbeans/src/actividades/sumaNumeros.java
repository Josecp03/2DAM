package actividades;

import java.util.Scanner;

/**
 * La clase {@code sumaNumeros} permite al usuario ingresar dos números enteros 
 * y calcular su suma. El resultado se muestra en la consola.
 * 
 * El programa solicita al usuario los dos números y luego imprime la operación 
 * de suma con su resultado correspondiente.
 * 
 * @author josec
 */
public class sumaNumeros {

    /**
     * Método principal que gestiona la entrada de los números por parte del usuario,
     * calcula la suma de estos números y muestra el resultado en la consola.
     * 
     * El programa sigue los siguientes pasos:
     * 1. Solicita el primer número.
     * 2. Solicita el segundo número.
     * 3. Calcula y muestra el resultado de la suma.
     * 
     * @param args Los argumentos de la línea de comandos (no utilizados en este programa).
     */
    public static void main(String[] args) {

        // Inicializar el Scanner
        Scanner sc = new Scanner(System.in);

        // Pedir el primer número al usuario
        System.out.println("Introduzca el primer número: ");
        int numero1 = sc.nextInt();

        // Pedir el primer segundo al usuario
        System.out.println("Introduzca el segundo número: ");
        int numero2 = sc.nextInt();

        // Imprimir el resultado por pantalla
        System.out.println(numero1 + " + " + numero2 + " = " + (numero1 + numero2));

        // Cerrar el Scanner
        sc.close();
        
    }

}
