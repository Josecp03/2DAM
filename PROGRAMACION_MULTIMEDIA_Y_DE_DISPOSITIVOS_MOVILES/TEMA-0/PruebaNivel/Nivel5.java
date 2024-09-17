package PruebaNivel;

public class Nivel5 {

	public static void main(String[] args) {
		
		// Inicializar el vector
		int[] vector = {9,2,3,4,5};
		int contadorImpares = 0;
		int sumaImpares = 0;
		
		// Recorrer el vector para comprobar sus valores
		for (int i = 0; i < vector.length; i++) {
			
			// Comprobar que el número actual es impar
			if (vector[i] % 2 != 0) {
				contadorImpares++;
				sumaImpares += vector[i];
			}
		}
		
		// Mostrar la media de los números impares del vector
		System.out.println("Media impares: "+(float)sumaImpares/contadorImpares);

	}

}
