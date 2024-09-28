package actividades;

import java.util.Scanner;

/**
 * La clase {@code Factorial} permite calcular el factorial de un número entero
 * positivo introducido por el usuario. El factorial de un número n se define
 * como el producto de todos los números enteros positivos desde 1 hasta n.
 *
 * Si el usuario introduce un número negativo, se le volverá a pedir hasta que
 * introduzca un número válido.
 *
 * @author josec
 */
public class Factorial {

    /**
     * Método principal que solicita al usuario un número entero positivo,
     * calcula su factorial y muestra el resultado por pantalla.
     *
     * El factorial de un número n se calcula multiplicando todos los números
     * enteros positivos menores o iguales a n. Por ejemplo, el factorial de 5
     * (5!) es 5 * 4 * 3 * 2 * 1.
     *
     * @param args Los argumentos de la línea de comandos (no utilizados en este
     * programa).
     */
    public static void main(String[] args) {

        // Inicializar el Scanner
        Scanner sc = new Scanner(System.in);

        // Inicializar la variable n
        int n = 0;

        // Pedir al usuario un número positivo
        do {
            
            // Pedir al usuario el número actual 
            System.out.println("Introduzca un número positivo: ");
            n = sc.nextInt();

        } while (n < 0);

        // Inicializar variables
        int aux = 1;
        int resultado = 1;

        // Calcular el factorial
        while (aux <= n) {
            resultado *= aux;
            aux++;
        }

        // Imprimir el resultado
        System.out.println(n + "! = " + resultado);
    }

}
