package pruebaficheros;

import java.io.*;

public class LeerFichAleatorio {

	public static void main(String[] args) {

		// Establezco un try cath siempre que se trabajen con lectura/escritura de archivos
		try {

			// Llamada a método para leer el fichero y mostrar su información
			leerfichero();

			// Llamada a método para consultar registros
			System.out.println("---------------------");
			consultarregistro(5); // Consultando un registro que ya existe
			System.out.println("---------------------");
			consultarregistro(10); // Consultando un registro que no existe
			
			// Llamada a método para insertar un registro
			System.out.println("---------------------");
			insertarregistro(6, "NUEVO 6", 10, 1000d); // Insertando un registro en el que el id ya existe
			System.out.println("---------------------");
			insertarregistro(20, "NUEVO 20", 10, 1000d); // Insretando un nuevo registro que no existía
			System.out.println("---------------------");
			insertarregistro(10, "NUEVO 10", 10, 1000d); // Insertando un nuevo registro en un hueco libre
			System.out.println("---------------------");
			insertarregistro(15, "NUEVO 15", 15, 1000d); // Insertando un nuevo registro en un hueco libre otra vez
			System.out.println("---------------------");

			// Llamada al método para modificar el salario de aquellos empleados que pertenecen a un mismo departamento
			System.out.println("------MODIFICAR EMPLES DE UN DEP---------------");
			modificartodoslosdeldep(10, 100d);

			modificartodoslosdeldep(100, 100d);
			
		} catch (IOException e) {
		
			e.printStackTrace();
			
		}

	}

