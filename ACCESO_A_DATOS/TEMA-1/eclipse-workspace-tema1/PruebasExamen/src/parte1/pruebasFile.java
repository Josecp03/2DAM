package parte1;

import java.io.*;

public class pruebasFile {
	public static void main(String[] args) {
		
		// Inicializar el fichero en el directorio actual
		String dir = "."; // El "." indica el directorio actual
		File f = new File(dir);
		
		// Guardar los archivos en un array
		String[] archivos = f.list();
		
		// Imprimir el número de archivos que hay
		System.out.printf("Ficheros en el directorio actual: %d %n", archivos.length);
		
		// Recorrer todos los archivos del directorio actual
		for (int i = 0; i < archivos.length; i++) {
			
			// Creo un nuevo objeto file para almacenar el archivo actual dentro del directorio que ya hemo sinicializado antes
			File f2 = new File(f, archivos[i]);
			
			// Imprimir por pantalla si el archivo actual es archivo o directorio
			System.out.printf("Nombre: %s, es fichero?: %b, es directorio?: %b %n", archivos[i], f2.isFile(), f2.isDirectory());
			
		}
	}
}
