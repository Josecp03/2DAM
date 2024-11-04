package ejercicio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Jardineria {

	// Atributos para los métodos
	private int codigoPedidoMaximo;
	private Date fechaPedidoMaximo;

	// Getters y Setters
	public int getCodigoPedidoMaximo() {
		return codigoPedidoMaximo;
	}

	public void setCodigoPedidoMaximo(int codigoPedidoMaximo) {
		this.codigoPedidoMaximo = codigoPedidoMaximo;
	}

	public Date getFechaPedidoMaximo() {
		return fechaPedidoMaximo;
	}

	public void setFechaPedidoMaximo(Date fechaPedidoMaximo) {
		this.fechaPedidoMaximo = fechaPedidoMaximo;
	}

	// Atributo de conexion para poder acceder a ello en cualquier sitio de la clase
	private static Connection conexion;

	// Bloque estático para inicializar la conexión al iniciar la clase
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "jardineriaad", "dam");
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
//				 insertarEmpleado(nombre2, primerApellido2, segundoApellido2, extension2,
//				 email2, codigoOficina2, codigoJefe2, puesto2);
//				 insertarEmpleado(nombre3, primerApellido3, segundoApellido3, extension3,
//				 email3, codigoOficina3, codigoJefe3, puesto3);

				break;

			case 2:

				// Caso Correcto
				int codigoCliente = 4;

//				// No existe cliente
//				int codigoCliente2 = 999;

//				// Cliente no tiene pedidos
//				int codigoCliente3 = 12

				visualizarPedidosCliente(codigoCliente);
