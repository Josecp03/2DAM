package paquete;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class App2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int op = 0;
		
		while(op!=6) {
			menu();
			op = sc.nextInt();
			
			switch(op) {
			case 1:
				System.out.println();
				leerSQLITEDATABASEMETADATA();
				System.out.println();
				break;
			case 2:
				System.out.println();
				leerDERBYDATABASEMETADATA();
				System.out.println();
				break;
			case 3:
				System.out.println();
				leerHSQLDBDATABASEMETADATA();
				System.out.println();
				break;
			case 4:
				System.out.println();
				leerSQLDATABASEMETADATA();
				System.out.println();
				break;
			case 5:
				break;
			case 6:
				break;
			default:
				System.out.println("\nOpción no disponible\n");
				break;
			}
		}
	}
	
	// Método para leer el hsqldb
	public static void leerHSQLDBDATABASEMETADATA() {
		try {
			// Inicializamos el driver
			Class.forName("org.hsqldb.jdbcDriver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:hsqldb:file:C:\\Users\\Manuel\\Desktop\\GRADO SUPERIOR\\DAM 2\\AD\\BASES DE DATOS\\HSQLDB-2.5.1\\hsqldb\\data\\ejemplo\\ejemploBaseDatos");
			// Creamos el dbmd y el resultado
			DatabaseMetaData dbmd = conexion.getMetaData();
			ResultSet result = null;
			
			// Obtenemos la información básica acerca de la base de datos
			String nombre = dbmd.getDatabaseProductName();
			String driver = dbmd.getDriverName();
			String url = dbmd.getURL();
			String usuario = dbmd.getUserName();
			
			System.out.println("INFORMACIÓN SOBRE LA BASE DE DATOS");
			System.out.println("===========================================");
			System.out.printf("Nombre: %s %n", nombre);
			System.out.printf("Driver: %s %n", driver);
			System.out.printf("URL: %s %n", url);
			System.out.printf("Usuario: %s %n", usuario);
			System.out.printf("Mayor versión: %s %n", dbmd.getDriverMajorVersion());
			
			System.out.println();
			
			ResultSet result2 = dbmd.getTables(null, null, null, null);
			while (result2.next()) {			    

			     String catalogo = result2.getString(1);//columna 1  

			     String esquema = result2.getString(2); //columna 2 

			     String tabla = result2.getString(3);   //columna 3 

			     String tipo = result2.getString(4);	//columna 4 

			  	     System.out.printf("%s - Catalogo: %s, Esquema: %s, Nombre: %s %n", tipo, catalogo, esquema, tabla); 

			      }
			conexion.close(); // Cerramos la conexion
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Método para leer el derby
	public static void leerDERBYDATABASEMETADATA() {
		try {
			// Inicializamos el driver
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:derby:C:\\Users\\Manuel\\Desktop\\GRADO SUPERIOR\\DAM 2\\AD\\BASES DE DATOS\\DERBY\\db-derby-10.14.2.0-bin\\ejemploBaseDatos");
			// Creamos el dbmd y el resultado
			DatabaseMetaData dbmd = conexion.getMetaData();
			ResultSet result = null;
			
			// Obtenemos la información básica acerca de la base de datos
			String nombre = dbmd.getDatabaseProductName();
			String driver = dbmd.getDriverName();
			String url = dbmd.getURL();
			String usuario = dbmd.getUserName();
			
			System.out.println("INFORMACIÓN SOBRE LA BASE DE DATOS");
			System.out.println("===========================================");
			System.out.printf("Nombre: %s %n", nombre);
			System.out.printf("Driver: %s %n", driver);
			System.out.printf("URL: %s %n", url);
			System.out.printf("Usuario: %s %n", usuario);
			System.out.printf("Mayor versión: %s %n", dbmd.getDriverMajorVersion());
			
			System.out.println();
			
			ResultSet result2 = dbmd.getTables(null, null, null, null);
			while (result2.next()) {			    

			     String catalogo = result2.getString(1);//columna 1  

			     String esquema = result2.getString(2); //columna 2 

			     String tabla = result2.getString(3);   //columna 3 

			     String tipo = result2.getString(4);	//columna 4 

			  	     System.out.printf("%s - Catalogo: %s, Esquema: %s, Nombre: %s %n", tipo, catalogo, esquema, tabla); 

			      }
			conexion.close(); // Cerramos la conexion
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Método para leer sqlite
	public static void leerSQLITEDATABASEMETADATA() {
		try {
			// Inicializamos el driver
			Class.forName("org.sqlite.JDBC");
			Connection conexion = DriverManager.getConnection(
					"jdbc:sqlite:C:\\Users\\Manuel\\Desktop\\GRADO SUPERIOR\\DAM 2\\AD\\BASES DE DATOS\\SQLITE3\\ejemploBaseDatos.db");
			// Creamos el dbmd y el resultado
			DatabaseMetaData dbmd = conexion.getMetaData();
			ResultSet result = null;
			
			// Obtenemos la información básica acerca de la base de datos
			String nombre = dbmd.getDatabaseProductName();
			String driver = dbmd.getDriverName();
			String url = dbmd.getURL();
			String usuario = dbmd.getUserName();
			
			System.out.println("INFORMACIÓN SOBRE LA BASE DE DATOS");
			System.out.println("===========================================");
			System.out.printf("Nombre: %s %n", nombre);
			System.out.printf("Driver: %s %n", driver);
			System.out.printf("URL: %s %n", url);
			System.out.printf("Usuario: %s %n", usuario);
			System.out.printf("Mayor versión: %s %n", dbmd.getDriverMajorVersion());
			
			System.out.println();
			
			ResultSet result2 = dbmd.getTables(null, null, null, null);
			while (result2.next()) {			    

			     String catalogo = result2.getString(1);//columna 1  

			     String esquema = result2.getString(2); //columna 2 

			     String tabla = result2.getString(3);   //columna 3 

			     String tipo = result2.getString(4);	//columna 4 

			  	     System.out.printf("%s - Catalogo: %s, Esquema: %s, Nombre: %s %n", tipo, catalogo, esquema, tabla); 

			      }
			conexion.close(); // Cerramos la conexion
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Método para mysql
	public static void leerSQLDATABASEMETADATA() {
		try {
			// Inicializamos el driver
			Class.forName("com.mysql.jdbc.Driver");
			// Establecemos la conexión con la BD
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplobasedatos", "root", "");
			// Creamos el dbmd y el resultado
			DatabaseMetaData dbmd = conexion.getMetaData();
			ResultSet result = null;
			
			// Obtenemos la información básica acerca de la base de datos
			String nombre = dbmd.getDatabaseProductName();
			String driver = dbmd.getDriverName();
			String url = dbmd.getURL();
			String usuario = dbmd.getUserName();
			
			System.out.println("INFORMACIÓN SOBRE LA BASE DE DATOS");
			System.out.println("===========================================");
			System.out.printf("Nombre: %s %n", nombre);
			System.out.printf("Driver: %s %n", driver);
			System.out.printf("URL: %s %n", url);
			System.out.printf("Usuario: %s %n", usuario);
			System.out.printf("Mayor versión: %s %n", dbmd.getDriverMajorVersion());
			
			System.out.println();
			
			ResultSet result2 = dbmd.getTables(null, null, null, null);
			while (result2.next()) {			    

			     String catalogo = result2.getString(1);//columna 1  

			     String esquema = result2.getString(2); //columna 2 

			     String tabla = result2.getString(3);   //columna 3 

			     String tipo = result2.getString(4);	//columna 4 

			  	     System.out.printf("%s - Catalogo: %s, Esquema: %s, Nombre: %s %n", tipo, catalogo, esquema, tabla); 

			      }
			conexion.close(); // Cerramos la conexion
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
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