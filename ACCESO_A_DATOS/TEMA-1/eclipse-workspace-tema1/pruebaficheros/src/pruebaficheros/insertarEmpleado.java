package pruebaficheros;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class insertarEmpleado {

	public static void main(String[] args) throws IOException {

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

		long posicion = (id - 1) * 36; // calculamos la posicion

		file.seek(posicion); // nos posicionamos
		file.writeInt(id); // se escribe id
		buffer = new StringBuffer(apellido);
		buffer.setLength(10); // 10 caracteres para el apellido
		file.writeChars(buffer.toString());// insertar apellido
		file.writeInt(dep); // insertar departamento
		file.writeDouble(salario);// insertar salario
		
		// Cerrar fichero
		file.close();

	}

}