	private static void modificartodoslosdeldep(int depart, double subida) throws IOException {
		// leer secuencialmente todos
		File fichero = new File(".\\AleatorioEmple.dat");
		// declara el fichero de acceso aleatorio
		try {
			RandomAccessFile file = new RandomAccessFile(fichero, "rw");
			int id, dep, posicion;
			Double salario;
			char apellido[] = new char[10], aux;
			posicion = 0; // para situarnos al principio
            int contador=0;
			for (;;) { // recorro el fichero
				file.seek(posicion); // nos posicionamos en posicion
				id = file.readInt(); // obtengo id de empleado
				for (int i = 0; i < apellido.length; i++) {
					aux = file.readChar();// recorro uno a uno los caracteres del apellido
					apellido[i] = aux; // los voy guardando en el array
				}
				String apellidoS = new String(apellido);// convierto a String el array
							
				dep = file.readInt();// obtengo dep
				
				if (dep==depart) {
					//actualizar
					salario = file.readDouble(); // obtengo salario
                    salario=salario+subida;
                    file.seek(posicion+4+20+4); 
                    file.writeDouble(salario);
                    System.out.println(
    						"ID actualizado: " + id + ", Apellido: " 
                    + apellidoS + ", Departamento: " + dep + ", Salario: " + salario);
                    contador=contador+1;
				}
				
				posicion = posicion + 36; 
				// Si he recorrido todos los bytes salgo del for
				if (posicion >= file.length() || file.getFilePointer() == file.length())
					break;

			} // fin bucle for
			file.close(); // cerrar fichero
			
			  System.out.println("Se han actualizado: "+contador +
						" empleados del dep: "+depart);
			
					} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void insertarregistro(int id, String apellido, int dep, double salario) throws IOException {

		// Establecer el nombre del fichero con el que vamos a trabajar
		File fiche = new File(".\\AleatorioEmple.dat");
		
		try {
			// Declarar el fichero de acceso aleatorio
			RandomAccessFile file = new RandomAccessFile(fiche, "rw");

			// Establecer la posición sabiendo el identificador
			long posicion = (id - 1) * 36; 

			// Comprobar que no exista el empleado gracias a su identificador
			if (posicion >= file.length()) {
				
				// Imprimir un mensaje de confirmación cuando el emplado no exista
				System.out.println("ID: " + id + ", NO EXISTE. Se inserta:");

				// Posicionarse en la posición establecida
				file.seek(posicion); 

				// Escribir el id
				file.writeInt(id); 

				// Escribir el apellido
				StringBuffer buffer = new StringBuffer(apellido);
				buffer.setLength(10); 
				file.writeChars(buffer.toString());

				// Escribir el resto de datos
				file.writeInt(dep); 
				file.writeDouble(salario);// insertar salario

			} else { 

				// Imprimir un mensaje si el identificador es 0 o ya existe
				System.out.println("ID: " + id + ", EXISTE O ES HUECO.");
				
				// Posicionarnse en la posición establecida
				file.seek(posicion);

				// Leer el identificador
				int iden = file.readInt();

				// Comprobar que el identificador sea 0 o que ya exista
				if (iden == 0) {
					
					// Escribir el identificador
					file.writeInt(id); 

					// Escribir el apellido
					StringBuffer buffer = new StringBuffer(apellido);
					buffer.setLength(10); 
					file.writeChars(buffer.toString());

					// Escribir el resto de datos
					file.writeInt(dep); 
					file.writeDouble(salario);

					// Imprimir un mensaje de confirmación cuando se haya insertado el nuevo empleado
					System.out.println("ID: " + id + ", ES HUECO, SE INSERTA.");

				} else {
					
					// Imprimir un mensaje de confirmación en caso de que el identifocador ya exista y no sea 0
					System.out.println("ID: " + id + ", YA EXISTE. NO SE INSERTA.");

				}

			}

			// Cerrar el fichero
			file.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	private static void consultarregistro(int identificador) throws IOException {

		// Establecer el nombre del fichero con el que vamos a trabajar
		File fichero = new File(".\\AleatorioEmple.dat");
		
		try {

			// Declarar el fichero de acceso aleatorio
			RandomAccessFile file = new RandomAccessFile(fichero, "r");

			// Establezco la posición del puntero sabiendo el indentificador del empleado en concreto
			int posicion = (identificador - 1) * 36; 

			// Comprobar que exista el identificador
			if (posicion >= file.length())

				// Imprimir un mensaje de error por si no existiera el empleado con ese identificador
				System.out.println("ID: " + identificador + ", NO EXISTE EMPLEADO...");

			else {
				
				// Imprimir un mensaje de confirmación de que el empleado se ha encontrado
				System.out.println("ID: " + identificador + " LOCALIZADO:");

				// Posicionarse en le posición establecida anteriormente gracias al identificador
				file.seek(posicion);

				// Obtener el identificador del empleado y guardarlo en una variable
				int id = file.readInt(); 

				// Inicializar una variable para el apellido 
				String ape = "";

				// Recorrer cada uno de los chars del apellido
				for (int i = 0; i < 10; i++) {

					// Añadir el char actual del apellido en la variable apellido para obtener el apellido completo
					ape = ape + file.readChar();

				}

				// Obtener los datos restantes
				int dep = file.readInt();
				Double salario = file.readDouble();

				// Imprimir los datos del empleado 
				System.out.println("ID: " + id + ", Apellido: " + ape + ", Departamento: " + dep + ", Salario: " + salario);

				// Cerrar el fichero
				file.close();

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	// Método para leer el fichero
	public static void leerfichero() throws IOException {

		// Establecer el nombre del fichero con el que vamos a trabajar
		File fichero = new File(".\\AleatorioEmple.dat");

		// eclarar el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "r");
		
		// Inicalizar las variables para guardar los datos que vamos a ir leyendo
		int id, dep, posicion;
		Double salario;
		char apellido[] = new char[10], aux; 

		// Inicializamos la posición inicial a 0
		posicion = 0; 

		// Recorro el fichero
		for (;;) { 

			// Establezco la posición del puntero
			file.seek(posicion); 

			// Guardo el identificador del empleado actual
			id = file.readInt(); 

			// Recorro todos los chars que contiene el apellido
			for (int i = 0; i < apellido.length; i++) {

				// Voy guardando en una variable el cahr actual del apellido que estoy leyendo
				aux = file.readChar();

				// Guardo en el array que contiene el apellido completo el char atual del apellido guardado en la variable aux
				apellido[i] = aux; 

			}

			// Creo una variable String para guardar el apellido que anteriormente era un array
			String apellidoS = new String(apellido);

			// Guardo los datos restantes en las variables inicializadas anteriormente
			dep = file.readInt();
			salario = file.readDouble(); 

			// Imprimo por pantalla todos los datos del empleado actual
			System.out.println("ID: " + id + ", Apellido: " + apellidoS + ", Departamento: " + dep + ", Salario: " + salario);
			
			// Actualizo la variable posición para posicionarme en el siguiente empleado. Cada empleado ocupa 36 bytes (4+20+4+8)
			posicion = posicion + 36; 
										
			// Cuando recorra todos los bytes del for me salgo del bucle
			if (posicion >= file.length() || file.getFilePointer() == file.length()) {
				break;
			}
				
		}

		// Cerrar el fichero
		file.close(); 

	}
}
