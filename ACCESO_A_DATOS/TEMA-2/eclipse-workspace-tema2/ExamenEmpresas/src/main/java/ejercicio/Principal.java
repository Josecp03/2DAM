package ejercicio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Principal {

	// Atributo de conexion para poder acceder a ello en cualquier sitio de la clase
	private static Connection conexion;

	// Bloque estático para inicializar la conexión al iniciar la clase
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "empresas", "dam");
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
				
				int codigoEmpresa = 2;
				verEmpresa(codigoEmpresa);
				break;

			case 2:
				
				break;

			case 3:

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
	
	private static void verEmpresa(int codigoEmpresa) throws SQLException {
		
		if (existeEmpresa(codigoEmpresa)) {
			
			visualizarDatosEmpresa(codigoEmpresa);
			
		} else {
			
			System.out.println("Error. No existe la empresa");
			
		}
		
		
	}

	private static void visualizarDatosEmpresa(int codigoEmpresa) throws SQLException {
		
		// Realizar consulta
		String sql = "select nombre, direccion from empresas where codempre = ?";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setInt(1, codigoEmpresa);
		ResultSet resul = sentencia.executeQuery();

		// Asignar datos
		resul.next();
		String nombre = resul.getString(1);
		String direccion = resul.getString(2);
		int numeroDepartamentos = calcularNumeroDepartamentosEmpresa(codigoEmpresa);
		
		// Imprimir cabacera
		System.out.println("COD-EMPRESA: " + codigoEmpresa + "\t NOMBRE: " + nombre);
		System.out.println("DIRECCIÓN: " + direccion + " \t Número de departamentos: " + numeroDepartamentos);
		System.out.println("---------------------------------------------------------------------------------------------------------------------------");
		
		// Visualizar los datos de la empresa
		visualizarDepartamentosEmpresa(codigoEmpresa);
		
		// Cerrar consulta
		resul.close();
		sentencia.close();
				
	}

	private static void visualizarDepartamentosEmpresa(int codigoEmpresa) throws SQLException {
		
		// Realizar consulta
		String sql = "select coddepart, nombre, localidad from departamentos where codempre = ?";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setInt(1, codigoEmpresa);
		ResultSet resul = sentencia.executeQuery();

		// Recorrer todos los departamentos
		while (resul.next()) {
			
			// Asignar datos
			int codigoDepartamento = resul.getInt(1);
			String nombre = resul.getString(2);
			String localidad = resul.getString(2);
			
			System.out.println("\t COD-DEPARTAMENTO: " + codigoDepartamento + " \t NOMBRE: " + nombre + "\t LOCALIDAD: " + localidad);
			System.out.printf("\t %-15s %-30s %-30s %-30s %n", "COD-EMPLEADO", "NOMBRE", "OFICIO", "NOMBRE ENCARGADO");
			System.out.printf("\t %-15s %-30s %-30s %-30s %n", "--------------", "-----------------------------", "-----------------------------", "-----------------------------");
			visualizarDatosEmpleadosDepartamento(codigoDepartamento);
			System.out.printf("\t %-15s %-30s %-30s %-30s %n", "--------------", "-----------------------------", "-----------------------------", "-----------------------------");
			
			// Obtener datos generales del departamento
			int numeroEmpleadosDepartamento = obtenerNumeroEmpleadosDepartamento(codigoDepartamento);
			String nombreJefeDepartamento = obtenerNombreJefeDepartamento(codigoDepartamento);
			System.out.println("\t Número de empleados por departamento: " + numeroEmpleadosDepartamento);
			System.out.println("\t Nombre del jefe de departamento: " + nombreJefeDepartamento + "\n");
		}
		
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		
	}

	private static String obtenerNombreJefeDepartamento(int codigoDepartamento) throws SQLException {

		// Inicializar variables
		String nombre = "";
		
		// Realizar consulta
		String sql = "select nombre from empleados where codemple = (select codjefedepartamento from departamentos where coddepart = ?)";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setInt(1, codigoDepartamento);
		ResultSet resul = sentencia.executeQuery();

		// Asignar Datos
		resul.next();
		nombre = resul.getString(1);
		
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		// Devolver valor
		return nombre;
		
	}

	private static int obtenerNumeroEmpleadosDepartamento(int codigoDepartamento) throws SQLException {

		// Inicializar variables
		int numEmpleados = 0;
		
		// Realizar consulta
		String sql = "select count(*) from empleados where coddepart = ?";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setInt(1, codigoDepartamento);
		ResultSet resul = sentencia.executeQuery();

		// Asignar Datos
		resul.next();
		numEmpleados = resul.getInt(1);
		
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		// Devolver valor
		return numEmpleados;
		
	}

	private static void visualizarDatosEmpleadosDepartamento(int codigoDepartamento) throws SQLException {
		
		// Realizar consulta
		String sql = "select codemple, nombre, codencargado, codoficio from empleados where coddepart = ?";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setInt(1, codigoDepartamento);
		ResultSet resul = sentencia.executeQuery();

		// Recorrer todos los departamentos
		while (resul.next()) {
			
			// Asignar datos
			int codigoEmpleado = resul.getInt(1);
			String nombre = resul.getString(2);
			String nombreEncargado = obtenerEncargado(resul.getInt(3), nombre);
		
			
			String oficio = obtenerOficio(resul.getInt(4));
			
			// Imprimir los datos
			System.out.printf("\t %-15s %-30s %-30s %-30s %n", codigoEmpleado, nombre, oficio, nombreEncargado);

		}
		
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		
	}

	private static String obtenerEncargado(int codigoEncargado, String nombreP) throws SQLException {

		// Inicializar variables
		String nombre = "";
		
		// Realizar consulta
		String sql = "select nombre from empleados where codemple = ?";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setInt(1, codigoEncargado);
		ResultSet resul = sentencia.executeQuery();

		// Asignar Datos
		if (resul.next()) {
			nombre = resul.getString(1);
		} else {
			nombre = nombreP;
		}
		
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		// Devolver valor
		return nombre;
		
	}

	private static String obtenerOficio(int codigoOficio) throws SQLException {

		// Inicializar variables
		String oficio = "";
		
		// Realizar consulta
		String sql = "select nombre from oficios where codoficio = ?";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setInt(1, codigoOficio);
		ResultSet resul = sentencia.executeQuery();

		// Asignar Datos
		resul.next();
		oficio = resul.getString(1);
		
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		// Devolver valor
		return oficio;
			
	}

	private static int calcularNumeroDepartamentosEmpresa(int codigoEmpresa) throws SQLException {

		// Inicializar variables
		int numDepartamentos = 0;
		
		// Realizar consulta
		String sql = "select count(*) from departamentos where codempre = ?";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setInt(1, codigoEmpresa);
		ResultSet resul = sentencia.executeQuery();

		// Asignar Datos
		resul.next();
		numDepartamentos = resul.getInt(1);
		
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		// Devolver valor
		return numDepartamentos;
		
	}

	private static boolean existeEmpresa(int codigoEmpresa) throws SQLException {

		// Inicializar variable
		boolean existe = false;
		int numeroEmpresasEncontradas = 0;
		
		// Realizar consulta
		String sql = "select count(*) from empresas where codempre = ?";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setInt(1, codigoEmpresa);
		ResultSet resul = sentencia.executeQuery();

		// Asignar datos
		resul.next();
		numeroEmpresasEncontradas = resul.getInt(1);
		
		// Comprobar que exista
		if (numeroEmpresasEncontradas > 0) {
			existe = true;
		}
		
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		// Devolver el valor
		return existe;
		
	}

	private static void menu() {
		
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("\t..................................................");
		System.out.println("\t.  1 Información de las Empresas");
		System.out.println("\t.  2 Crear Tabla EMPRESASSINDEPARTAMENTOS");
		System.out.println("\t.  3 Añadir columna NUMEMPLE");
		System.out.println("\t.  0 SALIR");
		System.out.println("\t..................................................");
		System.out.println("---------------------------------------------------------------------------");
		System.out.print("TECLEA OPERACIÓN:");
		
	}
	
	
}
