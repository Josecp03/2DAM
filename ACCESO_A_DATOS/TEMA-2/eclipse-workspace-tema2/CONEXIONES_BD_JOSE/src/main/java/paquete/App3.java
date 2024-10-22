package paquete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.jdbc.ResultSetMetaData;

public class App3 {
	// Método principal
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		int op = 0;
		
		while(op!=6) {
			menu();
			op = sc.nextInt();
			
			switch(op) {
			case 1:
				System.out.println();
				Class.forName("org.sqlite.JDBC");
				Connection conexionlite = DriverManager.getConnection(
						"jdbc:sqlite:C:\\Users\\Manuel\\Desktop\\GRADO SUPERIOR\\DAM 2\\AD\\BASES DE DATOS\\SQLITE3\\ejemploBaseDatos.db");
				ejemploMySql(conexionlite);
				System.out.println();
				break;
			case 2:
				System.out.println();
				Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
				Connection conexionderby = DriverManager.getConnection(
						"jdbc:derby:C:\\Users\\Manuel\\Desktop\\GRADO SUPERIOR\\DAM 2\\AD\\BASES DE DATOS\\DERBY\\db-derby-10.14.2.0-bin\\ejemploBaseDatos");
				ejemploMySql(conexionderby);
				System.out.println();
				break;
			case 3:
				System.out.println();
				Class.forName("org.hsqldb.jdbcDriver");
				Connection conexionhsql = DriverManager.getConnection(
						"jdbc:hsqldb:file:C:\\Users\\Manuel\\Desktop\\GRADO SUPERIOR\\DAM 2\\AD\\BASES DE DATOS\\HSQLDB-2.5.1\\hsqldb\\data\\ejemplo\\ejemploBaseDatos");
				ejemploMySql(conexionhsql);
				System.out.println();
				break;
			case 4:
				System.out.println();
				// Inicializamos el driver
				Class.forName("com.mysql.jdbc.Driver");
				// Establecemos la conexión con la BD
				Connection conexionsql = DriverManager.getConnection("jdbc:mysql://localhost/ejemplobasedatos", "root", "");
				ejemploMySql(conexionsql);
				System.out.println();
				break;
			case 5:
				System.out.println();
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection conexionoracle = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "ejemplo",
						"dam");
				ejemploMySql(conexionoracle);
				System.out.println();
				break;
			case 6:
				System.out.println("\nHASTA PRONTO\n");
				break;
			default:
				System.out.println("\nOpción no disponible\n");
				break;
			}
		}
	}
	
	public static void ejemploMySql(Connection conexion) {
		try {
			Statement sentencia = conexion.createStatement();
			ResultSet rs = sentencia.executeQuery("SELECT * FROM departamentos");
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
			
			int nColunmas = rsmd.getColumnCount();
			String nula;
			System.out.printf("Número de columnas recuperadas: %d%n", nColunmas);
			for(int i = 1;i<nColunmas;i++) {
				System.out.printf("Columa %d: %n ",i);
				System.out.printf("Nombre: %s %n Tipo: %s %n", rsmd.getColumnName(i), rsmd.getColumnTypeName(i));
				
				if(rsmd.isNullable(i) == 0) {
					nula = "NO";
				}else {
					nula = "SI";
				}
				
				System.out.printf("Puede ser nula? %s %n ", nula);
				System.out.printf("Maximo ancho de la columna: %d %n ", rsmd.getColumnDisplaySize(i));
			}
			sentencia.close();
			rs.close();
			conexion.close();
		} catch (SQLException e) {
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