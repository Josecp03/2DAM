import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ListarAlumnos {

	static int LON = 92;

	public static void main(String[] args) throws IOException {

		// Inicializar el objeto File
		File fichero = new File(".\\Alumnos.dat");

		// Declarar el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "r");

		// Inicializar variables
		int cod;
		char nombre[] = new char[20];
		char localidad[] = new char[20];
		char aux;
		int numAsignaturas;
		float notaMedia;

		// Establecer la posición a 0 para que empiece desde el principio
		int posicion = 0;

		// Escribir el encabezado
		System.out.printf("%9s %-20s %-20s %9s %10s %n", "NUMALUM", "NOMBRE", "LOCALIDAD", "NUMASIGN", "NOTA MEDIA");
		System.out.printf("%9s %-20s %-20s %9s %10s %n", "-------", "--------------------", "--------------------",
				"--------", "----------");

		// Recorrer el fichero
		for (;;) {

			// Situarse en la posición inicializada
			file.seek(posicion);

			// Obtener el id del alumno
			cod = file.readInt();

			// Obtener el nombre del alumno recorriendo uno a uno los caracteres
			for (int i = 0; i < nombre.length; i++) {
				aux = file.readChar(); // Leer el caracter
				nombre[i] = aux; // Guardarlo en el array formando el nombre completo
			}

			// Convertir el array en un string
			String nombreS = new String(nombre);

			// Obtener la localidad del alumno recorriendo uno a uno los caracteres
			for (int i = 0; i < localidad.length; i++) {
				aux = file.readChar(); // Leer el caracter
				localidad[i] = aux; // Guardarlo en el array formando la localidad completa
			}

			// Convertir el array en un string
			String localidadS = new String(localidad);

			// Obtener los datos restantes
			numAsignaturas = file.readInt();
			notaMedia = file.readFloat();

			// Mostrar los datos del empleado si existe
			if (cod != 0) {
				System.out.printf("%9s %-20s %-20s %9s %10s %n", cod, nombreS, localidadS, numAsignaturas, notaMedia);
			}

			// Posicionarse en el siguiente alumno
			posicion = posicion + LON;

			// Salir del for cuando haya recorrido todos los bytes (Y cerrar el file)
			if (file.getFilePointer() == file.length()) {
				file.close();
				break;
			}

		}

	}

}
