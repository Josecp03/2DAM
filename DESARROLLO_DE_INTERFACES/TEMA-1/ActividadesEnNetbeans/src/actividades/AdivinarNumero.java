package actividades;

import java.util.Scanner;

/**
 * Esta clase implementa un juego en el que el usuario debe adivinar un número
 * aleatorio entre 1 y 100. El usuario tiene un máximo de 5 intentos para
 * adivinar el número correcto.
 *
 * El programa proporciona pistas indicando si el número que debe adivinar es
 * mayor o menor al introducido por el usuario.
 *
 * @author josec
 */
public class AdivinarNumero {

    /**
     * Método principal que ejecuta el juego de adivinar el número. Se genera un
     * número aleatorio entre 1 y 100 y se le da al usuario un máximo de 5
     * intentos para adivinarlo. Si el usuario no adivina el número en 5
     * intentos, el juego termina.
     *
     * @param args Los argumentos de la línea de comandos (no utilizados en este
     * programa).
     */
    public static void main(String[] args) {

        // Inicializar el Scanner
        Scanner sc = new Scanner(System.in);

        // Establecer el mínimo y máximo del número aleatorio
        int min = 1;
        int max = 100;

        // Inicializar el valor del número aleatorio
        int numeroAleatorio = (int) (Math.random() * (max - min + 1)) + min;

        System.out.println(numeroAleatorio);

        // Inicializar el número pedido al usuario y el contador de aciertos
        int numeroIntroducido = 0;
        int contadorIntentos = 1;

        // Avisar al usuario de que tiene un límite de 5 intentos
        System.out.println("Tienes 5 intentos para adivinar el número");

        // Bucle para ir pidiendo al usario el número hasta que acierte o se le acaben los intentos
        do {

            // Pedir al usuario que introduzca el número a adivinar
            System.out.println("Introduzca el número: ");
            numeroIntroducido = sc.nextInt();

            // Si el número es incorrecto darle una pista y avisarle de los intentos que le quedan
            if (numeroAleatorio > numeroIntroducido) {

                // Imprimir por pantalla la pista y el número de intentos que le quedan
                System.out.println("El número que tienes que adivinar es mayor");
                System.out.println("Te quedan " + (5 - contadorIntentos) + " intentos");

                // Incrementar el contador
                contadorIntentos++;

            } else if (numeroAleatorio < numeroIntroducido) {

                // Imprimir por pantalla la pista y el número de intentos que le quedan
                System.out.println("El número que tienes que adivinar es menor");
                System.out.println("Te quedan " + (5 - contadorIntentos) + " intentos");

                // Incrementar el contador
                contadorIntentos++;

            } else {
                System.out.println("Has ganado en " + contadorIntentos + " intentos, enhorabuena!");
            }

        } while (numeroIntroducido != numeroAleatorio && contadorIntentos < 6);

        // Cerrar el scanner
        sc.close();

    }

}
