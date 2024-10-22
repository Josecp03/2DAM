package paquete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App4 {
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
				pruebaExecute(conexionlite);
				//insertarRegistro(conexionlite, args[0], args[1], args[2]);
				System.out.println();
				break;
			case 2:
				System.out.println();
				Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
				Connection conexionderby = DriverManager.getConnection(
						"jdbc:derby:C:\\Users\\Manuel\\Desktop\\GRADO SUPERIOR\\DAM 2\\AD\\BASES DE DATOS\\DERBY\\db-derby-10.14.2.0-bin\\ejemploBaseDatos");
				pruebaExecute(conexionderby);
				System.out.println();
				break;
			case 3:
				System.out.println();
				Class.forName("org.hsqldb.jdbcDriver");
				Connection conexionhsql = DriverManager.getConnection(
						"jdbc:hsqldb:file:C:\\Users\\Manuel\\Desktop\\GRADO SUPERIOR\\DAM 2\\AD\\BASES DE DATOS\\HSQLDB-2.5.1\\hsqldb\\data\\ejemplo\\ejemploBaseDatos");
				pruebaExecute(conexionhsql);
				System.out.println();
				break;
			case 4:
				System.out.println();
				// Inicializamos el driver
				Class.forName("com.mysql.jdbc.Driver");
				// Establecemos la conexión con la BD
				Connection conexionsql = DriverManager.getConnection("jdbc:mysql://localhost/ejemplobasedatos", "root", "");
				pruebaExecute(conexionsql);
				System.out.println();
				break;
			case 5:
				System.out.println();
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection conexionoracle = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "ejemplo",
						"dam");
				pruebaExecute(conexionoracle);
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
	
	// Método para execute
	public static void pruebaExecute(Connection con) throws SQLException {
		String sql = "SELECT * FROM departamentos";
		Statement sentencia = con.createStatement();
		boolean valor = sentencia.execute(sql);
		
		if(valor) {
			ResultSet rs = sentencia.getResultSet();
			while(rs.next()) {
				System.out.printf("%d %s %s %n", rs.getInt(1), rs.getString(2), rs.getString(3));
			}
			rs.close();
		}else {
			int f = sentencia.getUpdateCount();
			System.out.printf("Filas afectadas: %d %n", f);
		}
		sentencia.close();
		con.close();
	}
	
	// Método para insertar un nuevo registro
	public static void insertarRegistro(Connection con, String dep, String dnombre, String loc) throws SQLException {
		// Cosntruimos el insert
		String sql = String.format("INSERT INTO departamentos VALUES (%s, '%s', '%s')", dep, dnombre, loc);
		System.out.println(sql);
		
		Statement sentencia = con.createStatement();
		int filas = sentencia.executeUpdate(sql);
		System.out.printf("Filas afectadas: %d %n", filas);
		
		sentencia.close();
		con.close();
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