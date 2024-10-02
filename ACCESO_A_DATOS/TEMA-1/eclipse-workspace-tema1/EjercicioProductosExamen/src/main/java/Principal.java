import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import xmlProductosVentas.Venta;

public class Principal {

	// Declarar la longitud de los ficheros
	static int LON_Productos = 46;
	static int LON_Ventas = 28;

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
				leerProductos();
				break;
			case 2:
				leerVentas();
				break;
			case 3:
				actualizarProductos();
				break;
			case 4:
				crearXMLProductos();
				break;
			case 5:
				crearXMLProductosVentas();
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

	// Método para crear el XML de productos y ventas
	private static void crearXMLProductosVentas() throws IOException {
		
		// Inicializar variables
		int codigoPro; 
		String nombrePro; 
		int existencias; 
		double pvp; 

		// Cargar el fichero productos en la lista de productos
		ArrayList<xmlProductosVentas.Producto> listaProductos = new ArrayList<>();
		
		// Inicializar el objeto file
		File fichero = new File(".\\Productos.dat");
		// declara el fichero de acceso aleatorio
		try {
			
			// Declarar el fichero de acceso aleatorio
			RandomAccessFile file = new RandomAccessFile(fichero, "r");

			// Inicializar la posición a 0
			int posicion = 0; 
			
			// Recorrer el fichero
			for (;;) { 
				
				// Posicionarse correctamente
				file.seek(posicion);
				
				// Leer el código
				codigoPro = file.readInt();
				
				// Obtener los datos si no es vacío
				if (codigoPro != 0) {
					
					// Leer el nombre
					nombrePro = "";
					for (int i = 0; i < 15; i++) {
						nombrePro = nombrePro + file.readChar();
					}
					
					// Leer las existencias
					existencias = file.readInt();
					
					// Leer el precio
					pvp = file.readDouble();
					
					// Inicializar la lista de ventas
					ArrayList<xmlProductosVentas.Venta> ventas = listaVentas(codigoPro, pvp);
					
					// Crear la lista y añadirla
					xmlProductosVentas.Producto pro = new xmlProductosVentas.Producto(codigoPro, nombrePro.trim(), existencias, pvp, ventas);
					listaProductos.add(pro);

				}
				
				// Posicionarnos en el siguiente producto
				posicion = posicion + LON_Productos;

				// Salir del bucle cuando lleguemos al final del fichero
				if (posicion >= file.length()) {
					break;
				}
					
			} 

			// Cerrar el fichero
			file.close();
			
			// GENERAR EL XML, creamos un objeto de la raiz, y asignamos los productos
			xmlProductosVentas.Productos pp= new xmlProductosVentas.Productos();
			pp.setListaProductos(listaProductos);
			
			// Inicializar el contexto
			JAXBContext context;
			try {
				
				context = JAXBContext.newInstance(xmlProductosVentas.Productos.class);
				Marshaller m = context.createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				m.marshal(pp, System.out);
				m.marshal(pp, new File(".\\ProductosVentas.xml"));
			} catch (JAXBException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			
			System.out.println("**** FICHERO NO ENCONTRADO ****");
			e.printStackTrace();
		}

		
	}

	private static ArrayList<Venta> listaVentas(int codigoPro, double pvp) throws IOException {
		
		// Inicializar el Arrayist
		ArrayList<xmlProductosVentas.Venta> ventas = new ArrayList<xmlProductosVentas.Venta>();
		
		// Inicializar los objetos File
		File fichero = new File(".\\DatosdeVentas.dat");
		
		// Declarar el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "r");
		
		// Inicializar la posicion
		int posicion = 0;
		
		// Inicializar variables
		int codigoProActual;
		int unidadesVendidas = 0;
		String fecha = "";
		double importe = 0;
		
		// Recorrer el fichero
		for(;;) {
			
			// Posicionarnos correctamente
			file.seek(posicion);
			
			// Leer el código actual
			codigoProActual = file.readInt();
			
			// Leer la unidades vendidas del producto
			unidadesVendidas = file.readInt();
			
			// Leer la fecha
			fecha = "";
			for (int i = 0; i < 10; i++) {
				fecha += file.readChar();
			}
						
			// Comprobar que sea igual al que le he pasado como parámetro
			if (codigoPro == codigoProActual) {

				// Calcular el importe
				importe = pvp * unidadesVendidas;
				
				// Crear la venta
				xmlProductosVentas.Venta v = new xmlProductosVentas.Venta(unidadesVendidas, fecha.trim(), importe);
				
				// Añadir la venta a la lista
				ventas.add(v);
				
			}

			// Actualizar la posición
			posicion += LON_Ventas;
			
			// Salirnos del fichero cuando llegue al final
			if (posicion >= file.length()) {
				break;
			}
		
		}
		
		// Cerrar el fichero
		file.close();
		
		// Devolver la lista de ventas
		return ventas;
		
	}

	// Método para crear el XML de Productos
	private static void crearXMLProductos() throws IOException {
		
		int codigoPro; 
		String nombrePro; 
		int existencias; 
		double pvp; 

		// Cargar el fichero productos en la lista de productos
		ArrayList<xmlProductos.Producto> listaProductos = new ArrayList<>();
		
		// Inicializar el onjeto file
		File fichero = new File(".\\Productos.dat");
		// declara el fichero de acceso aleatorio
		try {
			
			// Declarar el fichero de acceso aleatorio
			RandomAccessFile file = new RandomAccessFile(fichero, "r");

			// Inicializar la posición a 0
			int posicion = 0; 

			// Recorrer el fichero
			for (;;) { 
				
				// Posicionarse correctamente
				file.seek(posicion);
				
				// Leer el código
				codigoPro = file.readInt();
				
				// Obtener los datos si no es vacío
				if (codigoPro != 0) {
					
					// Leer el nombre
					nombrePro = "";
					for (int i = 0; i < 15; i++) {
						nombrePro = nombrePro + file.readChar();
					}
					
					// Leer las existencias
					existencias = file.readInt();
					
					// Leer el precio
					pvp = file.readDouble();
					
					// Inicializar aquí las unidades vendidas porque es distinto para cada producto
					int uni = 0;
					
					// calcular las unidades vendidas
					// hacer un método que devuelva las unidades vendidas del prod
					uni = unidadesVendidas(codigoPro);
					
					// Calcular el importe para el código actual
					double impor = uni * pvp;
					
					// Calcular el mensaje de estadi
					String mensaje="";
					int stock = existencias - uni;
					if (stock < 2) {
						mensaje = "* A REPONER *";
					}
					
					xmlProductos.Producto pro = new xmlProductos.Producto(codigoPro, nombrePro.trim(), existencias, pvp,uni,impor,mensaje);
					listaProductos.add(pro);

				}
				
				// Posicionarnos en el siguiente producto
				posicion = posicion + LON_Productos;

				// Salir del bucle cuando lleguemos al final del fichero
				if (posicion >= file.length()) {
					break;
				}
					
			} 

			// Cerrar el fichero
			file.close();
			
			// GENERAR EL XML, creamos un objeto de la raiz, y asignamos los productos
			xmlProductos.Productos pp= new xmlProductos.Productos();
			pp.setListaProductos(listaProductos);
			
			// Inicializar el contexto
			JAXBContext context;
			try {
				
				context = JAXBContext.newInstance(xmlProductos.Productos.class);
				Marshaller m = context.createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				m.marshal(pp, System.out);
				m.marshal(pp, new File(".\\Productos.xml"));
			} catch (JAXBException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			
			System.out.println("**** FICHERO NO ENCONTRADO ****");
			e.printStackTrace();
		}

		
	}

	// Método para calcular las unidades vendidas por cada producto
	private static int unidadesVendidas(int codigoPro) throws IOException {
		
		// Inicializar los objetos File
		File fichero = new File(".\\DatosDeVentas.dat");
		
		// Declarar el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "r");
		
		// Inicializar la posicion
		int posicion = 0;
		
		// Inicializar variables
		int codigoProActual;
		int unidadesVendidas;
		int unidadesVendidasTotales = 0;
		
		// Recorrer el fichero
		for(;;) {
			
			// Posicionarnos correctamente
			file.seek(posicion);
			
			// Leer el código actual
			codigoProActual = file.readInt();
			
			// Comprobar que sea igual al que le he pasado como parámetro
			if (codigoPro == codigoProActual) {
				
				// Leer la unidades vendidas del producto
				unidadesVendidas = file.readInt();
				
				// Sumar esas unidades a las unidades totales
				unidadesVendidasTotales += unidadesVendidas;
			}
			
			// Actualizar la posición
			posicion += LON_Ventas;
			
			// Salirnos del fichero cuando llegue al final
			if (posicion >= file.length()) {
				break;
			}
			
		}
		
		// Cerrar el fichero
		file.close();
		
		// Devolver las unidades vendidas totales de cada producto
		return unidadesVendidasTotales;
	}

	// Método para actualizar los productos
	private static void actualizarProductos() throws IOException {
		
		// Inicializar los objetos File
		File ficheroProductos = new File(".\\Productos.dat");
		File ficheroVentas = new File(".\\DatosDeVentas.dat");
		
		// Declarar el fichero de acceso aleatorio
		RandomAccessFile fileProductos = new RandomAccessFile(ficheroProductos, "rw");
		RandomAccessFile fileVentas = new RandomAccessFile(ficheroVentas, "r");
				
		// Inicializar la posicion
		int posicionProductos = 0;
		int posicionVentas = 0;
		
		// Inicializar variables
		int codigoProVentas;
		int codigoProProductos;
		int unidadesVendidas;
		int existencias;
		
		// Recorrer el fichero
		for (;;) {
			
			// Posicionarnos correctamente en ventas
			fileVentas.seek(posicionVentas);
			
			// Leer el código de producto
			codigoProVentas = fileVentas.readInt();
			
			// Leer las unidades vendidad
			unidadesVendidas = fileVentas.readInt();
			
			// Comprobar que el código sea válido
			if (codigoProVentas < 1 || codigoProVentas > 99) {
				System.out.println("Error, el código no está entre 1 y 99");
			} else {
				
				// Calcular la posición del otro fichero conociendo el código
				posicionProductos = (codigoProVentas - 1) * LON_Productos;
				
				// Comprobar que el código esté en el otro fichero
				if (posicionProductos >= fileProductos.length()) {
					System.out.println("Código no encontrado");
				} else {
					
					// Posicionarnos correctamente
					fileProductos.seek(posicionProductos);
					
					// Leer el código del producto en el fichero de productos
					codigoProProductos = fileProductos.readInt();
					
					// Comprobar que sean iguales para actualizar el indicado
					if (codigoProVentas == codigoProProductos) {
						
						// Saltamos a la posición de existencias (Actualmente estamos al principio del registro por lo que 4 + 15*2)
						fileProductos.seek(posicionProductos + 34);
						
						// Leer las existencias actuales
						existencias = fileProductos.readInt();
						
						// Actualizar la variable existencias
						existencias -= unidadesVendidas;
						
						// Nos volvemos a posicionar para escribirlo bien
						fileProductos.seek(posicionProductos + 34); 
						
						// Escribir las existencias actualizadas
						fileProductos.writeInt(existencias);
						
						// Mostramos mensaje de confirmación
						System.out.println("Localizado el producto " + codigoProProductos + ", se actualiza, existencias actuales: " + existencias);
						
					} else {
						System.out.println("No localizado o es hueco");
					}
					
				}
				
			}
			
			// Actualizar la posición
			posicionVentas += LON_Ventas;
			
			// Salirnos del fichero cuando llegue al final
			if (posicionVentas >= fileVentas.length()) {
				break;
			}
			
		}
		
		// Cerrar los ficheros
		fileProductos.close();
		fileVentas.close();
		
	}

	// Método para leer las ventas
	private static void leerVentas() throws IOException {

		// Inicializar el objeto File
		File fichero = new File(".\\DatosdeVentas.dat");

		// Declarar el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "r");

		// Inicializar variables
		int codigoPro;
		int uniVen;
		char fecha[] = new char[10];
		char aux;

		// Establecer la posición a 0 para que empiece desde el principio
		int posicion = 0;

		// Escribir el encabezado
		System.out.printf("%6s %7s %-10s %n", "CODIGO", "UNI VEN", "FECHA");
		System.out.printf("%6s %7s %-10s %n", "------", "-------", "-----------");

		// Recorrer el fichero
		for (;;) {

			// Situarse en la posición inicializada
			file.seek(posicion);

			// Obtener el código del producto
			codigoPro = file.readInt();

			// Obtener el número de ventas
			uniVen = file.readInt();

			// Obtener la fecha recorriendo uno a uno los caracteres
			for (int i = 0; i < fecha.length; i++) {
				aux = file.readChar(); // Leer el caracter
				fecha[i] = aux; // Guardarlo en el array formando el nombre completo
			}
			
			// Convertir el array en un string
			String fechaS = new String(fecha);

			System.out.printf("%6s %7s %-10s %n", codigoPro, uniVen, fechaS);

			// Posicionarse en el siguiente alumno
			posicion = posicion + LON_Ventas;

			// Salir del for cuando haya recorrido todos los bytes (Y cerrar el file)
			if (file.getFilePointer() == file.length()) {
				file.close();
				break;
			}

		}

	}

	// Método para leer los productos
	private static void leerProductos() throws IOException {

		// Inicializar el objeto File
		File fichero = new File(".\\Productos.dat");

		// Declarar el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "r");

		// Inicializar variables
		int codigoPro;
		char nombrePro[] = new char[15];
		char aux;
		int existencias;
		double pvp;

		// Establecer la posición a 0 para que empiece desde el principio
		int posicion = 0;

		// Escribir el encabezado
		System.out.printf("%6s %-20s %11s %9s %n", "CODIGO", "NOMBRE", "EXISTENCIAS", "PVP");
		System.out.printf("%6s %-20s %11s %9s %n", "------", "--------------------", "-----------", "-------");

		// Recorrer el fichero
		for (;;) {

			// Situarse en la posición inicializada
			file.seek(posicion);

			// Obtener el código del producto
			codigoPro = file.readInt();

			if (codigoPro != 0) {

				// Obtener el nombre del producto recorriendo uno a uno los caracteres
				for (int i = 0; i < nombrePro.length; i++) {
					aux = file.readChar(); // Leer el caracter
					nombrePro[i] = aux; // Guardarlo en el array formando el nombre completo
				}

				// Convertir el array en un string
				String nombreProS = new String(nombrePro);

				// Obtener los datos restantes
				existencias = file.readInt();
				pvp = file.readDouble();

				System.out.printf("%6s %-20s %11s %9s %n", codigoPro, nombreProS, existencias, pvp);

			}

			// Posicionarse en el siguiente alumno
			posicion = posicion + LON_Productos;

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
		System.out.println(" 1) Listar Productos");
		System.out.println(" 2) Listar Ventas");
		System.out.println(" 3) Actualizar Productos");
		System.out.println(" 4) Crear XML Productos");
		System.out.println(" 5) Crear XML ProductosVentas");
		System.out.println(" 0) Salir");
		System.out.println("-------------------------------------------------");

	}

}
