package actividades;

/**
 * La clase {@code imprimirRangoNumeros} imprime los números del 1 al 10 en una
 * sola línea, separados por comas. El formato asegura que no haya una coma al
 * final del último número.
 *
 * El programa recorre un bucle del 1 al 10, y se encarga de imprimir
 * correctamente los números con una coma entre ellos, excepto después del
 * último número.
 *
 * @author josec
 */
public class imprimirRangoNumeros {

    /**
     * Método principal que imprime los números del 1 al 10 en una sola línea,
     * separados por comas.
     *
     * El bucle itera del 1 al 10, y la condición dentro del bucle se encarga de
     * que no se imprima una coma después del último número (10).
     *
     * @param args Los argumentos de la línea de comandos (no utilizados en este
     * programa).
     */
    public static void main(String[] args) {

        // Recorrer un bucle for del 1 al 10
        for (int i = 1; i <= 10; i++) {

            // No imprimir la "," al final
            if (i == 10) {
                System.out.println(i);
            } else {
                System.out.print(i + ", ");
            }

        }

    }

}
