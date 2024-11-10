package examen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class examen {

	// Atributo de conexion para poder acceder a ello en cualquier sitio de la clase
	private static Connection conexion;

	// Bloque estático para inicializar la conexión al iniciar la clase
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "EXAMEN", "examen");
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
				
				String nombre = "José";
				String direccion = "Avd. Portgal 8";
				String telefono = "123456789";
				
				// Fecha Actual
				LocalDate fechaActual = LocalDate.now();				
		        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd-MM-YYY");
		        String fechaAlta = fechaActual.format(formateador);
		        
//		        String dateString = "2023-11-07"; // El formato debe coincidir con el String de entrada
//
//		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		        LocalDate date = LocalDate.parse(dateString, formatter);
//
//		        System.out.println("Fecha convertida: " + date);
		        
		        insertarCliente(nombre, direccion, telefono, fechaAlta);
		           
				break;

			case 2:
				
				int codigoProyecto = 5;
				listarProyecto(codigoProyecto);
				
				break;

			case 3:

				actualizarProyectos();
				
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
	
	private static void actualizarProyectos() throws SQLException {
		
		if (!existenColumnas()) {
			
			// Crear columnas
			crearColumnaNumEmpre();
			crearColumnaImporteEmp();
			crearColumnaNumAlum();
			crearColumnaGastoAlum();
			crearColumnaGastoRecur();
			
			// Realizar consulta
			String sql = "select * from proyectos";
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			ResultSet resul = sentencia.executeQuery();
				
			// Asignar datos
			while (resul.next()) {
				
				int codigoProyecto = resul.getInt(1);
				
				// NumEmpre
				int numEmpre = obtenerNumEmpreProyecto(codigoProyecto);
				actualizarNumEmpre(numEmpre, codigoProyecto);
				
				// No me ha dado tiempo a hacer el resto
				
			}
						
			// Cerrar consulta
			resul.close();
			sentencia.close();
			
			
			// Listar proyectos
			listarProyectos();
			
		} else {
			
			// Mostrar mensaje de error
			System.out.println("Error. Ya existen las columnas");
			
		} 
		
		
	}

	private static void actualizarNumEmpre(int numEmpre, int codigoProyecto) throws SQLException {
		
		// Realizar consulta
		String sql = "update proyectos set numempre = ? where codigoempleado = ?";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setInt(1, numEmpre);
		sentencia.setInt(2, codigoProyecto);
		ResultSet resul = sentencia.executeQuery();
			
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
	}

	private static int obtenerNumEmpreProyecto(int codigoProyecto) throws SQLException {
		
		// Inicializar variables
		int numEmpre = 0;
		
		// Realizar consulta
		String sql = "select count(*) from patrocina where codigoproyecto = ?";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setInt(1, codigoProyecto);
		ResultSet resul = sentencia.executeQuery();
			
		// Asignar datos
		resul.next();
		numEmpre = resul.getInt(1);

		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		// Devolver valor
		return numEmpre;
		
	}

	private static boolean existenColumnas() throws SQLException {

		// Inicializar variable
		boolean existe = false;
		int numColumnasEncontradas = 0;
		
		// Realizar consulta
		String sql = "SELECT COUNT(*) FROM all_tab_columns WHERE owner = 'EXAMEN' AND table_name = 'PROYECTOS' AND column_name IN ('numempre', 'IMPORTEEMP', 'NUMALUM', 'GASTOALUM', 'GASTORECUR')";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		ResultSet resul = sentencia.executeQuery();
			
		// Asignar datos
		resul.next();
		numColumnasEncontradas = resul.getInt(1);

		// Comprobar datos
		if (numColumnasEncontradas != 5) {
			existe = true;
		}
		
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		// Devolver valor
		return existe;
		
	}

	private static void listarProyectos() throws SQLException {
		
		// Realizar consulta
		String sql = "select * from proyectos";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		ResultSet resul = sentencia.executeQuery();
			
		System.out.printf("%-10s %-50s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %n", "COD", "NOMBRE", "FECHAINI", "FECHAFIN", "PRESUPUEST", "EXTRAAPORT", "NUMEMPRE", "IMPORTEMPRE", "NUMALUM", "GASTOALUM", "GASTORECUR");
		System.out.printf("%-10s %-50s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %n", "---------", "-------------------------------------------------", "-------------------", "-------------------", "-------------------", "-------------------", "-------------------", "-------------------", "-------------------", "-------------------", "-------------------");

		// Asignar datos
		while (resul.next()) {
			
			int codigoProyecto = resul.getInt(1);
			String nombre = resul.getString(2);
			String fechaIni = resul.getString(3).substring(0,10);
			String fechaFin = resul.getString(4).substring(0,10);
			float presupuesto = resul.getFloat(5);
			float extraaport = resul.getFloat(6);
			int numempre = resul.getInt(7);
			float importeemp = resul.getFloat(8);
			int numalumno = resul.getInt(9);
			float gastoalum = resul.getFloat(10);
			float gastoRecur = resul.getFloat(11);
			
			System.out.printf("%-10s %-50s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %20s %n", codigoProyecto, nombre, fechaIni, fechaFin, presupuesto , extraaport, numempre, importeemp, numalumno, gastoalum, gastoRecur);

			
		}
		
		System.out.printf("%-10s %-50s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %n", "---------", "-------------------------------------------------", "-------------------", "-------------------", "-------------------", "-------------------", "-------------------", "-------------------", "-------------------", "-------------------", "-------------------");

		
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
	}

	private static void crearColumnaGastoRecur() throws SQLException {
		
		// Realizar consulta
		String sql = "alter table proyectos add gastorecur number(10,2)";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		ResultSet resul = sentencia.executeQuery();
			
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
	}

	private static void crearColumnaGastoAlum() throws SQLException {
		
		// Realizar consulta
		String sql = "alter table proyectos add gastoalum number(10,2)";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		ResultSet resul = sentencia.executeQuery();
			
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
	}

	private static void crearColumnaNumAlum() throws SQLException {
		
		// Realizar consulta
		String sql = "alter table proyectos add numalum number(10,2)";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		ResultSet resul = sentencia.executeQuery();
			
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
	}

	private static void crearColumnaImporteEmp() throws SQLException {
		
		// Realizar consulta
		String sql = "alter table proyectos add importeemp number(10,2)";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		ResultSet resul = sentencia.executeQuery();
			
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
	}

	private static void crearColumnaNumEmpre() throws SQLException {
		
		// Realizar consulta
		String sql = "alter table proyectos add numempre number(10,2)";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		ResultSet resul = sentencia.executeQuery();
			
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
	}

	private static void listarProyecto(int codigoProyecto) throws SQLException {
				
		float extraAportacion = mostrarInformacionProyecto(codigoProyecto);
		mostrarInformacionEntidades(codigoProyecto);
		mostrarInformacionEstudiantes(codigoProyecto, extraAportacion);
		
	}

	private static void mostrarInformacionEstudiantes(int codigoProyecto, float extraAportacion) throws SQLException {
		
		if (!proyectoTieneEstudiantes(codigoProyecto)) {
			
			System.out.println("NINGUNA ESTUDIANTE PERTENECE A ESTE PROYECTO");
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			
		} else {
			
			// Realizar consulta
			String sql = "select codestudiante, nombre, direccion from estudiantes where codestudiante in (select codestudiante from participa where codigoproyecto = ?)"
					+ "";
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			sentencia.setInt(1, codigoProyecto);
			ResultSet resul = sentencia.executeQuery();
				
			System.out.println("\nLISTA DE ESTUDINATES QUE PATROCINAN EL PROYECTO");
			System.out.printf("%-10s %-30s %-50s %-10s %-20s %-10s %-10s %n", "Cód", "Nombre", "Descripción", "CodPar", "Tipo aportación", "NumApt", "TotAport");
			System.out.printf("%-10s %-30s %-50s %-10s %-20s %-10s %-10s %n", "=========", "=============================", "=============================", "=========", "===================", "=========", "=========");
			
			int numAptTotal = 0;
			float totAportTotal = 0;
			
			// Recorrer todos los datos
			while (resul.next()) {
			
				int codigoEstudiante = resul.getInt(1);
				String nombre = resul.getString(2);
				String direccion = resul.getString(3);
				int codPar = obtenerCodigoParticipacionEstudiante(codigoEstudiante, codigoProyecto);
				String tipoAportacion = obtenerTipoAportacionEstudiante(codigoEstudiante, codigoProyecto);
				int numeroAportacionesEstudiante = obtenerNumeroAportacionesEstudiante(codigoEstudiante);
				float totAport = numeroAportacionesEstudiante * extraAportacion;
				
				System.out.printf("%-10s %-30s %-50s %-10s %-20s %-10s %-10s %n", codigoEstudiante, nombre, direccion, codPar, tipoAportacion, numeroAportacionesEstudiante, totAport);

				numAptTotal += numeroAportacionesEstudiante;
				totAportTotal += totAport;
				
			}
			
			System.out.printf("%-10s %-30s %-50s %-10s %-20s %-10s %-10s %n", "=========", "=============================", "=============================", "=========", "===================", "=========", "=========");
			System.out.printf("%-10s %-30s %-50s %-10s %-20s %-10s %-10s %n", "TOTALES", "", "", "", "",numAptTotal ,totAportTotal );
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

			// Cerrar consulta
			resul.close();
			sentencia.close();
			
		}

	}

	private static boolean proyectoTieneEstudiantes(int codigoProyecto) throws SQLException {

		// Inicializar variables
		boolean tiene = false;
		int numeroProyectosEncontrados = 0;
		
		// Realizar consulta
		String sql = "select count(*) from participa where codigoproyecto = ?";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setInt(1, codigoProyecto);
		ResultSet resul = sentencia.executeQuery();

		// Asignar datos
		resul.next();
		numeroProyectosEncontrados = resul.getInt(1);
		
		// Comprobar los datos
		if (numeroProyectosEncontrados > 0) {
			tiene = true;
		}
		
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		// Devolver valor booleano
		return tiene;
		
		
	}

	private static int obtenerNumeroAportacionesEstudiante(int codigoEstudiante) throws SQLException {

		// Inicializar variables
		int numeroAportaciones = 0;
		
		// Realizar consulta
		String sql = "select count(*) from participa where codestudiante = ?";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setInt(1, codigoEstudiante);
		ResultSet resul = sentencia.executeQuery();
			
		// Asignar datos
		resul.next();
		numeroAportaciones = resul.getInt(1);

		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		// Devolver valor
		return numeroAportaciones;
		
	}

	private static String obtenerTipoAportacionEstudiante(int codigoEstudiante, int codigoProyecto) throws SQLException {

		// Inicializar variables
		String tipo = "";
		
		// Realizar consulta
		String sql = "select tipoparticipacion from participa where codestudiante = ? and codigoproyecto = ?";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setInt(1, codigoEstudiante);
		sentencia.setInt(2, codigoProyecto);
		ResultSet resul = sentencia.executeQuery();
			
		// Asignar datos
		resul.next();
		tipo = resul.getString(1);

		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		// Devolver valor
		return tipo;
		
	}

	private static int obtenerCodigoParticipacionEstudiante(int codigoEstudiante, int codigoProyecto) throws SQLException {

		// Inicializar variables
		int codPar = 0;
		
		// Realizar consulta
		String sql = "select codparticipacion from participa where codestudiante = ? and codigoproyecto = ?";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setInt(1, codigoEstudiante);
		sentencia.setInt(2, codigoProyecto);
		ResultSet resul = sentencia.executeQuery();
			
		// Asignar datos
		resul.next();
		codPar = resul.getInt(1);

		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		// Devolver valor
		return codPar;
		
	}

	private static void mostrarInformacionEntidades(int codigoProyecto) throws SQLException {
		
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		
		if (!proyectoTieneEntidades(codigoProyecto)) {
			System.out.println("NINGUNA ENTIDAD PATROCINA ESTE PROYECTO");
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		} else {
			
			// Realizar consulta
			String sql = "select codentidad, descripcion from entidades where codentidad in (select codentidad from patrocina where codigoproyecto = ?)";
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			sentencia.setInt(1, codigoProyecto);
			ResultSet resul = sentencia.executeQuery();
				
			System.out.println("\nLISTA DE ENTIDADES QUE PATROCINAN EL PROYECTO");
			System.out.printf("%-10s %-30s %-30s %-30s %n", "Código", "Descripción", "Importe aportación", "Fecha aportación");
			System.out.printf("%-10s %-30s %-30s %-30s %n", "=========", "=============================", "=============================", "=============================");
			
			// Inicializar variables
			float totalAportaciones = 0;
			float presupuestoProyecto = obtenerPresupuestoProyecto(codigoProyecto);
			
			// Recorrer todos los datos
			while (resul.next()) {
			
				int codigoEntidad = resul.getInt(1);
				String descripcion = resul.getString(2);
				float importeAportacion = obtenerImporteAportacionEntidad(codigoEntidad);
				String fechaAportacion = obtenerFechaAportacionEntidad(codigoEntidad);
				
				totalAportaciones += importeAportacion;
				
				System.out.printf("%9s %-30s %30s %30s %n", codigoEntidad, descripcion, importeAportacion, fechaAportacion);
				
			}
			
			float presupuestoTotal = presupuestoProyecto + totalAportaciones;
			
			System.out.printf("%-10s %-30s %-30s %-30s %n", "=========", "=============================", "=============================", "=============================");
			System.out.printf("%-10s %-30s %20s %-30s %n", "TOTAL APORTACIONES:", "", totalAportaciones, "");
			System.out.printf("%-10s %-30s %21s %-30s %n", "PRESUPUESTO TOTAL:", "", presupuestoTotal, "");
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			
			// Cerrar consulta
			resul.close();
			sentencia.close();
			
		}
		
		

		
	}

	private static boolean proyectoTieneEntidades(int codigoProyecto) throws SQLException {

		// Inicializar variables
		boolean tiene = false;
		int numeroProyectosEncontrados = 0;
		
		// Realizar consulta
		String sql = "select count(*) from patrocina where codigoproyecto = ?";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setInt(1, codigoProyecto);
		ResultSet resul = sentencia.executeQuery();

		// Asignar datos
		resul.next();
		numeroProyectosEncontrados = resul.getInt(1);
		
		// Comprobar los datos
		if (numeroProyectosEncontrados > 0) {
			tiene = true;
		}
		
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		// Devolver valor booleano
		return tiene;
		
	}

	private static float obtenerPresupuestoProyecto(int codigoProyecto) throws SQLException {

		// Inicializar variables
		int presupuesto = 0;
		
		// Realizar consulta
		String sql = "select presupuesto from proyectos where codigoproyecto = ?";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setInt(1, codigoProyecto);
		ResultSet resul = sentencia.executeQuery();
			
		// Asignar datos
		resul.next();
		presupuesto = resul.getInt(1);

		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		// Devolver valor
		return presupuesto;
		
		
	}

	private static String obtenerFechaAportacionEntidad(int codigoEntidad) throws SQLException {

		// Inicializar variables
		String fecha = "";
		
		// Realizar consulta
		String sql = "select fechaaportacion from patrocina where codentidad = ?";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setInt(1, codigoEntidad);
		ResultSet resul = sentencia.executeQuery();
			
		// Asignar datos
		resul.next();
		fecha = resul.getString(1);

		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		// Devolver valor
		return fecha.substring(0, 10);
		
	}

	private static float obtenerImporteAportacionEntidad(int codigoEntidad) throws SQLException {

		// Inicializar variables
		float importe = 0;
		
		// Realizar consulta
		String sql = "select importeaportacion from patrocina where codentidad = ?";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setInt(1, codigoEntidad);
		ResultSet resul = sentencia.executeQuery();
			
		// Asignar datos
		resul.next();
		importe = resul.getFloat(1);

		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		// Devolver valor
		return importe;
		
	}

	private static float mostrarInformacionProyecto(int codigoProyecto) throws SQLException {
		
		// Realizar consulta
		String sql = "select * from proyectos where codigoproyecto = ?";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setInt(1, codigoProyecto);
		ResultSet resul = sentencia.executeQuery();
			
		// Asignar datos
		resul.next();
		String nombre = resul.getString(2);
		String fechaInicio = resul.getString(3).substring(0,10);
		String fechaFin = resul.getString(4).substring(0,10);
		float presupuesto = resul.getFloat(5);
		float extraexportacion = resul.getFloat(6);
		
		System.out.println("\nCOD-PROYECTO: " + codigoProyecto + "   NOMBRE: " + nombre);
		System.out.println("FECHA INICIO: " + fechaInicio + ", FECHA FIN: " + fechaFin);
		System.out.println("PRESUPUESTO: " + presupuesto + ", EXTRAAPORTACIÓN: " + extraexportacion);
		
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		return extraexportacion;
	}

	private static void insertarCliente(String nombre, String direccion, String telefono, String fechaAlta)  {
		
		try {
			
			if (!nombreRepetido(nombre)) {
				
				int codigoEstudiante = obtenerCodigoEstudianteMayor();
				
				if (nombre.equals("") || direccion.equals("") || telefono.equals("") || fechaAlta.equals("")) {
					
					// Mostrar mensaje de error
					System.out.println("Los datos no pueden estar vacíos, comprueba datos.");
					
				} else {
					
					insertarDatosCliente(codigoEstudiante, nombre, direccion, telefono, fechaAlta);
					System.out.println("Estudiante insertado correctamente con el código " + codigoEstudiante);
					
				}
				
				
			} else {
				
				// Mostrar mensaje de error
				System.out.println("Ya existe un estudiante con ese nombre, no se ha podido insertar.");
				
			}
			
		} catch (SQLException e) {
			
			System.out.println("Error inseperado");
		}
		
		
	}

	private static void insertarDatosCliente(int codigoEstudiante, String nombre, String direccion, String telefono,
			String fechaAlta) throws SQLException {
		
		// Realizar consulta
		String sql = "insert into estudiantes values (?,?,?,?,?)";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setInt(1, codigoEstudiante);
		sentencia.setString(2, nombre);
		sentencia.setString(3, direccion);
		sentencia.setString(4, telefono);
		sentencia.setString(5, fechaAlta);
		ResultSet resul = sentencia.executeQuery();
				
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
	}

	private static int obtenerCodigoEstudianteMayor() throws SQLException {

		// Inicializar variables
		int codigoNuevo = 0;
		
		// Realizar consulta
		String sql = "select max(codestudiante) + 1 from estudiantes";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		ResultSet resul = sentencia.executeQuery();

		// Asignar datos
		resul.next();
		codigoNuevo = resul.getInt(1);
				
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		// Devolver valor booleano
		return codigoNuevo;
		
	}

	private static boolean nombreRepetido(String nombre) throws SQLException {

		// Inicializar variables
		boolean existe = false;
		int numeroNombresEnconttrados = 0;
		
		// Realizar consulta
		String sql = "select count(*) from estudiantes where nombre = ?";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setString(1, nombre.toUpperCase());
		ResultSet resul = sentencia.executeQuery();

		// Asignar datos
		resul.next();
		numeroNombresEnconttrados = resul.getInt(1);
		
		// Comprobar los datos
		if (numeroNombresEnconttrados > 0) {
			existe = true;
		}
		
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		
		// Devolver valor booleano
		return existe;
		
	}

	private static void menu() {
		
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("\t..................................................");
		System.out.println("\t.  Ejercicio 1");
		System.out.println("\t.  Ejercicio 2");
		System.out.println("\t.  Ejercicio 3");
		System.out.println("\t.  0 SALIR");
		System.out.println("\t..................................................");
		System.out.println("---------------------------------------------------------------------------");
		System.out.print("TECLEA OPERACIÓN:");
		
	}
	
	
	
}
