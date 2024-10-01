import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

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
		System.out.println(" 0) Salir");
		System.out.println("-------------------------------------------------");

	}

}
