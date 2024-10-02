package ejercicioNotas;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Principal {

	// Declarar la longitud de los ficheros
	static int LON_Alumnos = 92;
	static int LON_Notas = 48;

	public static void main(String[] args) throws IOException {

		// Inicalizar variables
		Scanner sc = new Scanner(System.in);
		int opcion = 0;

		// Bucle para el menú
		do {

			// Mostrar el menú por pantalla
			mostrarMenu();

			// Leer respuesta
			opcion = sc.nextInt();

			// Case para la respuesta
			switch (opcion) {
			case 1:
				listarAlumnos();
				break;
			case 2:
				listarNotas();
				break;
			case 3:
				actualizarAlumnos();
				break;
			case 4:

				break;
			case 5:
				System.out.println("FIN DEL MENÚ!");
				break;
			default:
				System.out.println("Seleccione una opción válida");
				break;
			}

		} while (opcion != 5);

		// Cerrar el fichero
		sc.close();

	}

	// Método para actualizar las nota sy el número de asinaturas de cada alumno
	private static void actualizarAlumnos() throws IOException {

		// Inicializar el objeto File
		File fichero = new File(".\\Alumnos.dat");

		// Declarar el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "rw");

		// Inicializar variables
		int numAlumno;
		char nombreAlumno[] = new char[20];
		char localidad[] = new char[20];
		char aux;
		int numAsignaturas;
		float nota;

		// Establecer la posición a 0 para que empiece desde el principio
		int posicion = 0;

		// Recorrer el fichero
		for (;;) {

			// Situarse en la posición inicializada
			file.seek(posicion);

			// Obtener el código del producto
			numAlumno = file.readInt();

			if (numAlumno != 0) {

				// Calcular el número de asignaturas con la llamada al método
				numAsignaturas = calcularNumeroAsignaturas(numAlumno);
				
				// Obtener los datos restantes
				numAsignaturas = file.readInt();
				nota = file.readFloat();

				// Imprimir el registro actual
				System.out.printf("%7s %-20s %-20s %9s %11s %n", numAlumno, nombreS, localidadS, numAsignaturas, nota);

			}

			// Posicionarse en el siguiente alumno
			posicion = posicion + LON_Alumnos;

			// Salir del for cuando haya recorrido todos los bytes (Y cerrar el file)
			if (file.getFilePointer() == file.length()) {
				file.close();
				break;
			}

		}
		
	}

	// Método para calcular el número de asignaturas de un método
	private static int calcularNumeroAsignaturas(int numAlumnoBuscado) throws IOException {
		
		// Inicializar el objeto File
		File fichero = new File(".\\Notas.dat");

		// Declarar el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "r");

		// Inicializar variables
		int numAlumnoActual;
		char nombreAsignatura[] = new char[20];
		char aux;
		float nota;

		// Establecer la posición a 0 para que empiece desde el principio
		int posicion = 0;

		// Inicializar el contador de asignaturas
		int contadorAsignaturas = 0;
		
		// Recorrer el fichero
		for (;;) {

			// Situarse en la posición inicializada
			file.seek(posicion);

			// Obtener el código del producto
			numAlumnoActual = file.readInt();

			// Comprobar que sea el alumno buscado
			if (numAlumnoActual == numAlumnoBuscado) {
				
				// Aumentar el contador
				contadorAsignaturas++;
				
			}

			// Convertir el array en un string
			String nombreAsignaturaS = new String(nombreAsignatura);

			// Obtener la nota
			nota = file.readFloat();

			// Posicionarse en el siguiente alumno
			posicion = posicion + LON_Notas;

			// Salir del for cuando haya recorrido todos los bytes (Y cerrar el file)
			if (file.getFilePointer() == file.length()) {
				file.close();
				break;
			}

		}
		
		
		return 0;
	}

	// Método para listar las notas
	private static void listarNotas() throws IOException {

		// Inicializar el objeto File
		File fichero = new File(".\\Notas.dat");

		// Declarar el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "r");

		// Inicializar variables
		int numAlumno;
		char nombreAsignatura[] = new char[20];
		char aux;
		float nota;

		// Establecer la posición a 0 para que empiece desde el principio
		int posicion = 0;

		// Escribir el encabezado
		System.out.printf("%7s %7s %-20s %7s %n", "REGIS", "NUMALUM", "ASIGNATURA", "NOTA");
		System.out.printf("%7s %7s %-20s %7s %n", "-----", "-------", "--------------------", "----");

		// Inicializar el contador
		int contador = 1;

		// Recorrer el fichero
		for (;;) {

			// Situarse en la posición inicializada
			file.seek(posicion);

			// Obtener el código del producto
			numAlumno = file.readInt();

			// Obtener el nombre del alumno recorriendo uno a uno los caracteres
			for (int i = 0; i < nombreAsignatura.length; i++) {
				aux = file.readChar(); // Leer el caracter
				nombreAsignatura[i] = aux; // Guardarlo en el array formando el nombre completo
			}

			// Convertir el array en un string
			String nombreAsignaturaS = new String(nombreAsignatura);

			// Obtener la nota
			nota = file.readFloat();

			// Imprimir el registro actual
			System.out.printf("%7s %7s %-20s %7s %n", contador, numAlumno, nombreAsignaturaS, nota);

			// Posicionarse en el siguiente alumno
			posicion = posicion + LON_Notas;

			// Actualizar el contador
			contador++;

			// Salir del for cuando haya recorrido todos los bytes (Y cerrar el file)
			if (file.getFilePointer() == file.length()) {
				file.close();
				break;
			}

		}

	}

	// Método para listar los alumnos
	private static void listarAlumnos() throws IOException {

		// Inicializar el objeto File
		File fichero = new File(".\\Alumnos.dat");

		// Declarar el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "r");

		// Inicializar variables
		int numAlumno;
		char nombreAlumno[] = new char[20];
		char localidad[] = new char[20];
		char aux;
		int numAsignaturas;
		float nota;

		// Establecer la posición a 0 para que empiece desde el principio
		int posicion = 0;

		// Escribir el encabezado
		System.out.printf("%7s %-20s %-20s %9s %11s %n", "NUMALUM", "NOMBRE", "LOCALIDAD", "NUMASIG", "NOTA MEDIA");
		System.out.printf("%7s %-20s %-20s %9s %11s %n", "-------", "--------------------", "--------------------",
				"-------", "-----------");

		// Recorrer el fichero
		for (;;) {

			// Situarse en la posición inicializada
			file.seek(posicion);

			// Obtener el código del producto
			numAlumno = file.readInt();

			if (numAlumno != 0) {

				// Obtener el nombre del alumno recorriendo uno a uno los caracteres
				for (int i = 0; i < nombreAlumno.length; i++) {
					aux = file.readChar(); // Leer el caracter
					nombreAlumno[i] = aux; // Guardarlo en el array formando el nombre completo
				}

				// Convertir el array en un string
				String nombreS = new String(nombreAlumno);

				// Obtener el nombre de la localidad recorriendo uno a uno los caracteres
				for (int i = 0; i < localidad.length; i++) {
					aux = file.readChar(); // Leer el caracter
					localidad[i] = aux; // Guardarlo en el array formando el nombre completo
				}

				// Convertir el array en un string
				String localidadS = new String(localidad);

				// Obtener los datos restantes
				numAsignaturas = file.readInt();
				nota = file.readFloat();

				// Imprimir el registro actual
				System.out.printf("%7s %-20s %-20s %9s %11s %n", numAlumno, nombreS, localidadS, numAsignaturas, nota);

			}

			// Posicionarse en el siguiente alumno
			posicion = posicion + LON_Alumnos;

			// Salir del for cuando haya recorrido todos los bytes (Y cerrar el file)
			if (file.getFilePointer() == file.length()) {
				file.close();
				break;
			}

		}

	}

	// Método para mostrar el menú
	private static void mostrarMenu() {
		System.out.println("-------------------------------------------------");
		System.out.println(" 1) Listar Alumnos");
		System.out.println(" 2) Listar Notas");
		System.out.println(" 3) Actualizar el fichero Alumnos");
		System.out.println(" 4) Generar el fichero Alumnos.xml");
		System.out.println(" 5) Salir");
		System.out.println("-------------------------------------------------");

	}

}
