package pruebaficheros;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class crearUTF {

	public static void main(String[] args) throws IOException {

		File fichero = new File(".\\AleatorioEmpleUTF.dat");
		
		// Declarar el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "rw");
		
		// Inicializar variables
		String apellido[] = { "FERNANDEZ", "GIL", "LOPEZ", "RAMOS", "SEVILLA", "CASILLA", "REY" };
		int dep[] = { 10, 20, 10, 10, 30, 30, 20 };
		Double salario[] = { 1000.45, 2400.60, 3000.0, 1500.56, 2200.0, 1435.87, 2000.0 };

		// Establecer el número de elmentos del array
		int n = apellido.length;
		
		// Establecer la posición inicial
		int posicion = 0;
		
		// Recorrer los arrays y escribir los datos
		for (int i = 0; i < n; i++) {
			file.seek(posicion);
			file.writeInt(i + 1);
			file.writeUTF(apellido[i]);
			file.writeInt(dep[i]);
			file.writeDouble(salario[i]);
			posicion = posicion + 36;
		}
		
		// Cerrar fichero
		file.close();

	}

}
