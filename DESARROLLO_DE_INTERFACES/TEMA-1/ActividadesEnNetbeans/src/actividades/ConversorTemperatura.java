package actividades;

import java.util.Scanner;

/**
 * La clase {@code ConversorTemperatura} permite convertir una temperatura dada
 * en grados Celsius a grados Fahrenheit. El usuario introduce la temperatura en
 * Celsius, y el programa realiza la conversión y muestra el resultado en
 * Fahrenheit.
 *
 * La fórmula utilizada para la conversión es:
 * {@code Fahrenheit = (Celsius * 9 / 5) + 32}.
 *
 * @author josec
 */
public class ConversorTemperatura {

    /**
     * Método principal que solicita al usuario una temperatura en grados
     * Celsius, la convierte a grados Fahrenheit y muestra el resultado por
     * pantalla.
     *
     * @param args Los argumentos de la línea de comandos (no utilizados en este
     * programa).
     */
    public static void main(String[] args) {

        // Inicializar el Scanner
        Scanner sc = new Scanner(System.in);

        // Pedir al usuario la temperatura en grados Celsius
        System.out.println("Introduzca la temperatura en grados Celsius: ");
        double temperaturaCelsius = sc.nextDouble();

        // Mostrar por pantalla la temperatura en grados Farenheit
        System.out.println(temperaturaCelsius + "ºC = " + ((temperaturaCelsius * 9 / 5) + 32) + "ºF");

        // Cerrar el Scanner
        sc.close();

    }

}
