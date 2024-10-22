package paquete;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class App2Buena {
	// metodo principal
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		int op = 0;

		while (op != 6) {
			menu();
			op = sc.nextInt();

			switch (op) {
			case 1:
				System.out.println();
				Class.forName("org.sqlite.JDBC");
				Connection conexionlite = DriverManager.getConnection(
						"jdbc:sqlite:C:\\Users\\Manuel\\Desktop\\GRADO SUPERIOR\\DAM 2\\AD\\BASES DE DATOS\\SQLITE3\\ejemploBaseDatos.db");
				info(conexionlite);
				System.out.println();
				break;
			case 2:
				System.out.println();
				Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
				Connection conexionderby = DriverManager.getConnection(
						"jdbc:derby:C:\\Users\\Manuel\\Desktop\\GRADO SUPERIOR\\DAM 2\\AD\\BASES DE DATOS\\DERBY\\db-derby-10.14.2.0-bin\\ejemploBaseDatos");
				info(conexionderby);
				System.out.println();
				break;
			case 3:
				System.out.println();
				Class.forName("org.hsqldb.jdbcDriver");
				Connection conexionhsql = DriverManager.getConnection(
						"jdbc:hsqldb:file:C:\\Users\\Manuel\\Desktop\\GRADO SUPERIOR\\DAM 2\\AD\\BASES DE DATOS\\HSQLDB-2.5.1\\hsqldb\\data\\ejemplo\\ejemploBaseDatos");
				info(conexionhsql);
				System.out.println();
				break;
			case 4:
				System.out.println();
				// Cargar el driver
				Class.forName("com.mysql.jdbc.Driver");
				// Establecemos la conexion con la BD
				Connection conexionmysql = DriverManager.getConnection("jdbc:mysql://localhost/ejemplobasedatos", "root", "");
				info(conexionmysql);
				System.out.println();
				break;
			case 5:
				System.out.println();
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection conexionoracle = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "ejemplo",
						"dam");
				info(conexionoracle);
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
	
	// Método para sacar info de la base de datos
	public static void info(Connection con) throws SQLException {
		DatabaseMetaData dbmd = con.getMetaData();
		ResultSet re = null;
		
		// Obtenemos lo datos de la base de datos
		String nombre = dbmd.getDatabaseProductName();
		String driver = dbmd.getDriverName();
		String url = dbmd.getURL();
		String usuario = dbmd.getUserName();
		
		// Los imprimimos por pantalla
		System.out.println("INFORMACIÓN SOBRE LA BASE DE DATOS");
		System.out.println("=================================================");
		System.out.println("Nombre: "+nombre);
		System.out.println("Driver: "+driver);
		System.out.println("URL: "+url);
		System.out.println("User: "+usuario);
		
		// Detectamos que tipo de base de datos es
		if(nombre.equalsIgnoreCase("Sqlite")) {
			re = dbmd.getTables(null, null, null, null);
			obtenerTablas(con, re);
		}else if(nombre.equalsIgnoreCase("Apache Derby")) {
			re = dbmd.getTables(null, "APP", null, null);
			obtenerTablas(con, re);
		}else if(nombre.equalsIgnoreCase("HSQL Database Engine")) {
			re = dbmd.getTables("PUBLIC", "PUBLIC", null, null);
			obtenerTablas(con, re);
		}else if(nombre.equalsIgnoreCase("MySQL")) {
			re = dbmd.getTables("ejemplobasedatos", null, null, null);
			obtenerTablas(con, re);
		}else if(nombre.equalsIgnoreCase("Oracle")) {
			re = dbmd.getTables(null, usuario, null, null);
			obtenerTablas(con, re);
		}
		
		// Cerramos ela conexión
		con.close();
	}
	
	// Método para obtener las tablas
	public static void obtenerTablas(Connection con, ResultSet re) throws SQLException {
		while(re.next()) {
			String catalogo = re.getString(1);
			String esquema = re.getString(2);
			String tabla = re.getString(3);
			String tipo = re.getString(4);
			System.out.printf("%s - Catalogo: %s, Esquema: %s, Nombre: %s %n", tipo, catalogo, esquema, tabla);
		}
	}
	
	// Menu
		private static void menu() {
			System.out.println("--------------------------------------");
			System.out.println("MENU DE CONEXIONES");
			System.out.println("--------------------------------------");
			System.out.println("1º) Ver datos de SQLITE");
			System.out.println("2º) Ver datos de DERBY");
			System.out.println("3º) Ver datos de HSQLDB");
			System.out.println("4º) Ver datos de MySQL");
			System.out.println("5º) Ver datos de ORACLE");
			System.out.println("6º) SALIR");
			System.out.println("--------------------------------------");
			System.out.print("Elija una: ");
		}
}