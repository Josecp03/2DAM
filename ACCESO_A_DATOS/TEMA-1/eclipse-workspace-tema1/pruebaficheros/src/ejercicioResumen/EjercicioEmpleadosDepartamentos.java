package ejercicioResumen;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class EjercicioEmpleadosDepartamentos {

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
				int codigoDepartamento2 = 6;
				String nombre = "Pepe";
				String localidad = "Talavera";
				int numeroEmpleados = 33;
				float mediaSalario = 1800;
				insertarRegistro(codigoDepartamento2, nombre, localidad, numeroEmpleados, mediaSalario);
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

	private static void crearFichero(String nombreFichero) throws IOException {
		// Establecer el nombre del fichero
		File fichero = new File(".\\AleatorioEmple.dat");

		// Declarar el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "rw");

		// Establecer los datos en distintos arrays
		// Arrasys con los datos
		int codigoDepartamento[] = { 10, 20, 10, 10, 30 };
		String nombre[] = { "José", "Mario", "Alberto", "Luisa", "Fernando" };
		String localidad[] = { "Talavera", "Toledo", "Valladolid", "Valencia", "Alicante" };
		int numeroEmpleado[] = { 31, 45, 35, 15, 45 };
		float mediaSalario[] = { 1500, 2300, 1750, 3400, 1200 };

		// Inicializar el buffer para las variables que sean strings
		StringBuffer buffer = null;

		// Guardar en una variable el tamaño de los arrays
		int longitud = codigoDepartamento.length;

		// Recorrer los arrays
		for (int i = 0; i < longitud; i++) {

			// Escribir el identificador del empleado actual
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

		// Cerrar el fichero
		file.close();

		// Imprimir un mensaje de confirmación una vez se haya cerrado el fichero
		System.out.println("Fichero grabado");

	}

}