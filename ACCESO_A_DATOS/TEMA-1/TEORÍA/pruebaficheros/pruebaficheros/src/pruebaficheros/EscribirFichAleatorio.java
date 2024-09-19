package pruebaficheros;

import java.io.*;

public class EscribirFichAleatorio {
	public static void main(String[] args) throws IOException {

		// Establecer el nombre del fichero
		File fichero = new File(".\\AleatorioEmple.dat");

		// Declarar el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "rw");

		// Establecer los datos en distintos arrays 
		String apellido[] = { "FERNANDEZ", "GIL", "LOPEZ", "RAMOS", "SEVILLA", "CASILLA", "REY" }; 
		int dep[] = { 10, 20, 10, 10, 30, 30, 20 }; 
		Double salario[] = { 1000.45, 2400.60, 3000.0, 1500.56, 2200.0, 1435.87, 2000.0 };

		// Inicializar el buffer para las variables que sean strings
		StringBuffer buffer = null;

		// Guardar en una variable el tamaño de los arrays
		int n = apellido.length;

		// Recorrer los arrays
		for (int i = 0; i < n; i++) { 

			// Imprimir la posición del puntero actual
			System.out.println("\nPuntero al inicio del registro: " + file.getFilePointer());
			
			// Escribir el identificador del empleado actual
			file.writeInt(i + 1); 

			// Escribir el apellido del empleado actual estableciendo el buffer necesario
			buffer = new StringBuffer(apellido[i]);
			buffer.setLength(10); // Establezco 10 caracteres para el apellido, lo que serían 20 bytes (2 bytes por char)
			file.writeChars(buffer.toString()); // Escribo finalmente el apellido como un conjunto de chars

			// Imprimo la posición del putero una vez escrito el identificador y el apellido dle empleado actual
			System.out.println("Puntero después del apellido: " + file.getFilePointer());

			// Inserto los datos que faltan
			file.writeInt(dep[i]); 
			file.writeDouble(salario[i]);

		}

		// Cerrar el fichero
		file.close(); 

		// Imprimir un mensaje de confirmación una vez se haya cerrado el fichero
		System.out.println("Fichero grabado");
		
	}
}
