package actividades;

import java.util.Scanner;

/**
 * La clase {@code NumeroVocales} permite contar el número de vocales en una
 * palabra proporcionada por el usuario. Se utiliza un bucle para recorrer cada
 * letra de la palabra y un contador para registrar cuántas vocales contiene.
 *
 * <p>
 * Vocales consideradas: 'a', 'e', 'i', 'o', 'u' (solo en minúsculas, ya que la
 * palabra se convierte a minúsculas).</p>
 *
 * @author josec
 */
public class NumeroVocales {

    /**
     * Método principal que solicita al usuario una palabra, cuenta el número de
     * vocales en esa palabra, y muestra el resultado en la consola.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {

        // Inicializar el Scanner
        Scanner sc = new Scanner(System.in);

        // Pedir al usuario una palabra
        System.out.println("Introduzca una palabra: ");
        String palabra = sc.next().toLowerCase();

        // Inicializar un contador
        int contadorVocales = 0;

        // Recorrer todas las letras de la palabra
        for (int i = 0; i < palabra.length(); i++) {

            // Guardar en una variable la letra actual de la palabra
            char letra = palabra.charAt(i);

            // Comprobar si es una vocal
            if (letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o' || letra == 'u') {
                contadorVocales++; // Incrementar el contador si es una vocal
            }

        }

        // Imprimir por pantalla el número de vocales que tiene la palabra
        System.out.println(palabra + " tiene " + contadorVocales + " vocales");

    }

}
