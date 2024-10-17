package ejercicioBinario;

public class Binario {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Por favor, proporciona un número decimal como argumento.");
            return;
        }
        
        try {
            int numero = Integer.parseInt(args[0]);
            String binario = Integer.toBinaryString(numero);
            System.out.println("El número en binario es: " + binario);
        } catch (NumberFormatException e) {
            System.out.println("Error: " + args[0] + " no es un número válido.");
        }
    }
}


