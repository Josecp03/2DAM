package parte2;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class insertarAleatoriamente {

	public static void main(String[] args) throws IOException {
		
		// Inicializar el objeto File
		File fichero = new File(".\\AleatorioEmple.dat");

		// Declarar el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "rw");

		// Buffer para almacenar el apellido
		StringBuffer buffer = null;

		// Establecer los datos que se van a escribir
		String apellido = "GONZALEZ"; 
		Double salario = 1230.87; 
		int id = 20; 
		int dep = 10; 

		// Calcular la posición (Se le resta uno porque empieza por 0)
		long posicion = (id - 1) * 36; 

		// Posicionarse correctamente
		file.seek(posicion); 
		
		// Escribir los datos
		file.writeInt(id); 
		buffer = new StringBuffer(apellido);
		buffer.setLength(10); 
		file.writeChars(buffer.toString());
		file.writeInt(dep); 
		file.writeDouble(salario);
		
		// Cerrar fichero
		file.close();
		
	}
	
}
