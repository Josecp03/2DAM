package ejercicioNotas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import xmlAlumnos.Nota;

public class Principal {

	// Declarar la longitud de los ficheros
	static int LON_Alumnos = 92;
	static int LON_Notas = 48;

	// Método Principal
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
				crearXML();
				break;
			case 5:
				int numeroAlumno = 2;
				String nombreAsignatura = "Acceso a Datos";
				float nota = (float) 4.67;
				insertarNota(numeroAlumno, nombreAsignatura, nota);
				break;
			case 6:
				int numeroAlumnoBorrado = 1;
				borrarAlumno(numeroAlumnoBorrado);
				break;
			case 7:
				System.out.println("FIN DEL MENÚ!");
				break;
			default:
				System.out.println("Seleccione una opción válida");
				break;
			}

		} while (opcion != 7);

		// Cerrar el fichero
		sc.close();

	}
	
	// Método para borrar un alumno
	private static void borrarAlumno(int numeroAlumnoBorrado) throws IOException {
		
		// Inicializar el objeto File
		File fichero = new File(".\\Alumnos.dat");

		// Declarar el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "rw");

		// Inicializar variables
		int numAlumnoActual;

		// Establecer la posición a 0 para que empiece desde el principio
		int posicion = 0;

		// Recorrer el fichero
		for (;;) {

			// Situarse en la posición inicializada
			file.seek(posicion);

			// Obtener el código del producto
			numAlumnoActual = file.readInt();

			if (numAlumnoActual == numeroAlumnoBorrado) {
				
				// Posicionarnos donde el principio
				file.seek(posicion);
				
				// Escribir un 0 en el número de alumno
				file.writeInt(0);
								
			}

			// Posicionarse en el siguiente alumno
			posicion += LON_Alumnos;

			// Salir del for cuando haya recorrido todos los bytes (Y cerrar el file)
			if (posicion >= file.length()) {
				break;
			}

		}
		
		// Cerrar el fichero
		file.close();
		
	}

	// Método para insertar una nueva nota
	private static void insertarNota(int numeroAlumno, String nombreAsignatura, float nota) throws IOException {
		
		// Inicializar el objeto File
        File fichero = new File(".\\Notas.dat");
        
        // Declarar el fichero de acceso aleatorio
        RandomAccessFile file = new RandomAccessFile(fichero, "rw");
                
        // Comprobar que el número de alumno es válido
        if (numeroCorrecto(numeroAlumno)) {
            
        	// Posicionarnos al final
            file.seek(file.length());
            
            // Esribimos el número de alumno
            file.writeInt(numeroAlumno);
            
            // Buffer para almacenar el 
         	StringBuffer buffer = null;
    		
    		// Establecer 20 caracteres para el nombre de la asignatura
    		buffer = new StringBuffer(nombreAsignatura);
    		buffer.setLength(20);
    		file.writeChars(buffer.toString());
    		
    		// Escribir la nota
    		file.writeFloat(nota);
    		
    		// Cerrar el fichero
    		file.close();
		} else {
			
			// Imprimir mensaje de error
			System.out.println("Número no encontrado en el fichero alumnos");
		}
        		
	}

	// Método para comprobar que el número sea correcto
	private static boolean numeroCorrecto(int numeroAlumno) throws IOException {
		
		// Inicializar un valor booleano
		boolean existe = false;
		
		// Comprobar que el número de alumno sea positivo
		if (numeroAlumno > 1) {
			
			// Inicializar el objeto File
			File fichero = new File(".\\Alumnos.dat");

			// Declarar el fichero de acceso aleatorio
			RandomAccessFile file = new RandomAccessFile(fichero, "r");

			// Inicializar variables
			int numAlumno;

			// Establecer la posición a 0 para que empiece desde el principio
			int posicion = 0;

			// Recorrer el fichero
			for (;;) {

				// Situarse en la posición inicializada
				file.seek(posicion);

				// Obtener el código del producto
				numAlumno = file.readInt();

				// Comporbar que coincida con el numero de alumno actual
				if (numAlumno == numeroAlumno) {
					existe = true;
				}
				
				// Posicionarse en el siguiente alumno
				posicion = posicion + LON_Alumnos;

				// Salir del for cuando haya recorrido todos los bytes (Y cerrar el file)
				if (posicion >= file.length()) {
					break;
				}

			}
			
			// Cerrar el fichero
			file.close();
			
		} else {
			
			// Imprimir mensaje de error
			System.out.println("Número incorrecto");
		}
		
		// Devolver el valor booleano
		return existe;
	}

	// Método para crear el xml
	private static void crearXML() throws IOException {
		
		// Inicializar variables
		int numalumno; 
		String nombre; 
		String localidad; 
		int numasig;
		float notamedia;

		// Cargar el fichero productos en la lista de alumnos
		ArrayList<xmlAlumnos.Alumno> listaAlumnos = new ArrayList<>();
		
		// Inicializar el objeto file
		File fichero = new File(".\\Alumnos.dat");
		
		try {
			
			// Declarar el fichero de acceso aleatorio
			RandomAccessFile file = new RandomAccessFile(fichero, "r");

			// Inicializar la posición a 0
			int posicion = 0; 
			
			// Recorrer el fichero
			for (;;) { 
				
				// Posicionarse correctamente
				file.seek(posicion);
				
				// Leer el número de alumno
				numalumno = file.readInt();
				
				// Obtener los datos si no es vacío
				if (numalumno != 0) {
					
					// Leer el nombre
					nombre = "";
					for (int i = 0; i < 20; i++) {
						nombre = nombre + file.readChar();
					}
					
					// Leer la localidad
					localidad = "";
					for (int i = 0; i < 20; i++) {
						localidad = localidad + file.readChar();
					}
					
					// Leer el número de asignaturas
					numasig = file.readInt();
					
					// Leer la nota media
					notamedia = file.readFloat();
					
					// Inicializar la lista de notas
					ArrayList<xmlAlumnos.Nota> notas = listaNotas(numalumno);
					
					// Crear la lista y añadirla
					xmlAlumnos.Alumno alumno = new xmlAlumnos.Alumno(numalumno, nombre.trim(), localidad.trim(), numasig, notamedia, notas);
					listaAlumnos.add(alumno);

				}
				
				// Posicionarnos en el siguiente alumno
				posicion = posicion + LON_Alumnos;

				// Salir del bucle cuando lleguemos al final del fichero
				if (posicion >= file.length()) {
					break;
				}
					
			} 

			// Cerrar el fichero
			file.close();
			
			// GENERAR EL XML, creamos un objeto de la raiz, y asignamos los productos
			xmlAlumnos.ListaAlumnos aa = new xmlAlumnos.ListaAlumnos();
			aa.setListaAlumnos(listaAlumnos);;
			
			// Inicializar el contexto
			JAXBContext context;
			try {
				
				context = JAXBContext.newInstance(xmlAlumnos.ListaAlumnos.class);
				Marshaller m = context.createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				m.marshal(aa, System.out);
				m.marshal(aa, new File(".\\ProductosVentas.xml"));
			} catch (JAXBException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			
			System.out.println("**** FICHERO NO ENCONTRADO ****");
			e.printStackTrace();
		}
		
	}

	// Método que devuelve un ArrayList con las notas del alumno indicado
	private static ArrayList<Nota> listaNotas(int numalumno) throws IOException {
		
		// Inicializar el Arrayist
		ArrayList<xmlAlumnos.Nota> notas = new ArrayList<xmlAlumnos.Nota>();
		
		// Inicializar los objetos File
		File fichero = new File(".\\Notas.dat");
		
		// Declarar el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "r");
		
		// Inicializar la posicion
		int posicion = 0;
		
		// Inicializar variables
		int numAlumnoActual;
		String asignatura = "";
		float nota = 0;
		
		// Recorrer el fichero
		for(;;) {
			
			// Posicionarnos correctamente
			file.seek(posicion);
			
			// Leer el número de alumno actual
			numAlumnoActual = file.readInt();
			
			// Leer la asignatura
			asignatura = "";
			for (int i = 0; i < 20; i++) {
				asignatura += file.readChar();
			}
						
			// Comprobar que sea igual al que le he pasado como parámetro
			if (numAlumnoActual == numalumno) {
				
				// Leer la nota
				nota = file.readFloat();
				
				// Crear la nota
				xmlAlumnos.Nota n = new xmlAlumnos.Nota(asignatura.trim(), nota);
				
				// Añadir la nota a la lista
				notas.add(n);
				
			}

			// Actualizar la posición
			posicion += LON_Notas;
			
			// Salirnos del fichero cuando llegue al final
			if (posicion >= file.length()) {
				break;
			}
		
		}
		
		// Cerrar el fichero
		file.close();
		
		// Devolver la lista de ventas
		return notas;
		
	}

	// Método para actualizar las nota sy el número de asinaturas de cada alumno
	private static void actualizarAlumnos() throws IOException {

		// Inicializar el objeto File
		File fichero = new File(".\\Alumnos.dat");

		// Declarar el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "rw");

		// Inicializar variables
		int numAlumno;
		int numAsignaturas;
		float nota;
		float sumaMedias = 0;
		int contadorAlumnos = 0;
		float notaMaxima = 0;
		char nombreAlumno[] = new char[20];
		char aux;
		String nombreAlumnoNotaMaxima = "";

		// Establecer la posición a 0 para que empiece desde el principio
		int posicion = 0;

		// Recorrer el fichero
		for (;;) {

			// Situarse en la posición inicializada
			file.seek(posicion);

			// Obtener el código del producto
			numAlumno = file.readInt();

			if (numAlumno != 0) {

				// Aumentar el contador de alumnos
				contadorAlumnos ++;
				
				// Calcular el número de asignaturas con la llamada al método
				numAsignaturas = calcularNumeroAsignaturas(numAlumno);
				
				// Calcular la nota media de cada alumno
				nota = calcularNotaMedia(numAlumno, numAsignaturas);

				// Calcular el nombre del alumno con nota media máxima
				if (nota > notaMaxima) {
					notaMaxima = nota;
					
					// Leer el nombre del alumno con la nota máxima actual
					// Obtener el nombre del alumno recorriendo uno a uno los caracteres
					for (int i = 0; i < nombreAlumno.length; i++) {
						aux = file.readChar(); // Leer el caracter
						nombreAlumno[i] = aux; // Guardarlo en el array formando el nombre completo
					}

					// Convertir el array en un string
					String nombreAlumnoS = new String(nombreAlumno);
					
					// Asignar ese nombre al nombre de la máxima media actual
					nombreAlumnoNotaMaxima = nombreAlumnoS;
					
				}
				
				// Sumar todas las notasMedias de todos los alumnos
				sumaMedias += nota;
				
				// Posicionarnos donde el número de asignaturas
				file.seek(posicion + 84);
				
				// Escribir el número de asignaturas de ese alumno
				file.writeInt(numAsignaturas);
				
				// Actualizar de nuevo la posición para colocarnos donde la nota media
				file.seek(posicion + 88);
				
				// Escribir la nota media del alumno
				file.writeFloat(nota);
				
			}

			// Posicionarse en el siguiente alumno
			posicion += LON_Alumnos;

			// Salir del for cuando haya recorrido todos los bytes (Y cerrar el file)
			if (posicion >= file.length()) {
				
				// Imprimir el fichero actualizado
				listarAlumnos();
				System.out.printf("%7s %-20s %-20s %9s %11s %n", "-------", "--------------------", "--------------------",
						"-------", "-----------");
				
				// Imprimir el nombre del alumno con mayor nota media
				System.out.println("Alumno con nota media máxima: " + nombreAlumnoNotaMaxima);
				
				// Imprimir la media de nota total
				System.out.printf("Media de nota total: %.2f %n", sumaMedias/contadorAlumnos);
				break;
			}

		}
		
		// Cerrar el fichero
		file.close();
		
	}

	// Método para calular la nota media del alumno buscado
	private static float calcularNotaMedia(int numAlumnoBuscado, int numAsignaturas) throws IOException{
		
		// Inicializar el objeto File
		File fichero = new File(".\\Notas.dat");

		// Declarar el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "r");

		// Inicializar variables
		int numAlumnoActual;
		float nota;

		// Establecer la posición a 0 para que empiece desde el principio
		int posicion = 0;

		// Inicializar la suma de las notas
		float sumaNotas = 0;
		
		// Recorrer el fichero
		for (;;) {

			// Situarse en la posición inicializada
			file.seek(posicion);

			// Obtener el código del producto
			numAlumnoActual = file.readInt();

			// Comprobar que sea el alumno buscado
			if (numAlumnoActual == numAlumnoBuscado) {
				
				// Posicionarnos en el registro de la nota
				file.seek(posicion + 44);
				
				// Obtener la nota
				nota = file.readFloat();
				
				// Actualizar la suma de las notas
				sumaNotas += nota;
				
			}

			// Posicionarse en el siguiente alumno
			posicion = posicion + LON_Notas;

			// Salir del for cuando haya recorrido todos los bytes (Y cerrar el file)
			if (posicion >= file.length()) {
				break;
			}

		}
		
		// Cerrar el fichero
		file.close();
		
		// Devolver la media de las notas
		return sumaNotas/numAsignaturas;
		
	}

	// Método para calcular el número de asignaturas de un método
	private static int calcularNumeroAsignaturas(int numAlumnoBuscado) throws IOException {
		
		// Inicializar el objeto File
		File fichero = new File(".\\Notas.dat");

		// Declarar el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "r");

		// Inicializar variables
		int numAlumnoActual;

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

			// Posicionarse en el siguiente alumno
			posicion = posicion + LON_Notas;

			// Salir del for cuando haya recorrido todos los bytes (Y cerrar el file)
			if (posicion >= file.length()) {
				break;
			}

		}
		
		// Cerrar el fichero
		file.close();
		
		return contadorAsignaturas;
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
				System.out.printf("%7s %-20s %-20s %9s %11.2f %n", numAlumno, nombreS, localidadS, numAsignaturas, nota);

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
		System.out.println(" 5) Insertar Nueva nota");
		System.out.println(" 6) Borrar Alumno");
		System.out.println(" 7) Salir");
		System.out.println("-------------------------------------------------");

	}

}
