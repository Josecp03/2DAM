package ejercicioResumen;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class operacionesFicheros {

	public static void main(String[] args) throws IOException {

		// Inicalizar variables
		Scanner sc = new Scanner(System.in);
		int respuesta = 0;

		// Bucle para el menú
		do {

			// Mostrar el menú por pantalla
			System.out.println("\nSeleccione una opción:");
			System.out.println("1) Crear fichero");
			System.out.println("2) Consultar registro");
			System.out.println("3) Insertar Registro");
			System.out.println("4) Visualizar registro");
			System.out.println("5) Modificar registro");
			System.out.println("6) Borrar registro");
			System.out.println("7) Salir");
			respuesta = sc.nextInt();

			// Comprobar las respuestas
			if (respuesta == 7) {
				System.out.println("Saliendo del programa");
			} else if (respuesta <= 0 || respuesta > 7) {
				System.out.println("Introduzca una opción correcta ");
			}

			// Case para la respuesta
			switch (respuesta) {
			case 1:

				// Establecer el nombre del fichero
				String nombreFichero = "EmpleadosEjemplo";
				crearFichero(nombreFichero);

				break;
			case 2:

				// Establecer el código de departamento del empleado que se va a modificar
				int codigoDepartamento = 0;
				if (consultarRegistro(codigoDepartamento)) {
					System.out.println("DEPARTAMENTO SI EXISTE");
				} else {
					System.out.println("DEPARTAMENTO NO EXISTE");
				}

				break;
			case 3:

				break;
			case 4:

				break;
			case 5:

				break;
			case 6:

				break;
			}

		} while (respuesta != 7);

	}

	// Método para consultar un registro
	private static boolean consultarRegistro(int codigoDepartamento) throws IOException {

		// Establecer el fichero
		File fichero = new File(".\\EmpleadosEjemplo.dat");

		// Comprobar si el fichero existe
		if (!fichero.exists()) {
			System.out.println("El fichero no existe.");
			return false;
		}

		// Declarar el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "r");

		// Inicializar variables
		int codDep, posicion = 0;
		int tamanoRegistro = 42; // 4 (int) + 15*2 (nombre y localidad) + 4 (int) + 4 (float)

		// Recorrer el fichero
		while (file.getFilePointer() < file.length()) {
			
			// Situarse en la posición inicializada
			file.seek(posicion);

			try {
				// Obtener el id del departamento
				codDep = file.readInt();
			} catch (EOFException e) {
				// Si se alcanza el final del archivo inesperadamente
				break;
			}

			// Verificar si el código de departamento coincide
			if (codDep == codigoDepartamento) {
				return true;
			}

			// Posicionarse en el siguiente registro
			posicion += tamanoRegistro;
		}
		
		return false;

	}

	// Método para crear el fichero
	private static void crearFichero(String nombreFichero) throws IOException {

		// Crear el fichero
		File fichero = new File(".\\" + nombreFichero + ".dat");

		// Comprobar si el archivo ya existe
		if (fichero.exists()) {
			System.out.println("El archivo ya existe, no se crea.");
		} else {
			// Intentar crear el archivo si no existe
			if (fichero.createNewFile()) {

				// Declarar el fichero de acceso aleatorio
				RandomAccessFile file = new RandomAccessFile(fichero, "rw");

				// Arrasys con los datos
				int codigoDepartamento[] = { 10, 20, 10, 10, 30 };
				String nombre[] = { "José", "Mario", "Alberto", "Luisa", "Fernando" };
				String localidad[] = { "Talavera", "Toledo", "Valladolid", "Valencia", "Alicante" };
				int numeroEmpleado[] = { 31, 45, 35, 15, 45 };
				float mediaSalario[] = { 1500, 2300, 1750, 3400, 1200 };

				// Buffer para almacenar el apellido
				StringBuffer buffer = null;

				// Número de elementos del array
				int longitud = codigoDepartamento.length;

				// Recorrer los arrays
				for (int i = 0; i < longitud; i++) {

					// Escribo el código de departamento
					file.writeInt(i + 1);

					// Establecer 15 caracteres para el nombre
					buffer = new StringBuffer(nombre[i]);
					buffer.setLength(15);

					// Establecer 15 caracteres para la localidad
					buffer = new StringBuffer(localidad[i]);
					buffer.setLength(15);

					// Insertar los datos restantes
					file.writeChars(buffer.toString());
					file.writeInt(numeroEmpleado[i]);
					file.writeFloat(mediaSalario[i]);

				}

				// Cerrar fichero
				file.close();
				System.out.println("El archivo se ha creado correctamente.");
			} else {
				System.out.println("El archivo no pudo ser creado.");
			}
		}

	}

}