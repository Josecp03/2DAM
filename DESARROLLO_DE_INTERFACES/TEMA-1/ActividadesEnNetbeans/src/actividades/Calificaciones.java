package actividades;

import java.util.Arrays;
import java.util.Scanner;

/**
 * La clase {@code Calificaciones} permite al usuario ingresar 5 calificaciones,
 * almacenarlas en un array, calcular el promedio y mostrar tanto las
 * calificaciones ingresadas como su promedio.
 *
 * El programa solicita al usuario que introduzca 5 notas, las almacena en un
 * array y luego calcula la suma total para obtener el promedio. Finalmente, se
 * muestran las calificaciones y el promedio.
 *
 * @author josec
 */
public class Calificaciones {

    /**
     * Método principal que gestiona la interacción con el usuario para ingresar
     * 5 calificaciones, almacenarlas, calcular la media y mostrar los
     * resultados.
     *
     * @param args Los argumentos de la línea de comandos (no utilizados en este
     * programa).
     */
    public static void main(String[] args) {

        // Inicializar el Scanner
        Scanner sc = new Scanner(System.in);

        // Inicializar el array
        int[] calificaciones = new int[5];

        // Inicializar la suma de calificaciones
        int sumaCalificaciones = 0;

        // Ir gestionando la nota actual con un for
        for (int i = 0; i < 5; i++) {

            // Pedir al usuario la nota
            System.out.println("Introduzca la nota " + (i + 1) + ": ");
            int calificacion = sc.nextInt();

            // Añadir la calificacion al array
            calificaciones[i] = calificacion;

            // Ir sumando las calificaciones
            sumaCalificaciones += calificacion;

        }

        // Imprimir por pantalla los resultados
        System.out.println("Las notas son las siguientes: " + Arrays.toString(calificaciones));
        System.out.println("El promedio de estas calificaciones es de " + sumaCalificaciones / 5);

    }

}
