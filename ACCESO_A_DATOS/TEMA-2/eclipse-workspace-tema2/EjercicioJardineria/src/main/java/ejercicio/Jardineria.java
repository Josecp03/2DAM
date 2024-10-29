package ejercicio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Jardineria {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int op = -1;

		while (op != 0) {
			menu();
			op = sc.nextInt();

			switch (op) {
			case 1:

				// 1. Caso correcto
				String nombre = "José";
				String primerApellido = "Corrochano";
				String segundoApellido = "Pardo";
				String extension = "2840";
				String email = "jose@jardineria.es";
				String codigoOficina = "TAL-ES";
				int codigoJefe = 2;
				String puesto = "Director General Talavera";

//				// 2. Mal jefe
//				String nombre2 = "José";
//				String primerApellido2 = "Corrochano";
//				String segundoApellido2 = "Pardo";
//				String extension2 = "2840";
//				String email2 = "jose@jardineria.es";
//				String codigoOficina2 = "TAL-ES";
//				int codigoJefe2 = 4;
//				String puesto2 = "Director General Talavera";
//
//				// 3. Mal oficina
//				String nombre3 = "José";
//				String primerApellido3 = "Corrochano";
//				String segundoApellido3 = "Pardo";
//				String extension3 = "2840";
//				String email3 = "jose@jardineria.es";
//				String codigoOficina3 = "TAL-TAL";
//				int codigoJefe3 = 3;
//				String puesto3 = "Director General Talavera";

				insertarEmpleado(nombre, primerApellido, segundoApellido, extension, email, codigoOficina, codigoJefe,
						puesto);
				// insertarEmpleado(nombre2, primerApellido2, segundoApellido2, extension2,
				//		email2, codigoOficina2, codigoJefe2, puesto2);
				// insertarEmpleado(nombre3, primerApellido3, segundoApellido3, extension3,
				//		email3, codigoOficina3, codigoJefe3, puesto3);
				break;
			case 2:

				break;
			case 3:

				break;
			case 4:

				break;
			case 5:

				break;
			case 6:

				break;
			case 7:

				break;
			case 8:

				break;
			case 9:
				probarConexion();
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

	private static void insertarEmpleado(String nombre, String primerApellido, String segundoApellido, String extension,
			String email, String codigoOficina, int codigoJefe, String puesto) {

		try {

			// Realizamos la conexión a Oracle
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "jardineriaad",
					"dam");

			// Primera consulta para saber el codigo del nuevo empleado
			Statement sentencia = conexion.createStatement();
			String sql = "SELECT MAX(CODIGOEMPLEADO)+1 FROM EMPLEADOS";
			ResultSet resul = sentencia.executeQuery(sql);

			// Obtener el código del empleado
			resul.next();
			int codigoEmpleado = resul.getInt(1);

			// Cerrar consulta
			resul.close();
			sentencia.close();

			// Segunda consulta para verificar si existe el jefe
			Statement sentencia2 = conexion.createStatement();
			String sql2 = "select codigojefe from empleados";
			ResultSet resul2 = sentencia2.executeQuery(sql2);

			// Inicializar una variable booleana
			boolean existeJefe = false;

			// Recorrer todos los códigos de los jefes
			while (resul2.next()) {

				// Compararlo con el código pasado como parámetro
				if (resul2.getInt(1) == codigoJefe) {
					existeJefe = true;
				}

			}

			// Cerrar consulta
			resul2.close();
			sentencia2.close();

			// Tercera consulta para comprobar que exista la oficina
			Statement sentencia3 = conexion.createStatement();
			String sql3 = "select codigooficina from oficinas";
			ResultSet resul3 = sentencia3.executeQuery(sql3);

			// Inicializar una variable booleana
			boolean existeOficina = false;

			// Recorrer todos los códigos de los jefes
			while (resul3.next()) {

				// Compararlo con el código pasado como parámetro
				if (resul3.getString(1).equals(codigoOficina)) {
					existeOficina = true;
				}

			}

			// Cerrar consulta
			resul3.close();
			sentencia3.close();

			// Si existe el jefe se sigue con el proceso de inserción
			if (existeJefe) {

				// Si existe la oficina se sigue con el proceso de inserción
				if (existeOficina) {

					// Realizar la inserción
					Statement sentencia4 = conexion.createStatement();
					String sql4 = "insert into empleados values (" + "    " + codigoEmpleado + "," + "    '" + nombre
							+ "'," + "    '" + primerApellido + "'," + "    '" + segundoApellido + "'," + "    '"
							+ extension + "'," + "    '" + email + "'," + "    '" + codigoOficina + "'," + "    "
							+ codigoJefe + "," + "    '" + puesto + "'" + ")";
					ResultSet resul4 = sentencia4.executeQuery(sql4);

					// Cerrar consulta
					resul4.close();
					sentencia4.close();

				} else {
					System.out.println("No existe la oficina");
				}

			} else {
				System.out.println("No existe el jefe");
			}

			// Cerrar conexion
			conexion.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	private static void probarConexion() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "jardineriaad",
					"dam");
			// Preparamos la consulta
			Statement sentencia = conexion.createStatement();
			String sql = "SELECT * FROM detallepedidos";
			ResultSet resul = sentencia.executeQuery(sql);

			// Recorremos el resultado para visualizar cada fila

			// Se hace un bucle mientras haya registros y se van mostrando

			while (resul.next()) {
				System.out.printf("%d, %s, %d, %d, %d %n", resul.getInt(1), resul.getString(2), resul.getInt(3),
						resul.getInt(4), resul.getInt(5));
			}
			resul.close(); // Cerrar ResultSet
			sentencia.close(); // Cerrar Statement
			conexion.close(); // Cerrar conexión
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	private static void menu() {

		System.out.println("---------------------------------------------------------------------------");
		System.out.println("\t..................................................");
		System.out.println("\t.  1 Insertar empleado");
		System.out.println("\t.  2 Visualizar pedidos de un cliente");
		System.out.println("\t.  3 Crear clientes sin pedido");
		System.out.println("\t.  4 Actualizar clientes por empleado");
		System.out.println("\t.  5 Crear STOCKACTUALIZADO");
		System.out.println("\t.  6 Oficinas con función almacenada");
		System.out.println("\t.  7 Ver los pedidos de todos los clientes");
		System.out.println("\t.  8 Tratar nuevos empleados");
		System.out.println("\t.  0 SALIR");
		System.out.println("\t..................................................");
		System.out.println("---------------------------------------------------------------------------");
		System.out.print("TECLEA OPERACIÓN:");
	}

}