//				visualizarPedidosCliente(codigoCliente2);
//				visualizarPedidosCliente(codigoCliente3);

				break;

			case 3:
				clientesSinPedidos();
				break;
			case 4:
				actualizarEmpleados();
				break;
			case 5:
				actualizarProductos();
				break;
			case 6:
				
				break;
			case 7:
				visualizarPedidosClientes();
				break;
			case 8:

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

	private static void actualizarProductos() throws SQLException {
		
		// Comprobar que no exista la tabla StockActualizado
		if (!comprobarActualizacionProductos()) {
			
			// Creo la nueva columna en la tabla empleados
			crearNuevaColumnaProductos();
		
			// Mostrar por pantalla los productos que se necesitan reponer
			System.out.println("Los productos que necesitan reponerse son los siguientes: \n");
			System.out.printf("%-20s %-20s %-20s %n", "COD-PRODUCTO", "CANTIDAD-EN-STOCK", "STOCK-ACTUALIZADO");
			System.out.printf("%-20s %-20s %-20s %n", "-------------------", "-------------------", "-------------------");
			
			// Actualizar el contenido de la columna
			actualizarStock();
			
			// Mostrar mensaje de confirmación
			System.out.println("\nTabla de Productos actualizada con éxito");
			
		} else {
			
			// Mostrar mensaje de error
			System.out.println("Error. La tabla productos ya ha sido actualizada anteriormente");
			
		}
		
	}

	private static void actualizarStock() throws SQLException {
		
		// Inicializar variables
		String codigoPedidoActual = "";

		// Realizar consulta
		Statement sentencia = conexion.createStatement();
		String sql = "select codigoproducto from productos";
		ResultSet resul = sentencia.executeQuery(sql);

		// Recorrer todos los clientes
		while (resul.next()) {

			// Asignar a una variable el número de clientes que tiene el empleado actual
			codigoPedidoActual = resul.getString(1);

			// Insertar los datos actualizados
			insertarStockActualizado(codigoPedidoActual);

		}

		// Cerrar consulta
		resul.close();
		sentencia.close();
		
	}

	private static void insertarStockActualizado(String codigoProductoActual) throws SQLException {
		
		// Inicializar variables
		int cantidadStockActualizada = 0;

		// Realizar consulta
		Statement sentencia = conexion.createStatement();
		String sql = "select cantidadenstock from productos where codigoproducto = '" + codigoProductoActual + "'";
		ResultSet resul = sentencia.executeQuery(sql);

		// Pasar a la siguiente línea para poder leer los datos
		resul.next();

		// Asignar el dato en una variable
		cantidadStockActualizada = resul.getInt(1) - cantidadProductoVendida(codigoProductoActual);
		
		// Insertar el dato en la tabla
		actualizarDatoStock(cantidadStockActualizada, codigoProductoActual);
		
		// Mostrar los datos de los productos a reponer
		mostrarProductosParaReponer(codigoProductoActual, cantidadStockActualizada);

		// Cerrar consulta
		resul.close();
		sentencia.close();
			
	}

	private static void mostrarProductosParaReponer(String codigoProductoActual, int cantidadStockActualizada) throws SQLException {
		
		// Incializar variables
		int cantidadEnStock = 0;
		
		// Realizar consulta
		Statement sentencia = conexion.createStatement();
		String sql = "select cantidadenstock from productos where codigoproducto = '"+codigoProductoActual+"'";
		ResultSet resul = sentencia.executeQuery(sql);

		// Pasar a la sigueinte línea
		resul.next();
		
		// Asignar valor a la variable
		cantidadEnStock = resul.getInt(1);
		
		// Comprobar primero q el stockactualizado sea mayor que 5
		if (cantidadStockActualizada < 5) {
			
			// Mostrar los datos del producto
			System.out.printf("%-20s %-20s %-20s %n", codigoProductoActual, cantidadEnStock, cantidadStockActualizada);
		}
		
		// Cerrar consulta
		resul.close();
		sentencia.close();
		
	}

	private static void actualizarDatoStock(int cantidadStockActualizada, String codigoProductoActual) throws SQLException {
		
		// Realizar consulta
		Statement sentencia = conexion.createStatement();
		String sql = "update productos set stockactualizado = " + cantidadStockActualizada + " where codigoproducto = '"
				+ codigoProductoActual + "'";
		ResultSet resul = sentencia.executeQuery(sql);

		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		
	}

	private static int cantidadProductoVendida(String codigoProductoActual) throws SQLException {
		
		// Inicializar variables
		int cantidadVendidaProducto = 0;

		// Realizar consulta
		Statement sentencia = conexion.createStatement();
		String sql = "select sum(cantidad) from detallepedidos where codigoproducto = '" + codigoProductoActual + "'";
		ResultSet resul = sentencia.executeQuery(sql);

		// Pasar a la siguiente línea para poder leer los datos
		resul.next();

		// Asignar el dato en una variable
		cantidadVendidaProducto = resul.getInt(1);

		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		// Devolver el valor entero
		return cantidadVendidaProducto;
	}

	private static void crearNuevaColumnaProductos() throws SQLException {
		
		// Realizar consulta
		Statement sentencia = conexion.createStatement();
		String sql = "alter table productos add stockactualizado number(6,2)";
		ResultSet resul = sentencia.executeQuery(sql);

		// Cerrar consulta
		resul.close();
		sentencia.close();
		
		
	}

	private static boolean comprobarActualizacionProductos() throws SQLException {
		
		// Inicializar variables
		int numeroColumnasEncontradas = 0;
		boolean datosActualizados = false;

		// Realizar consulta
		Statement sentencia = conexion.createStatement();
		String sql = "SELECT COUNT(*) FROM all_tab_columns WHERE owner = 'JARDINERIAAD' AND table_name = 'PRODUCTOS' AND column_name = 'STOCKACTUALIZADO'";
		ResultSet resul = sentencia.executeQuery(sql);

		// Saltar de línea
		resul.next();

		// Asignar el numero de coumnas encontradas
		numeroColumnasEncontradas = resul.getInt(1);

		// Comprobar si se ha encontrado la columna buscada
		if (numeroColumnasEncontradas > 0) {

			// Comprobar que exista la columna
			datosActualizados = true;

		}

		// Cerrar consulta
		resul.close();
		sentencia.close();

		// Devolver el valor booleano
		return datosActualizados;
		
	}

	private static void visualizarPedidosClientes() throws SQLException {

		// Inicializar variables
		int codigoClienteActual = 0;

		// Realizar consulta
		Statement sentencia = conexion.createStatement();
		String sql = "select codigocliente from clientes where codigocliente in (select codigocliente from pedidos)";
		ResultSet resul = sentencia.executeQuery(sql);

		// Recorrer todos los clientes
		while (resul.next()) {

			// Asignar a una variable el número de clientes que tiene el empleado actual
			codigoClienteActual = resul.getInt(1);

			// Visualizar todos los pedidos de todos los clientes
			visualizarPedidosCliente(codigoClienteActual);

		}

		// Cerrar consulta
		resul.close();
		sentencia.close();

	}

	private static void actualizarEmpleados() throws SQLException {

		// Comprobar que ya se haya actualizado
		if (!comprobarActualizaciones()) {

			// Creo la nueva columna en la tabla empleados
			crearNuevaColumna();

			// Actualizar el contenido de la columna
			actualizarNumClientes();

			System.out.println("Tabla de Empleados actualizada con éxito");

		} else {

			// Mostrar mensaje de error
			System.out.println("Error. La tabla empleados ya ha sido actualizada anteriormente");

		}

	}

	private static void actualizarNumClientes() throws SQLException {

		// Inicializar variables
		int codigoEmpleadoActual = 0;

		// Realizar consulta
		Statement sentencia = conexion.createStatement();
		String sql = "select codigoempleado from empleados";
		ResultSet resul = sentencia.executeQuery(sql);

		// Recorrer todos los clientes
		while (resul.next()) {

			// Asignar a una variable el número de clientes que tiene el empleado actual
			codigoEmpleadoActual = resul.getInt(1);

			// Insertar los datos actualizados
			insertarNumeroClientes(codigoEmpleadoActual);

		}

		// Cerrar consulta
		resul.close();
		sentencia.close();

	}

	private static void insertarNumeroClientes(int codigoEmpleadoActual) throws SQLException {

		// Inicializar variables
		int numeroClientes = 0;

		// Realizar consulta
		Statement sentencia = conexion.createStatement();
		String sql = "select count(*) from clientes where codigoempleadorepventas = " + codigoEmpleadoActual;
		ResultSet resul = sentencia.executeQuery(sql);

		// Pasar a la siguiente línea para poder leer los datos
		resul.next();

		// Asignar el dato en una variable
		numeroClientes = resul.getInt(1);

		// Insertar el dato en la tabla
		actualizarDato(numeroClientes, codigoEmpleadoActual);

		// Cerrar consulta
		resul.close();
		sentencia.close();

	}

	private static void actualizarDato(int numeroClientes, int codigoEmpleadoActual) throws SQLException {

		// Realizar consulta
		Statement sentencia = conexion.createStatement();
		String sql = "update empleados set numclientes = " + numeroClientes + " where codigoempleado = "
				+ codigoEmpleadoActual;
		ResultSet resul = sentencia.executeQuery(sql);

		// Cerrar consulta
		resul.close();
		sentencia.close();

	}

	private static void crearNuevaColumna() throws SQLException {

		// Realizar consulta
		Statement sentencia = conexion.createStatement();
		String sql = "alter table empleados add numclientes number(10,2)";
		ResultSet resul = sentencia.executeQuery(sql);

		// Cerrar consulta
		resul.close();
		sentencia.close();

	}

	private static boolean comprobarActualizaciones() throws SQLException {

		// Inicializar variables
		int numeroColumnasEncontradas = 0;
		boolean datosActualizados = false;

		// Realizar consulta
		Statement sentencia = conexion.createStatement();
		String sql = "select count(*) from all_tab_columns where owner = 'jardineriaad' and table_name = 'empleados' and column_name = 'numclientes'";
		ResultSet resul = sentencia.executeQuery(sql);

		// Saltar de línea
		resul.next();

		// Asignar el numero de coumnas encontradas
		numeroColumnasEncontradas = resul.getInt(1);

		// Comprobar si se ha encontrado la columna buscada
		if (numeroColumnasEncontradas > 0) {

			// Comprobar que exista la columna
			datosActualizados = true;

		}

		// Cerrar consulta
		resul.close();
		sentencia.close();

		// Devolver el valor booleano
		return datosActualizados;
	}

	private static void clientesSinPedidos() throws SQLException {

		// Inicializar variables
		int codigoCliente = 0;
		String nombreCliente = "";
		String nombreContacto = "";
		String apellidoContacto = "";
		String telefono = "";
		String fax = "";
		String lineaDireccion1 = "";
		String lineaDireccion2 = "";
		String ciudad = "";
		String region = "";
		String pais = "";
		String codigoPostal = "";
		int codigoEmpleadoRepVentas = 0;
		int limiteCredito = 0;

		// Preparamos la consulta
		Statement sentencia = conexion.createStatement();
		String sql = "select * from clientes";
		ResultSet resul = sentencia.executeQuery(sql);

		// Comprobar que existan clientes sin pedidos
		if (existenClientesSinPedidos()) {

			// Crear la tabla de clientes sin pedidos
			crearTablaClientesSinPedidos();

			while (resul.next()) {

				// Asignar variables del cliente
				codigoCliente = resul.getInt(1);
				nombreCliente = resul.getString(2);
				nombreContacto = resul.getString(3);
				apellidoContacto = resul.getString(4);
				telefono = resul.getString(5);
				fax = resul.getString(6);
				lineaDireccion1 = resul.getString(7);
				lineaDireccion2 = resul.getString(8);
				ciudad = resul.getString(9);
				region = resul.getString(10);
				pais = resul.getString(11);
				codigoPostal = resul.getString(12);
				codigoEmpleadoRepVentas = resul.getInt(13);
				limiteCredito = resul.getInt(14);

				// Comprobar que tenga pedidos
				if (!tienePedidos(codigoCliente)) {

					// Borrar el cliente de la tabla clientes
					borrarCliente(codigoCliente);

					// Insertar el cliente en la tabla de clientes sin pedidos
					insertarClienteSinPedidos(codigoCliente, nombreCliente, nombreContacto, apellidoContacto, telefono,
							fax, lineaDireccion1, lineaDireccion2, ciudad, region, pais, codigoPostal,
							codigoEmpleadoRepVentas, limiteCredito);

				}

			}

			// Mostrar mensaje de confirmación
			System.out.println("Se ha creado la tabla ClientesSinPedidos con éxito");

		} else {

			// Mostrar mensaje de error
			System.out.println("No existen clientes sin pedidos en la tabla CLIENTES");

		}

		resul.close(); // Cerrar ResultSet
		sentencia.close(); // Cerrar Statement

	}

	private static void insertarClienteSinPedidos(int codigoCliente, String nombreCliente, String nombreContacto,
			String apellidoContacto, String telefono, String fax, String lineaDireccion1, String lineaDireccion2,
			String ciudad, String region, String pais, String codigoPostal, int codigoEmpleadoRepVentas,
			int limiteCredito) throws SQLException {

		// Realizar la inserción
		Statement sentencia = conexion.createStatement();
		String sql = "insert into ClientesSinPedidos values (" + "    " + codigoCliente + "," + "    '" + nombreCliente
				+ "'," + "    '" + nombreContacto + "'," + "    '" + apellidoContacto + "'," + "    '" + telefono + "',"
				+ "    '" + fax + "'," + "    '" + lineaDireccion1 + "'," + "    '" + lineaDireccion2 + "'," + "    '"
				+ ciudad + "'," + "    '" + region + "'," + "    '" + pais + "'," + "    '" + codigoPostal + "'" + ","
				+ codigoEmpleadoRepVentas + "," + limiteCredito + ")";
		ResultSet resul = sentencia.executeQuery(sql);

		// Cerrar consulta
		resul.close();
		sentencia.close();

	}

	private static void borrarCliente(int codigoCliente) throws SQLException {

		// Realizar consulta
		Statement sentencia = conexion.createStatement();
		String sql = "delete from clientes where codigocliente = " + codigoCliente;
		ResultSet resul = sentencia.executeQuery(sql);

		// Saltar de línea
		resul.next();

		// Cerrar consulta
		resul.close();
		sentencia.close();

	}

	private static void crearTablaClientesSinPedidos() throws SQLException {

		// Realizar consulta
		Statement sentencia = conexion.createStatement();
		String sql = "		CREATE TABLE ClientesSinPedidos (" + "				  CodigoCliente number(6) NOT NULL,"
				+ "				  NombreCliente varchar2(50) NOT NULL,"
				+ "				  NombreContacto varchar2(30) DEFAULT NULL,"
				+ "				  ApellidoContacto varchar2(30) DEFAULT NULL,"
				+ "				  Telefono varchar2(15) NOT NULL," + "				  Fax varchar2(15) NOT NULL,"
				+ "				  LineaDireccion1 varchar2(50) NOT NULL,"
				+ "				  LineaDireccion2 varchar2(50) DEFAULT NULL,"
				+ "				  Ciudad varchar2(50) NOT NULL," + "				  Region varchar2(50) DEFAULT NULL,"
				+ "				  Pais varchar2(50) DEFAULT NULL,"
				+ "				  CodigoPostal varchar2(10) DEFAULT NULL,"
				+ "				  CodigoEmpleadoRepVentas number(6) DEFAULT NULL,"
				+ "				  LimiteCredito number(15,2) DEFAULT NULL,"
				+ "				  PRIMARY KEY (CodigoCliente),"
				+ "				  CONSTRAINT ClientesSinPedidos_EmpleadosFK FOREIGN KEY (CodigoEmpleadoRepVentas) REFERENCES Empleados (CodigoEmpleado)"
				+ "				)";
		ResultSet resul = sentencia.executeQuery(sql);

		// Cerrar consulta
		resul.close();
		sentencia.close();

	}

	private static boolean existenClientesSinPedidos() throws SQLException {

		// Inicializar variables
		boolean existenClientesSinPedidos = false;
		int numeroClientesSinPedidos = 0;

		// Realizar consulta
		Statement sentencia = conexion.createStatement();
		String sql = "select count(*) from clientes where codigocliente not in (select codigocliente from pedidos)";
		ResultSet resul = sentencia.executeQuery(sql);

		// Saltar de línea
		resul.next();

		// Saltar a la siguiente línea para poder leerlo bien
		numeroClientesSinPedidos = resul.getInt(1);

		// Comprobar si son 0 los clientes sin pedidos
		if (numeroClientesSinPedidos > 0) {
			existenClientesSinPedidos = true;
		}

		// Cerrar consulta
		resul.close();
		sentencia.close();

		// Devolver el número de línea del producto de ese pedido en concreto
		return existenClientesSinPedidos;

	}

	private static void visualizarPedidosCliente(int codigoCliente) throws SQLException {

		// Comprobar que exista el cliente
		if (existeCliente(codigoCliente)) {

			// Comprobar que el cliente tenga pedidos
			if (tienePedidos(codigoCliente)) {

				// Mostrar los datos del cliente (Encabezado)
				mostrarDatosCliente(codigoCliente);
				mostrarDatosPedidosCliente(codigoCliente);

			} else {

				// Mostrar mensaje de error
				System.out.println("El cliente " + codigoCliente + " no tiene pedidos");

			}

		} else {

			// Mostrar mensaje de error
			System.out.println("No existe el cliente");

		}

	}

	private static void mostrarDatosPedidosCliente(int codigoCliente) throws SQLException {

		// Inicializar variables
		int importeMaximo = 0;
		int importeTotalPedidoActual = 0;
		Jardineria j = new Jardineria();
		int cantidadMaxima = 0;
		String nombreProductoMasVendido = "";
		String codigoProductoMasVendido = "";

		// Realizar consulta
		Statement sentencia = conexion.createStatement();
		String sql = "select * from pedidos where codigocliente = " + codigoCliente;
		ResultSet resul = sentencia.executeQuery(sql);

		System.out.println(
				"----------------------------------------------------------------------------------------------------------------------------------");

		// Recorremos todos los pedidos que tiene el cliente
		while (resul.next()) {

			int codigoPedido = resul.getInt(1);
			Date fechaPedido = resul.getDate(2);
			String estado = resul.getString(5);

			if (pedidoTieneProductos(codigoPedido)) {

				// Mostrar los datos del pedido
				System.out.printf("%n COD-PEDIDO: %-10s FECHA-PEDIDO: %-15s ESTADO DEL PEDIDO: %-15s %n%n",
						codigoPedido, fechaPedido, estado);

				// Inicializar variables
				int importeTotalPedido = 0;

				// Mostrar la cabecera
				System.out.printf("\t%-15s %-15s %-50s %-10s %-15s %-10s %n", "NUM-LINEA", "COD-PROD",
						"NOMBRE PRODUCTO", "CANTIDAD", "PREC-UNID", "IMPORTE");
				System.out.printf("\t%-15s %-15s %-50s %-10s %-15s %-10s %n", "--------------", "--------------",
						"---------------------------------------", "---------", "--------------", "---------");

				// Realizar consulta
				Statement sentencia2 = conexion.createStatement();
				String sql2 = "select * from productos where codigoproducto in (select codigoproducto from detallepedidos where codigopedido = "
						+ codigoPedido + ")";
				ResultSet resul2 = sentencia2.executeQuery(sql2);

				while (resul2.next()) {

					// Asignar datos producto
					String codigoProducto = resul2.getString(1);
					String nombre = resul2.getString(2);
					int cantidad = resul2.getInt(7);
					int precioUnidad = resul2.getInt(8);
					int importe = cantidad * precioUnidad;
					int numeroLinea = numeroLineaPedidoProducto(codigoPedido, codigoProducto);

					// Ir sumando el importe de cada producto para obtener el importe total
					importeTotalPedido += importe;

					// Comprobar el producto con mayor cantidad
					if (cantidad > cantidadMaxima) {
						cantidadMaxima = cantidad;
						nombreProductoMasVendido = nombre;
						codigoProductoMasVendido = codigoProducto;
					}

					// Mostrar los datos
					System.out.printf("\t%-15s %-15s %-50s %-10s %-15s %-10s %n", numeroLinea, codigoProducto, nombre,
							cantidad, precioUnidad, importe);

				}

				// Asignar el importe total
				importeTotalPedidoActual = importeTotalPedido;

				// Cerrar consulta
				resul2.close();
				sentencia2.close();

				// Comprobar cual es el importe máximo
				if (importeTotalPedidoActual > importeMaximo) {
					j.setCodigoPedidoMaximo(codigoPedido);
					j.setFechaPedidoMaximo(fechaPedido);
				}

			}

		}

		System.out.println(
				"----------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("COD de PEDIDO y FECHA PEDIDO CON TOTAL IMPORTE MÁXIMO: " + j.getCodigoPedidoMaximo() + " | "
				+ j.getFechaPedidoMaximo());
		System.out.println("COD PRODUCTO y NOMBRE PRODUCTO, del producto más comprado (producto con CANTIDAD Máxima): "
				+ codigoProductoMasVendido + " | " + nombreProductoMasVendido);
		System.out.println(
				"----------------------------------------------------------------------------------------------------------------------------------\n");
		// Cerrar consulta
		resul.close();
		sentencia.close();

	}

	private static boolean pedidoTieneProductos(int codigoPedido) throws SQLException {

		// Inicializar variable booleana
		boolean tieneProductos = false;

		// Realizar consulta
		Statement sentencia = conexion.createStatement();
		String sql = "select count(*) from detallepedidos where codigopedido = " + codigoPedido;
		ResultSet resul = sentencia.executeQuery(sql);

		// Saltar de línea
		resul.next();

		// Comprobar el número de pedidos que tiene
		if (resul.getInt(1) > 0) {
			tieneProductos = true;
		}

		// Cerrar consulta
		resul.close();
		sentencia.close();

		// Devolver el valor booleano
		return tieneProductos;
	}

	private static int numeroLineaPedidoProducto(int codigoPedido, String codigoProducto) throws SQLException {

		// Realizar consulta
		Statement sentencia = conexion.createStatement();
		String sql = "select numerolinea from detallepedidos where codigopedido = " + codigoPedido
				+ " and codigoproducto = '" + codigoProducto + "'";
		ResultSet resul = sentencia.executeQuery(sql);

		// Saltar de línea
		resul.next();

		// Saltar a la siguiente línea para poder leerlo bien
		int numeroLinea = resul.getInt(1);

		// Cerrar consulta
		resul.close();
		sentencia.close();

		// Devolver el número de línea del producto de ese pedido en concreto
		return numeroLinea;
	}

	private static void mostrarDatosCliente(int codigoCliente) throws SQLException {

		// Calcular el número de pedidos que tiene el cliente
		int numeroPedidos = numeroPedidosCliente(codigoCliente);

		// Realizar consulta
		Statement sentencia = conexion.createStatement();
		String sql = "select * from clientes where codigocliente = " + codigoCliente;
		ResultSet resul = sentencia.executeQuery(sql);

		// Pasar a la linea correcta para poder leer los datos de la consulta
		resul.next();

		// Asignar los datos al cliente
		String nombre = resul.getString(2);
		String direccion = resul.getString(7);

		// Encabezado de cliente
		System.out.println(
				"----------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("COD-CLIENTE: %-30s NOMBRE: %-50s %n", codigoCliente, nombre);
		System.out.printf("DIRECCIÓN1: %-31s NÚMERO PEDIDOS: %-10s %n", direccion, numeroPedidos);

		// Cerrar consulta
		resul.close();
		sentencia.close();

	}

	private static int numeroPedidosCliente(int codigoCliente) throws SQLException {

		// Realizar consulta
		Statement sentencia = conexion.createStatement();
		String sql = "select count(*) from pedidos where codigocliente = " + codigoCliente;
		ResultSet resul = sentencia.executeQuery(sql);

		// Inicialar la variable
		int numeroPedidosCliente = 0;

		// Pasar a la linea correcta para poder leer los datos de la consulta
		resul.next();

		// Asignar el número de pedidos que tiene el cliente
		numeroPedidosCliente = resul.getInt(1);

		// Cerrar consulta
		resul.close();
		sentencia.close();

		// Devolver el valor booleano
		return numeroPedidosCliente;

	}

	private static void insertarEmpleado(String nombre, String primerApellido, String segundoApellido, String extension,
			String email, String codigoOficina, int codigoJefe, String puesto) throws SQLException {

		// Obtener el código del empleado
		int codigoEmpleado = obtenerCodigoEmpleado();

		// Si existe el jefe se sigue con el proceso de inserción
		if (existeJefe(codigoJefe)) {

			// Si existe la oficina se sigue con el proceso de inserción
			if (existeOficina(codigoOficina)) {

				// Realizar la inserción
				Statement sentencia = conexion.createStatement();
				String sql = "insert into empleados values (" + "    " + codigoEmpleado + "," + "    '" + nombre + "',"
						+ "    '" + primerApellido + "'," + "    '" + segundoApellido + "'," + "    '" + extension
						+ "'," + "    '" + email + "'," + "    '" + codigoOficina + "'," + "    " + codigoJefe + ","
						+ "    '" + puesto + "'" + ")";
				ResultSet resul = sentencia.executeQuery(sql);

				// Cerrar consulta
				resul.close();
				sentencia.close();

				// Mostrar mensaje de confirmación
				System.out.println("Se ha insertado el empleado número " + codigoEmpleado + " con éxito");

			} else {
				System.out.println("No se ha insertado el empleado. No existe la oficina");
			}

		} else {
			System.out.println("No se ha insertado el empleado. No existe el jefe");
		}

		// Cerrar conexion
		conexion.close();

	}

	private static boolean existeOficina(String codigoOficina) throws SQLException {

		// Tercera consulta para comprobar que exista la oficina
		Statement sentencia = conexion.createStatement();
		String sql = "select codigooficina from oficinas";
		ResultSet resul = sentencia.executeQuery(sql);

		// Inicializar una variable booleana
		boolean existeOficina = false;

		// Recorrer todos los códigos de los jefes
		while (resul.next()) {

			// Compararlo con el código pasado como parámetro
			if (resul.getString(1).equals(codigoOficina)) {
				existeOficina = true;
			}

		}

		// Cerrar consulta
		resul.close();
		sentencia.close();

		// Devolver el valor booleano
		return existeOficina;
	}

	private static boolean existeCliente(int codigoCliente) throws SQLException {

		// Realizar consulta
		Statement sentencia = conexion.createStatement();
		String sql = "select codigocliente from clientes";
		ResultSet resul = sentencia.executeQuery(sql);

		// Inicializar una variable booleana
		boolean existeEmpleado = false;

		// Recorrer todos los códigos de los jefes
		while (resul.next()) {

			// Compararlo con el código pasado como parámetro
			if (resul.getInt(1) == codigoCliente) {
				existeEmpleado = true;
			}

		}

		// Cerrar consulta
		resul.close();
		sentencia.close();

		// Devolver el valor booleano
		return existeEmpleado;
	}

	private static boolean tienePedidos(int codigoCliente) throws SQLException {

		// // Realizar consulta
		Statement sentencia = conexion.createStatement();
		String sql = "select count(*) from pedidos where codigocliente = " + codigoCliente;
		ResultSet resul = sentencia.executeQuery(sql);

		// Inicializar una variable booleana
		boolean tienePedidos = false;

		// Pasar a la linea correcta para poder leer los datos de la consulta
		resul.next();

		// Comprobar el número que nos da la consulta
		if (resul.getInt(1) > 0) {
			tienePedidos = true;
		}

		// Cerrar consulta
		resul.close();
		sentencia.close();

		// Devolver el valor booleano
		return tienePedidos;
	}

	private static boolean existeJefe(int codigoJefe) throws SQLException {

		// Segunda consulta para verificar si existe el jefe
		Statement sentencia = conexion.createStatement();
		String sql = "select codigojefe from empleados";
		ResultSet resul = sentencia.executeQuery(sql);

		// Inicializar una variable booleana
		boolean existeJefe = false;

		// Recorrer todos los códigos de los jefes
		while (resul.next()) {

			// Compararlo con el código pasado como parámetro
			if (resul.getInt(1) == codigoJefe) {
				existeJefe = true;
			}

		}

		// Cerrar consulta
		resul.close();
		sentencia.close();

		// Devolver el valor booleano
		return existeJefe;
	}

	private static int obtenerCodigoEmpleado() throws SQLException {

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

		// Devolver el código del empleado
		return codigoEmpleado;
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
