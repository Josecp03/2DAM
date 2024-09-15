package pruebaficheros;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class EscribirFichAleatorio {

	public static void main(String[] args) throws IOException {
		
		File fichero = new File(".\\AleatorioEmple.dat");
		
		// Declarar el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "rw");
		
		// Arrays con los datos
		String apellido[] = {"FERNANDEZ", "GIL", "LOPEZ", "RAMOS", "SEVILLA", "CASILLA", "REY"};
		int dep[] = {10, 20, 10, 10, 30, 30, 20};
		Double salario[] = {1000.45, 2400.60, 3000.0, 1500.56, 2200.0, 1435.87, 2000.0};
		
		// Buffer para almacenar el apellido
		StringBuffer buffer = null;
		
		// Número de elementos del array
		int n = apellido.length;
		
		// Recorrer los arrays
		for (int i = 0; i < n; i++) {
			
			// Escribo el identificador del empleado
			file.writeInt(i+1);
			
			// Establecer 10 caracteres para el apellido
			buffer = new StringBuffer(apellido[i]);
			buffer.setLength(10);
			
			// Insertar los datos restantes
			file.writeChars(buffer.toString());
			file.writeInt(dep[i]);
			file.writeDouble(salario[i]);
			
		}
		
		// Cerrar fichero
		file.close();
		
	}

}
