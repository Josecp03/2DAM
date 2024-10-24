package binario;

public class CalcularBinarioArgs {

	public static void main(String[] args) {
		
		// Comprobar que se pasan correctamente los argumentos
		if (args.length < 1) {
			System.out.println("SE NECESITA UN NUMERO");
			System.exit(1);
		}
		
		// Inicializar el número
		int numero = Integer.parseInt(args[0]);
				
		// Inicializar la cadena
        String binario = "";
        
        // Comprobar si es 0 el número
        if (numero == 0) {
            binario = "0";
        } else {
        	
        	// Calcular el dígito
            while (numero > 0) {
                int residuo = numero % 2;
                binario = residuo + binario;
                numero = numero / 2;
            }
        }
        
        // Mostrar por pantalla el número en binario
        System.out.println("El numero en binario es: " + binario);
        		
	}
	
}
