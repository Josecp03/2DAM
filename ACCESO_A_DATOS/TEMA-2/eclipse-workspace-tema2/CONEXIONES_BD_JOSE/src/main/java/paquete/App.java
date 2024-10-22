package paquete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int op = 0;

		while (op != 6) {
			menu();
			op = sc.nextInt();

			switch (op) {
			case 1:
				System.out.println();
				leerSQLITE();
				System.out.println();
				break;
			case 2:
				System.out.println();
				leerDERBY();
				System.out.println();
				break;
			case 3:
				System.out.println();
				leerHSQLDB();
				System.out.println();
				break;
			case 4:
				System.out.println();
				leerMySQL();
				System.out.println();
				break;
			case 5:
				System.out.println();
				leerORACLE();
				System.out.println();
				break;
			case 6:
				System.out.println("\nHASTA PRONTO\n");
				break;
			default:
				System.out.println("\nPrueba con otra opción\n");
				break;
			}
		}
	}

	// Método para conectarse a ORACLE
	public static void leerORACLE() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "ejemplo",
					"dam");
			// Preparamos la consulta
			Statement sentencia = conexion.createStatement();
			String sql = "SELECT * FROM departamentos";
			ResultSet resul = sentencia.executeQuery(sql);

			// Recorremos el resultado para visualizar cada fila

			// Se hace un bucle mientras haya registros y se van mostrando

			while (resul.next()) {
				System.out.printf("%d, %s, %s %n", resul.getInt(1), resul.getString(2), resul.getString(3));
			}
			resul.close(); // Cerrar ResultSet
			sentencia.close(); // Cerrar Statement
			conexion.close(); // Cerrar conexión
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Método para contectarse a HSQLDB
	public static void leerHSQLDB() {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:hsqldb:file:C:\\Users\\Manuel\\Desktop\\GRADO SUPERIOR\\DAM 2\\AD\\BASES DE DATOS\\HSQLDB-2.5.1\\hsqldb\\data\\ejemplo\\ejemploBaseDatos");
			// Preparamos la consulta
			Statement sentencia = conexion.createStatement();
			String sql = "SELECT * FROM departamentos";
			ResultSet resul = sentencia.executeQuery(sql);

			// Recorremos el resultado para visualizar cada fila

			// Se hace un bucle mientras haya registros y se van mostrando

			while (resul.next()) {
				System.out.printf("%d, %s, %s %n", resul.getInt(1), resul.getString(2), resul.getString(3));
			}
			resul.close(); // Cerrar ResultSet
			sentencia.close(); // Cerrar Statement
			conexion.close(); // Cerrar conexión
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Método para conectarse a DERBY
	public static void leerDERBY() {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:derby:C:\\Users\\Manuel\\Desktop\\GRADO SUPERIOR\\DAM 2\\AD\\BASES DE DATOS\\DERBY\\db-derby-10.14.2.0-bin\\ejemploBaseDatos");
			// Preparamos la consulta
			Statement sentencia = conexion.createStatement();
			String sql = "SELECT * FROM departamentos";
			ResultSet resul = sentencia.executeQuery(sql);

			// Recorremos el resultado para visualizar cada fila

			// Se hace un bucle mientras haya registros y se van mostrando

			while (resul.next()) {
				System.out.printf("%d, %s, %s %n", resul.getInt(1), resul.getString(2), resul.getString(3));
			}
			resul.close(); // Cerrar ResultSet
			sentencia.close(); // Cerrar Statement
			conexion.close(); // Cerrar conexión
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Método para conectarse con SQLITE3
	public static void leerSQLITE() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexion = DriverManager.getConnection(
					"jdbc:sqlite:C:\\Users\\Manuel\\Desktop\\GRADO SUPERIOR\\DAM 2\\AD\\BASES DE DATOS\\SQLITE3\\ejemploBaseDatos.db");

			// Preparamos la consulta
			Statement sentencia = conexion.createStatement();
			String sql = "SELECT * FROM departamentos";
			ResultSet resul = sentencia.executeQuery(sql);

			// Recorremos el resultado para visualizar cada fila

			// Se hace un bucle mientras haya registros y se van mostrando

			while (resul.next()) {
				System.out.printf("%d, %s, %s %n", resul.getInt(1), resul.getString(2), resul.getString(3));
			}
			resul.close(); // Cerrar ResultSet
			sentencia.close(); // Cerrar Statement
			conexion.close(); // Cerrar conexión
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Método para leer la base de datos de mysql
	public static void leerMySQL() {
		try {
			// Cargar el driver
			Class.forName("com.mysql.jdbc.Driver");
			// Establecemos la conexion con la BD
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplobasedatos", "root", "");
			// Preparamos la consulta
			Statement sentencia = conexion.createStatement();
			String sql = "SELECT * FROM departamentos";
			ResultSet resul = sentencia.executeQuery(sql);

			// Recorremos el resultado para visualizar cada fila

			// Se hace un bucle mientras haya registros y se van mostrando

			while (resul.next()) {
				System.out.printf("%d, %s, %s %n", resul.getInt(1), resul.getString(2), resul.getString(3));
			}
			resul.close(); // Cerrar ResultSet
			sentencia.close(); // Cerrar Statement
			conexion.close(); // Cerrar conexión
		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Menu
	private static void menu() {
		System.out.println("--------------------------------------");
		System.out.println("MENU DE CONEXIONES");
		System.out.println("--------------------------------------");
		System.out.println("1º) Consultas en SQLITE");
		System.out.println("2º) Consultas en DERBY");
		System.out.println("3º) Consultas en HSQLDB");
		System.out.println("4º) Consultas en MySQL");
		System.out.println("5º) Consultas en ORACLE");
		System.out.println("6º) SALIR");
		System.out.println("--------------------------------------");
		System.out.print("Elija una: ");
	}
}