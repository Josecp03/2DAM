package tema1;

import java.io.*;

public class pruebasFile3 {

	public static void main(String[] args) {

		// Inicializo los objetos file
		File d = new File("NUEVODIR");
		File f1 = new File(d, "FICHERO1.TXT");
		File f2 = new File(d, "FICHERO2.TXT");

		// Creo el directorio o carpeta
		d.mkdir();

		// Creo los ficheros txt y muestro un mensaje de confirmación
		try {

			// Primer archivo
			if (f1.createNewFile())
				System.out.println("FICHERO1 creado correctamente...");
			else
				System.out.println("No se ha podido crear FICHERO1...");

			// Segundo archivo
			if (f2.createNewFile())
				System.out.println("FICHERO2 creado correctamente...");
			else
				System.out.println("No se ha podido crear FICHERO2...");

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		// Renombro el primer fichero
		f1.renameTo(new File(d, "FICHERO1NUEVO"));

		// Crear un nuevo fichero en el mismo sitio
		try {

			// Inicializar el objeto file
			File f3 = new File("NUEVODIR/FICHERO3.TXT");

			// Crear el archivo y mostrar un mensaje de confirmación
			if (f3.createNewFile()) {
				System.out.println("FICHERO3 creado correctamente...");
			} else {
				System.out.println("No se ga podido crear FICHERO3...");
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		// Guardar el nombre de los archivos
		String[] archivos = d.list();

		// Recorrer todos los ficheros del directorio
		for (int i = 0; i < archivos.length; i++) {

			// Inicializar el archivo a eliminar
			File archivoEliminar = new File(d, archivos[i]);

			// Borrar el archivo y mostrar un mensaje de confirmación
			if (archivoEliminar.delete()) {
				System.out.println(archivoEliminar.getName() + " eliminado correctamente...");
			} else {
				System.out.println("No se ha podido borrar " + archivoEliminar.getName());
			}

		}

		// Una vez se hayan eliminado todos los archivos del directorio borramos el directorio
		if (d.delete()) {
			System.out.println(d.getName() + " borrado correctamente");
		} else {
			System.out.println("No se ha podido borrar " + d.getName());
		}

	}

}
