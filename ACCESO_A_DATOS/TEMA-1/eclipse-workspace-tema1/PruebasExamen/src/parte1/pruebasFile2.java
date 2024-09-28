package parte1;

import java.io.*;

public class pruebasFile2 {

	public static void main(String[] args) {

		System.out.println("INFORMACIÓN SOBRE EL FICHERO:");

		// Establecer la ruta del archivo.java con el que estamos trabajando
		File f = new File("./src/tema1/pruebasFile2.java");

		// Primero comprobar que exista
		if (f.exists()) {
			System.out.println("Nombre del fichero  : " + f.getName());
			System.out.println("Ruta                : " + f.getPath());
			System.out.println("Ruta absoluta       : " + f.getAbsolutePath());
			System.out.println("Se puede leer       : " + f.canRead());
			System.out.println("Se puede escribir   : " + f.canWrite());
			System.out.println("Tamaño              : " + f.length());
			System.out.println("Es un directorio    : " + f.isDirectory());
			System.out.println("Es un fichero       : " + f.isFile());
			System.out.println("Nombre del directorio padre: " + f.getParent());
		} else {
			System.out.println("No existe el fichero");
		}

	}

}
