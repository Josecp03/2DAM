import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ListarNotas {

	static int LON = 48;

	public static void main(String[] args) throws IOException {

		// Inicializar el objeto File
		File fichero = new File(".\\Notas.dat");

		// Declarar el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "rw");

		// Inicializar variables
		int codAlumno;
		char asignatura[] = new char[20];
		char aux;
		float nota;

		// Establecer la posición a 0 para que empiece desde el principio
		int posicion = 0;

		// Escribir el encabezado
		System.out.printf("%9s %9s %-20s %9s %n", "REGIS", "NUMALUM", "ASIGNATURA", "NOTA");
		System.out.printf("%9s %9s %-20s %9s %n", "-----", "-------", "--------------------","-------");

		// Inicializar un contador
		int contador = 1;
		
		// Recorrer el fichero
		for (;;) {

			// Situarse en la posición inicializada
			file.seek(posicion);
			
			// Obtener el id del alumno
			codAlumno = file.readInt();

			// Obtener el nombre de la asignatura recorriendo uno a uno los caracteres
			for (int i = 0; i < asignatura.length; i++) {
				aux = file.readChar(); // Leer el caracter
				asignatura[i] = aux; // Guardarlo en el array formando el nombre completo
			}

			// Convertir el array en un string
			String asignaturaS = new String(asignatura);

			// Obtener los datos restantes
			nota = file.readFloat();

			System.out.printf("%9s %9s %-20s %9s %n", contador, codAlumno, asignaturaS, nota);

			// Posicionarse en el siguiente alumno
			posicion = posicion + LON;

			// Actualizar el contador
			contador++;
			
			// Salir del for cuando haya recorrido todos los bytes (Y cerrar el file)
			if (file.getFilePointer() == file.length()) {
				file.close();
				break;
			}

		}

	}

}
