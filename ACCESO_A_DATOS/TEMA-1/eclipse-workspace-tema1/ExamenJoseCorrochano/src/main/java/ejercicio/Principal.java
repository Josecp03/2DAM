package ejercicio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Principal {

	// Declarar la longitud de los ficheros
	static int LON = 74;
	
	// Método main
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
				actualizarViajes();
				break;
			case 2:
				listarViajes();
				break;
			case 3:
				crearXML();
				break;
			case 0:
				System.out.println("FIN DEL MENÚ!");
				break;
			default:
				System.out.println("Seleccione una opción válida");
				break;
			}

		} while (opcion != 0);

		// Cerrar el fichero
		sc.close();
		
	}
	
	// Método para actualizar los Viajes
	private static void actualizarViajes() {
		
		
		
	}

	// Método para crear el XML
	private static void crearXML() throws IOException {
		
		// Inicializar variables
		int codigoViaje; 
		String nombre; 
		int pvp; 
		int plazas;
		String operacion;

		// Cargar el fichero productos en la lista de alumnos
		ArrayList<xmlViajes.Viaje> listaViajes = new ArrayList<>();
		
		// Inicializar el objeto file
		File fichero = new File(".\\Viajes.dat");
		
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
				codigoViaje = file.readInt();
				
				// Obtener los datos si no es vacío
				if (codigoViaje != 0) {
					
					// Leer el nombre
					nombre = "";
					for (int i = 0; i < 30; i++) {
						nombre = nombre + file.readChar();
					}
										
					// Leer el pvp
					pvp = file.readInt();
					
					// Leer las plazas
					plazas = file.readInt();
					
					// Leer operacion
					operacion = "";
					for (int i = 0; i < 1; i++) {
						operacion = operacion + file.readChar();
					}
					
					// Crear la lista y añadirla
					xmlViajes.Viaje viaje = new xmlViajes.Viaje(codigoViaje, nombre.trim(), pvp, plazas, operacion.trim());
					listaViajes.add(viaje);

				}
				
				// Posicionarnos en el siguiente alumno
				posicion = posicion + LON;

				// Salir del bucle cuando lleguemos al final del fichero
				if (posicion >= file.length()) {
					break;
				}
					
			} 

			// Cerrar el fichero
			file.close();
			
			// GENERAR EL XML, creamos un objeto de la raiz, y asignamos los productos
			xmlViajes.ListaViajes aa = new xmlViajes.ListaViajes();
			aa.setListaViajes(listaViajes);;
			
			// Inicializar el contexto
			JAXBContext context;
			try {
				context = JAXBContext.newInstance(xmlViajes.ListaViajes.class);
				Marshaller m = context.createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				m.marshal(aa, System.out);
				m.marshal(aa, new File(".\\viajes.xml"));
			} catch (JAXBException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			
			System.out.println("**** FICHERO NO ENCONTRADO ****");
			e.printStackTrace();
		}
		
	}

	// Método para listar los viajes
	private static void listarViajes() throws IOException {
		
		// Inicializar el objeto File
		File fichero = new File(".\\Viajes.dat");

		// Declarar el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "r");

		// Inicializar variables
		int codigoViaje;
		char nombre[] = new char[30];
		int pvp;
		int plazas;
		char situacion[] = new char[1];
		char aux;
		
		// Inicializar las sumas para calcular
		int sumaPvps = 0;
		int contadorPvps = 0;
		int sumaPlazas = 0;
		int contadorPlazas = 0;

		// Establecer la posición a 0 para que empiece desde el principio
		int posicion = 0;

		// Escribir el encabezado
		System.out.printf("%9s %-30s %9s %7s %7s %n", "CodViaje", "Nombre", "PVP", "PLAZAS", "SITUACIÓN");
		System.out.printf("%9s %-30s %9s %7s %7s %n", "--------", "------------------------------", "-------", "-------", "---------");

		// Recorrer el fichero
		for (;;) {

			// Situarse en la posición inicializada
			file.seek(posicion);

			// Obtener el código del viaje
			codigoViaje = file.readInt();

			if (codigoViaje != 0) {

				// Obtener el nombre del viaje recorriendo uno a uno los caracteres
				for (int i = 0; i < nombre.length; i++) {
					aux = file.readChar(); // Leer el caracter
					nombre[i] = aux; // Guardarlo en el array formando el nombre completo
				}

				// Convertir el array en un string
				String nombreS = new String(nombre);

				// Obtener el pvp
				pvp = file.readInt();
				
				// Aumentar la suma de pvps y aumentar su contador
				sumaPvps += pvp;
				contadorPvps++;
				
				// Obtener las plazas
				plazas = file.readInt();
				
				// Aumentar la suma de plazas y aumentar su contador
				sumaPlazas += plazas;
				contadorPlazas++;
				
				// Obtener la situacion recorriendo uno a uno los caracteres
				for (int i = 0; i < situacion.length; i++) {
					aux = file.readChar(); // Leer el caracter
					situacion[i] = aux; // Guardarlo en el array formando el nombre completo
				}
				
				// Convertir el array en un string
				String situacionS = new String(situacion);
				
				
				// Imprimir el registro actual
				System.out.printf("%9s %-30s %9s %7s %7s %n", codigoViaje, nombreS, pvp, plazas, situacionS);

			}

			// Posicionarse en el siguiente alumno
			posicion = posicion + LON;

			// Salir del for cuando haya recorrido todos los bytes (Y cerrar el file)
			if (posicion >= file.length()) {
				System.out.printf("%9s %-30s %9s %7s %7s %n", "--------", "------------------------------", "-------", "-------", "---------");
				System.out.printf("%9s %-30s %9s %7s %7s %n" , "MEDIAS", "", sumaPvps/contadorPvps, sumaPlazas/contadorPlazas, "");
				break;
			}

		}
		
		// Cerrar el fichero
		file.close();
		
	}

	// Método para mostrar el menú
	private static void mostrarMenu() {
		System.out.println("--------------------------------------------------------------------");
		System.out.println(" 1) Actualizar Viajes.dat");
		System.out.println(" 2) Listar el fichero Viajes.dat");
		System.out.println(" 3) Crear XML viajes.xml");
		System.out.println(" 0) Salir");
		System.out.println("---------------------------------------------------------------------");

	}
	
}
