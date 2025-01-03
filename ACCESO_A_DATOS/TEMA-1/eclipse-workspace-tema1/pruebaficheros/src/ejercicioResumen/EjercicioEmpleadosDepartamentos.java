package ejercicioResumen;

import java.io.File;
import java.io.FileNotFoundException;
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
				String nombreFichero = "AleatorioEmple";
				crearFichero(nombreFichero);
				break;
				
			case 2:

				// Establecer el código de departamento del empleado que se va a modificar
				int codigoDepartamento = 2;
				if (consultarRegistro(codigoDepartamento)) {
					System.out.println("DEPARTAMENTO SI EXISTE");
				} else {
					System.out.println("DEPARTAMENTO NO EXISTE");
				}
				break;
				
			case 3:

				// Insertar un empleado con un departamento correcto
				int codigoDepartamento2 = 7;
				String nombre = "Pepe";
				String localidad = "Talavera";
				int numeroEmpleados = 33;
				float mediaSalario = 1800;
				System.out.println(insertarRegistro(codigoDepartamento2, nombre, localidad, numeroEmpleados, mediaSalario));
				break;
				
			case 4:
				
				int codigoDepartamento3 = 2;
				
				if (!visualizarRegistro(codigoDepartamento3)) {
					System.out.println("DEPARTAMENTO NO EXISTE NO SE VISUALIZA");
				}
				
				
				break;
			case 5:

				break;
			case 6:

				break;
			}

		} while (respuesta != 7);

	}

	private static boolean visualizarRegistro(int codDep) throws IOException {
		
		// Inicializar una variable booleana
		boolean existe = false;
		
		if (consultarRegistro(codDep)) {
			
			// Establecer el nombre del fichero con el que vamos a trabajar
			File fichero = new File(".\\AleatorioEmple.dat");

			// eclarar el fichero de acceso aleatorio
			RandomAccessFile file = new RandomAccessFile(fichero, "r");
			
			// Inicalizar las variables para guardar los datos que vamos a ir leyendo
			int codigoDep, numEmple, posicion;
			Float mediaSal;
			char nombre[] = new char[15], aux; 
			char localidad[] = new char[15];
			
			// Inicializamos la posición inicial a 0
			posicion = 0; 

			// Recorro el fichero
			for (;;) { 
				
				// Establezco la posición del puntero
				file.seek(posicion); 

				// Guardo el identificador del empleado actual
				codigoDep = file.readInt(); 

				// Visualizar el empleado con el identificador que pasamos
				if (codigoDep == codDep) {
					
					// Recorro todos los chars que contiene el nombre
					for (int i = 0; i < nombre.length; i++) {

						// Voy guardando en una variable el cahr actual del nombre que estoy leyendo
						aux = file.readChar();

						// Guardo en el array que contiene el nombre completo el char atual del nombre guardado en la variable aux
						nombre[i] = aux; 

					}

					// Creo una variable String para guardar el nombre que anteriormente era un array
					String nombreS = new String(nombre);

					// Recorro todos los chars que contiene la localidad
					for (int i = 0; i < localidad.length; i++) {

						// Voy guardando en una variable el cahr actual de la localidad que estoy leyendo
						aux = file.readChar();

						// Guardo en el array que contiene la localidad completa el char atual de la localidad guardada en la variable aux
						localidad[i] = aux; 

					}

					// Creo una variable String para guardar el apellido que anteriormente era un array
					String localidadS = new String(localidad);
					
					// Guardo los datos restantes en las variables inicializadas anteriormente
					numEmple = file.readInt();
					mediaSal = file.readFloat(); 

					// Imprimo por pantalla todos los datos del empleado actual
					System.out.println("DEPARTAMENTO: " + codigoDep + ", Nombre: " + nombreS + ", Localidad: " + localidadS + ", Empleados: " + numEmple + ", Media Salario: " + mediaSal);
					
					// Actualizo la variable posición para posicionarme en el siguiente empleado. Cada empleado ocupa 72 bytes
					posicion = posicion + 72; 
												
					// Cuando recorra todos los bytes del for me salgo del bucle
					if (posicion >= file.length() || file.getFilePointer() == file.length()) {
						break;
					}
				}
				

					
			}

			// Cerrar el fichero
			file.close(); 
			
			existe = true;
			
		} else {
			existe = false;
		}
		
		return existe;
		
	}

	private static String insertarRegistro(int codDep, String nombre, String localidad, int numeroEmpleados, float mediaSalario) throws IOException {

		// Comprobar que el código de departamento esté entre 1 y 100
		if (codDep < 1 || codDep > 100) {
			return "ERROR EL DEPARTAMENTO DEBE ESTAR ENTRE 1 Y 100";
		} else {

			if (consultarRegistro(codDep)) {
				return "ERROR EL DEPARTAMENTO YA EXISTE NO SE PUEDE INSERTAR";
			} else {
				
				// Establecer el nombre del fichero con el que vamos a trabajar
				File fiche = new File(".\\AleatorioEmple.dat");

				try {
					
					// Declarar el fichero de acceso aleatorio
					RandomAccessFile file = new RandomAccessFile(fiche, "rw");

					// Establecer la posición sabiendo el identificador
					long posicion = (codDep - 1) * 72;

					// Comprobar que no exista el empleado gracias a su identificador
					if (posicion >= file.length()) {

						// Posicionarse en la posición establecida
						file.seek(posicion);

						// Escribir el id
						file.writeInt(codDep);

						// Escribir el nombre
						StringBuffer buffer = new StringBuffer(nombre);
						buffer.setLength(15);
						file.writeChars(buffer.toString());

						// Escribir la localidad
						buffer = new StringBuffer(localidad);
						buffer.setLength(15);
						file.writeChars(buffer.toString());

						// Escribir el resto de datos
						file.writeInt(numeroEmpleados);
						file.writeFloat(mediaSalario);

						// Devolver el mensaje de que el empleado ya ha sido registrado
						return "REGISTRO INSERTADO";
					}


					

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}

			return "ERROR INSEPERADO";
			
		}
			

	}

	private static boolean consultarRegistro(int codigoDepartamento) throws IOException {

		// Establecer el nombre del fichero con el que vamos a trabajar
		File fichero = new File(".\\AleatorioEmple.dat");

		// Inicializar una variable booleana
		boolean existe = false;

		try {

			// Declarar el fichero de acceso aleatorio
			RandomAccessFile file = new RandomAccessFile(fichero, "r");

			// Establezco la posición del puntero sabiendo el indentificador del empleado en
			// concreto
			int posicion = (codigoDepartamento - 1) * 72;

			// Comprobar que exista el identificador
			if (codigoDepartamento == 0) {
				existe = false;
			} else if (posicion >= file.length()) {
				existe = false;
			} else {

				// Posicionarse en le posición establecida anteriormente gracias al
				// identificador
				file.seek(posicion);

				// Obtener el identificador del empleado y guardarlo en una variable
				int codDep = file.readInt();

				// Comprobar su existe o no el codigo de departamento
				if (codDep == codigoDepartamento) {
					existe = true;
				}

			}

			// Cerrar el fichero
			file.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return existe;
	}

	private static void crearFichero(String nombreFichero) throws IOException {
		// Establecer el nombre del fichero
		File fichero = new File(".\\" + nombreFichero + ".dat");

		// Comprobar que el fichero ya esté creado o no
		if (fichero.exists()) {
			System.out.println("El fichero ya está creado, no se puede volver a crear");
		} else {

			// Declarar el fichero de acceso aleatorio
			RandomAccessFile file = new RandomAccessFile(fichero, "rw");

			// Establecer los datos en distintos arrays
			// Arrasys con los datos
			String nombre[] = { "José", "Mario", "Alberto", "Luisa", "Fernando" };
			String localidad[] = { "Talavera", "Toledo", "Valladolid", "Valencia", "Alicante" };
			int numeroEmpleado[] = { 31, 45, 35, 15, 45 };
			float mediaSalario[] = { 1500, 2300, 1750, 3400, 1200 };

			// Inicializar el buffer para las variables que sean strings
			StringBuffer buffer = null;

			// Guardar en una variable el tamaño de los arrays
			int longitud = nombre.length;

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

}
