import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ActualizarAlumnos {

	static int LON_Alumnos = 92;
	static int LON_Notas = 48;
	
	public static void main(String[] args) throws IOException {
		
		// Inicializar los objetos File
		File ficheroAlumnos = new File(".\\Alumnos.dat");
		File ficheroNotas = new File(".\\Notas.dat");
		
		// Declarar el fichero de acceso aleatorio
		RandomAccessFile fileAlumnos = new RandomAccessFile(ficheroAlumnos, "rw");
		RandomAccessFile fileNotas = new RandomAccessFile(ficheroNotas, "r");
				
		// Inicializar la posicion
		int posicion = 0;
		
		// Recorrer el fichero
		for (;;) {
			
			// Posicionarnos correctamente
			fileNotas.seek(posicion);
			
			// Salir del for cuando haya recorrido todos los bytes (Y cerrar los files)
			if (fileNotas.getFilePointer() == fileNotas.length()) {
				fileNotas.close();
				fileAlumnos.close();
				break;
			}
			
		}
				
		
	}
	
}
