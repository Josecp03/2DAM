package factorial;

public class CalcularFactorialArgs {

	public static void main(String[] args) {
		
		// Comprobar que se pasan correctamente los argumentos
		if (args.length < 1) {
			System.out.println("SE NECESITA UN NUMERO");
			System.exit(1);
		}
		
		// Inicializar el número
		int numero = Integer.parseInt(args[0]);
				
		int factorial = calcularFactorial(numero);
		
        
        // Mostrar por pantalla el número en binario
        System.out.println("El factorial de " + numero + " es " + factorial);
		
	}
	
    public static int calcularFactorial(int n) {
        
    	if (n < 0) {
            throw new IllegalArgumentException("El número debe ser no negativo.");
        }

        int factorial = 1; 
        
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        
        return factorial;
        
    }
	
	
}
