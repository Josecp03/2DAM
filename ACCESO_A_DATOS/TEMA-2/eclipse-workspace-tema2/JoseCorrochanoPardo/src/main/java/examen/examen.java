package examen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
				

				break;

			case 2:
				
				break;

			case 3:

				break;
				
			case 4:
				prueba();
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
	
	private static void prueba() throws SQLException {
		
		// Realizar consulta
		String sql = "select count(*) from empleados";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		ResultSet resul = sentencia.executeQuery();

		resul.next();
		System.out.println(resul.getInt(1));
		
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
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
