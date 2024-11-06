package ejercicio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Principal {

	// Atributo de conexion para poder acceder a ello en cualquier sitio de la clase
	private static Connection conexion;

	// Bloque estático para inicializar la conexión al iniciar la clase
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "reservas", "dam");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws SQLException {
		
		Scanner sc = new Scanner(System.in);
		int op = -1;

		while (op != 0) {
			menu();
			op = sc.nextInt();

			switch (op) {
			case 1:
								
				informacionReservasCliente();
				
				break;

			case 2:

				crearEstadisticaCiudades();
				break;

			case 3:
				
				// Fecha Actual
				LocalDate fechaActual = LocalDate.now();
		        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yy");
		        String fechaReserva = fechaActual.format(formateador);
		        
		        // Distintos datos
		        int codigoReserva = 2001;
		        int numeroPlaza = 30;
		        int codigoViaje = 10;
		        int codigoCliente = 1;
		        
				insertarReserva(codigoReserva, numeroPlaza, codigoViaje, codigoCliente, fechaReserva);
				break;

			case 0:
				System.out.println("\nHASTA PRONTO\n");
				break;
			default:
				System.out.println("\nPrueba con otra opción\n");
				break;
			}
		}

		// Cerrar el scanner
		sc.close();

		
	}

	private static void insertarReserva(int codigoReserva, int numeroPlaza, int codigoViaje, int codigoCliente, String fechaReserva) throws SQLException {
		
		if (!existeReserva(codigoReserva)) {
			
			if (existeCliente(codigoCliente)) {
				
				if (existeViaje(codigoViaje)) {
					
					if (!plazaOcupada(numeroPlaza, codigoViaje)) {
						
						if (existenPlazas(codigoViaje)) {
							
							if (numPlazaCorrecto(codigoViaje, numeroPlaza)) {
								
								if (primeraReservaViaje(codigoViaje)) {
									
									numeroPlaza = 1;
									System.out.println("Es la primera reserva del viaje");
									
								} 
																
								// Realizar consulta
								String sql = "insert into reservas values(?, ?, ?, ?, ?)";
								PreparedStatement sentencia = conexion.prepareStatement(sql);
								sentencia.setInt(1, codigoReserva);
								sentencia.setInt(2, numeroPlaza);
								sentencia.setString(3, fechaReserva);
								sentencia.setInt(4, codigoCliente);
								sentencia.setInt(5, codigoViaje);
								ResultSet resul = sentencia.executeQuery();
								
								// Cerrar consulta
								resul.close();
								sentencia.close();
								
								// Mostrar mensaje de confirmación
								System.out.println("Reserva insertada con éxito");
								
								// Actualizar las plazas ocupadas en el viaje
								actualizarViajeReserva(codigoViaje);
								
								
							} else {
								
								// Mostrar mensaje de error
								System.out.println("Error. El número de plaza no es correcto para ese viaje");
							}
							
						} else {
							
							// Mostrar mensaje de error
							System.out.println("Error. No existen plazas para ese viaje");
							
						}
						
						
					} else {
						
						// Mostrar mensaje de error
						System.out.println("Error. El numero de plaza ya está ocupada para ese viaje");
						
					}
					
				} else {
					
					// Mostrar mensaje de error
					System.out.println("Error. El viaje no existe");
					
				}
				
			} else {
				
				// Mostrar mensaje de error
				System.out.println("Error. El cliente no existe");
				
			}
			
		} else {
			
			// Mostrar mensaje de error
			System.out.println("Error. La reserva ya existe");
			
		}
		
	}

	private static void actualizarViajeReserva(int codigoViaje) throws SQLException {
		
		// Realizar consulta
		String sql = "update viajes set plazasocupadas = plazasocupadas + 1 where codviaje = ?";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setInt(1, codigoViaje);
		ResultSet resul = sentencia.executeQuery();
				
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
	}

	private static boolean primeraReservaViaje(int codigoViaje) throws SQLException {
		
		// Inicializar variable
		boolean esPrimerReserva = false;
		int reservasEncontradas = 0;
		
		// Realizar consulta
		String sql = "select count(*) from viajes where codviaje in (select codviaje from reservas) and codviaje = ?";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setInt(1, codigoViaje);
		ResultSet resul = sentencia.executeQuery();

		// Asignar datos
		resul.next();
		reservasEncontradas = resul.getInt(1);
		
		// Comprobar que sea correcto
		if (reservasEncontradas == 0) {
			esPrimerReserva = true;
		}
		
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		// Devolver el valor
		return esPrimerReserva;
		
	}

	private static boolean numPlazaCorrecto(int codigoViaje, int numeroPlaza) throws SQLException {
		
		// Inicializar variable
		boolean correcto = false;
		int plazasDisponibles = 0;
		
		// Realizar consulta
		String sql = "select plazasofertadas - plazasocupadas from viajes where codviaje = ?";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setInt(1, codigoViaje);
		ResultSet resul = sentencia.executeQuery();

		// Asignar datos
		resul.next();
		plazasDisponibles = resul.getInt(1);
		
		// Comprobar que sea correcto
		if (numeroPlaza > 0 && numeroPlaza < plazasDisponibles) {
			correcto = true;
		}
		
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		// Devolver el valor
		return correcto;
		
	}

	private static boolean existenPlazas(int codigoViaje) throws SQLException {

		// Inicializar variable
		boolean existe = false;
		int plazasDisponibles = 0;
		
		// Realizar consulta
		String sql = "select plazasofertadas - plazasocupadas from viajes where codviaje = ?";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setInt(1, codigoViaje);
		ResultSet resul = sentencia.executeQuery();

		// Asignar datos
		resul.next();
		plazasDisponibles = resul.getInt(1);
		
		// Comprobar que exista
		if (plazasDisponibles > 0) {
			existe = true;
		}
		
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		// Devolver el valor
		return existe;
		
	}

	private static boolean plazaOcupada(int numeroPlaza, int codigoViaje) throws SQLException {

		// Inicializar variable
		boolean existe = false;
		int numeroReservasEncontradas = 0;
		
		// Realizar consulta
		String sql = "select count(*) from reservas where numplaza = ? and codviaje = ?";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setInt(1, numeroPlaza);
		sentencia.setInt(2, codigoViaje);
		ResultSet resul = sentencia.executeQuery();

		// Asignar datos
		resul.next();
		numeroReservasEncontradas = resul.getInt(1);
		
		// Comprobar que exista
		if (numeroReservasEncontradas > 0) {
			existe = true;
		}
		
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		// Devolver el valor
		return existe;
			
	}

	private static boolean existeViaje(int codigoViaje) throws SQLException {

		// Inicializar variable
		boolean existe = false;
		int numeroViajesEncontrados = 0;
		
		// Realizar consulta
		String sql = "select count(*) from viajes where codviaje = ?";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setInt(1, codigoViaje);
		ResultSet resul = sentencia.executeQuery();

		// Asignar datos
		resul.next();
		numeroViajesEncontrados = resul.getInt(1);
		
		// Comprobar que exista
		if (numeroViajesEncontrados > 0) {
			existe = true;
		}
		
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		// Devolver el valor
		return existe;
		
	}

	private static boolean existeCliente(int codigoCliente) throws SQLException {
		
		// Inicializar variable
		boolean existe = false;
		int numeroClientesEncontrados = 0;
		
		// Realizar consulta
		String sql = "select count(*) from clientes where codclien = ?";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setInt(1, codigoCliente);
		ResultSet resul = sentencia.executeQuery();

		// Asignar datos
		resul.next();
		numeroClientesEncontrados = resul.getInt(1);
		
		// Comprobar que exista
		if (numeroClientesEncontrados > 0) {
			existe = true;
		}
		
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		// Devolver el valor
		return existe;
		
	}

	private static boolean existeReserva(int codigoReserva) throws SQLException {
		
		// Inicializar variable
		boolean existe = false;
		int numeroReservasEncontradas = 0;
		
		// Realizar consulta
		String sql = "select count(*) from reservas where codreserva = ?";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setInt(1, codigoReserva);
		ResultSet resul = sentencia.executeQuery();

		// Asignar datos
		resul.next();
		numeroReservasEncontradas = resul.getInt(1);
		
		// Comprobar que exista
		if (numeroReservasEncontradas > 0) {
			existe = true;
		}
		
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		// Devolver el valor
		return existe;
	}

	private static void crearEstadisticaCiudades() throws SQLException {
		
		// Comprobar si no existe
		if (!existeEstadisticaCiudades()) {
			
			// Crear la tabla
			crearTabla();
			
			// Rellenar los datos de la tabla
			rellenarDatos();
			
			// Mostrar mensaje de confirmación
			System.out.println("Tabla creado con éxito");
			
		} else {
			System.out.println("Error. Ya existe la tabla de ESTADISTICACIUDADES");
		}
		
		
	}

	private static void rellenarDatos() throws SQLException {
		
		// Realizar consulta
		String sql = "select * from ciudades";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		ResultSet resul = sentencia.executeQuery();

		// Recorrer todos los viajes que ha realizado el cliente
		while (resul.next()) {
			
			// Asignar los datos
			String ciudad = resul.getString(1);
			String nombrePais = obtenerNombrePais(ciudad);
			int numViajesDestino = obtenerNumViajesDestino(ciudad);
			int numViajesOrigen = obtenerNumViajesOrigen(ciudad);
			
			// Insertar los datos
			insertarDatos(ciudad, nombrePais, numViajesDestino, numViajesOrigen);
			
		}
		
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
	}

	private static void insertarDatos(String ciudad, String nombrePais, int numViajesDestino, int numViajesOrigen) throws SQLException {
		
		// Realizar consulta
		String sql = "insert into estadisticaciudades values (?, ?, ?, ?)";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setString(1, ciudad);
		sentencia.setString(2, nombrePais);
		sentencia.setInt(3, numViajesDestino);
		sentencia.setInt(4, numViajesOrigen);
		ResultSet resul = sentencia.executeQuery();
		
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
	}

	private static int obtenerNumViajesOrigen(String ciudad) throws SQLException {

		// Inicializar variables
		int numViajesOrigen = 0;
		
		// Realizar consulta
		String sql = "select count(*) from viajes where ciudadorigen = ?";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setString(1, ciudad);
		ResultSet resul = sentencia.executeQuery();

		// Asignar Datos
		resul.next();
		numViajesOrigen = resul.getInt(1);
		
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		
		// Devolver valor
		return numViajesOrigen;
		
	}

	private static int obtenerNumViajesDestino(String ciudad) throws SQLException {
		
		// Inicializar variables
		int numViajesDestino = 0;
		
		// Realizar consulta
		String sql = "select count(*) from viajes where ciudaddestino = ?";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setString(1, ciudad);
		ResultSet resul = sentencia.executeQuery();

		// Asignar Datos
		resul.next();
		numViajesDestino = resul.getInt(1);
		
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		
		// Devolver valor
		return numViajesDestino;
	}

	private static String obtenerNombrePais(String ciudad) throws SQLException {
		
		// Inicializar variables
		String nombrePais = "";
		
		// Realizar consulta
		String sql = "select nombre from paises where codpais = (select codpais from ciudades where ciudad = ?)";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setString(1, ciudad);
		ResultSet resul = sentencia.executeQuery();

		// Asignar Datos
		resul.next();
		nombrePais = resul.getString(1);
		
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		// Devolver el valor
		return nombrePais;
	}

	private static void crearTabla() throws SQLException {
		
		// Realizar consulta
		String sql = "create table estadisticaciudades (ciudad varchar2(20) not null, nombrepais varchar2(25), numviajesdestino number(5), numviajesprocedencia number(5), primary key(ciudad))";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		ResultSet resul = sentencia.executeQuery();
		
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		
	}

	private static boolean existeEstadisticaCiudades() throws SQLException {

		// Inicializar variables
		boolean existe = false;
		int numeroTablasEncontradas = 0;
		
		// Realizar consulta
		String sql = "select count(*) from all_tables where table_name = ? and owner = ?";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setString(1, "ESTADISTICACIUDADES");
		sentencia.setString(2, "RESERVAS");
		ResultSet resul = sentencia.executeQuery();

		// Asignar datos
		resul.next();
		numeroTablasEncontradas = resul.getInt(1);
		
		// Comprobar si se ha encontrado
		if (numeroTablasEncontradas > 0) {
			existe = true;
		}
		
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		
		// Devolver el valor booleano
		return existe;
	}

	private static void informacionReservasCliente() throws SQLException {
		
		// Inicializar variables
		int numeroReservasTotales = 0;
		double totalImporteClientes = 0;
		int numeroReservasMaximo = 0;
		String nombresMaxReservas = "";
		
		// Realizar consulta
		String sql = "select codclien, nombre from clientes";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		ResultSet resul = sentencia.executeQuery();

		// Mostrar la cabecera
		// Mostrar cabecera
		System.out.printf("%n%-15s %-30s %-15s %-15s %n", "COD CLIENTE", "NOMBRE", "NUM RESERVAS", "TOTAL IMPORTE");
		System.out.printf("%-15s %-30s %-15s %-15s %n", "--------------", "-----------------------------", "--------------", "--------------");
		
		
		
		// Recorrer todos los clientes
		while (resul.next()) {
			
			// Asignar datos
			int codigoCliente = resul.getInt(1);
			String nombre = resul.getString(2);
			int numeroReservas = numeroReservasCliente(codigoCliente);
			double totalImporte = calcularTotal(codigoCliente);
			
			// Imprimir los datos
			System.out.printf("%-15s %-30s %-15s %-15s %n", codigoCliente, nombre, numeroReservas, totalImporte);
			
			// Comprobar cuál es el número de reservas máximo
			if (numeroReservas > numeroReservasMaximo) {
				
			    numeroReservasMaximo = numeroReservas;
			    
			    // Reiniciar nombresMaxReservas con el nuevo cliente máximo
			    nombresMaxReservas = nombre + " "; 
			    
			} else if (numeroReservas == numeroReservasMaximo) {
				
				// Añadir el cliente con el mismo número máximo de reservas
			    nombresMaxReservas += nombre + " "; 
			    
			}
			
			// Asignar datos globales
			numeroReservasTotales += numeroReservas;
			totalImporteClientes += totalImporte;
			
		}
		
		System.out.printf("%-15s %-30s %-15s %-15s %n", "--------------", "-----------------------------", "--------------", "--------------");
		System.out.printf("%-15s %-30s %-15s %-15s %n", "Totales: ", "", numeroReservasTotales, totalImporteClientes);
		System.out.println("Cliente/ Clientes con más reservas: " + nombresMaxReservas);
		
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
	}

	private static double calcularTotal(int codigoCliente) throws SQLException {
		
		// Inicializar variable
		double precioTotal = 0;
		
		// Realizar consulta
		String sql = "select precio, ciudaddestino from viajes where codviaje in (select codviaje from reservas where codclien = ?)";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setInt(1, codigoCliente);
		ResultSet resul = sentencia.executeQuery();

		// Recorrer todos los viajes que ha realizado el cliente
		while (resul.next()) {
			
			// Asignar los datos
			double precioViaje = resul.getDouble(1);
			String pais = resul.getString(2);
			double precioTasa = precioTasaPais(pais);
			precioTotal += precioViaje + precioTasa;
			
		}
		
		// Cerrar consulta
		resul.close();
		sentencia.close();
				
		// Devolver el valor
		return precioTotal;
	}

	private static double precioTasaPais(String pais) throws SQLException {
		
		// Inicializar variable
		double precioTasa = 0;
		
		// Realizar consulta
		String sql = "select tasa from paises where codpais = (select codpais from ciudades where ciudad = ?)";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setString(1, pais);
		ResultSet resul = sentencia.executeQuery();

		// Asignar el dato
		resul.next();
		precioTasa = resul.getDouble(1);
		
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		// Devolver el valor
		return precioTasa;
	}

	private static int numeroReservasCliente(int codigoCliente) throws SQLException {
		
		// Inicializar variable
		int numeroReservas = 0;
		
		// Realizar consulta
		String sql = "select count(*) from reservas where codclien = ?";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setInt(1, codigoCliente);
		ResultSet resul = sentencia.executeQuery();

		// Paasar a la siguiente linea
		resul.next();
		
		// Asignar el número de reservas del cliente
		numeroReservas = resul.getInt(1);
	
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		// Devolver el valor
		return numeroReservas;
	}

	private static void menu() {
		
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("\t..................................................");
		System.out.println("\t.  1 Información de las Reservas");
		System.out.println("\t.  2 Crear Tabla EstadisticaCiudades");
		System.out.println("\t.  3 Insertar Reservas");
		System.out.println("\t.  0 SALIR");
		System.out.println("\t..................................................");
		System.out.println("---------------------------------------------------------------------------");
		System.out.print("TECLEA OPERACIÓN:");
		
	}
	
	
}
