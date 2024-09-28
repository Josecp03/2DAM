package parte2;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class borrarRegistro {

	public static void main(String[] args) throws IOException {
		
		// Inicializar el objeto File
		File fichero = new File(".\\AleatorioEmple.dat");

		// Declarar el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "rw");

		// Establecer el id del empleado que vamos a borrar
		int registro = 4;

		// Establecer la posicion
		long posicion = (registro - 1) * 36;
		
		// Posicionarse correctamente
		file.seek(posicion);
		
		// Pongo a 0 el identificador (se considera hueco o borrado)
		file.writeInt(0); 

		// Cerrar el fichero
		file.close();
		
	}
	
}
