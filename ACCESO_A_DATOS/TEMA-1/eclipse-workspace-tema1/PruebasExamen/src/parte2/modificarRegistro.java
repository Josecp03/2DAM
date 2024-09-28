package parte2;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class modificarRegistro {

	public static void main(String[] args) throws IOException {
		
		// Iicializar el objeto File
		File fichero = new File(".\\AleatorioEmple.dat");

		// Declarar el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "rw");

		// Establecer el id del empleado que vamos a modificar
		int registro = 4;

		// Calular la posición
		long posicion = (registro - 1) * 36; 
		
		// Vamos a modificar solo el departamento y salario, por que nos tenemos que saltar los otros datos
		
		// sumo el tamaño de ID+apellido
		posicion += 4 + 20; 
		
		// Posicionarse correctamente
		file.seek(posicion); 
		
		// Modificar los datos
		file.writeInt(40); 
		file.writeDouble(4000.87);

		// Cerrar fichero
		file.close();
		
	}
	
}
