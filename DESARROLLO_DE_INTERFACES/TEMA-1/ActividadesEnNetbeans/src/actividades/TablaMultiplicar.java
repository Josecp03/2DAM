package actividades;

import java.util.Scanner;

/**
 * La clase {@code TablaMultiplicar} genera y muestra la tabla de multiplicar de
 * un número entero entre 1 y 10 introducido por el usuario.
 *
 * El programa valida que el número introducido esté en el rango correcto (1 a
 * 10) antes de mostrar la tabla de multiplicar.
 *
 * @author josec
 */
public class TablaMultiplicar {

    /**
     * Método principal que solicita al usuario un número entero entre 1 y 10, y
     * luego imprime la tabla de multiplicar correspondiente de dicho número.
     *
     * Si el número introducido no está en el rango permitido (1 a 10), el
     * programa seguirá solicitando un número válido.
     *
     * @param args Los argumentos de la línea de comandos (no utilizados en este
     * programa).
     */
    public static void main(String[] args) {

        // Inicializar el Scanner
        Scanner sc = new Scanner(System.in);

        // Inicializar la variable n
        int n = 0;

        // Pedir al usuario un número del 1 al 10
        do {

            System.out.println("Introduzca un número del 1 al 10: ");
            n = sc.nextInt();

        } while (n < 0 || n > 10);

        // Mostrar la tabla de multiplicar del número dado
        for (int i = 1; i <= 10; i++) {
            System.out.println(n + " x " + i + " = " + n * i);
        }

        // Cerrar el Scanner
        sc.close();

    }

}
