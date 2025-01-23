package principal;

import java.sql.*;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import objetos.Estudiantes;
import objetos.Participa;
import objetos.Proyectos;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal que gestiona la creación, consulta e inserción de datos en
 * una base de datos Neodatis, a partir de datos de una base de datos Oracle.
 * Proporciona un menú interactivo para realizar estas operaciones.
 */
public class Principal {

	private static final String DB_NAME = "proyectos.dat";
	private static ODB odb;
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USER = "PROYECTOS";
	private static final String PASSWORD = "proyectos";

	/**
	 * Método principal. Muestra un menú interactivo para realizar operaciones en la
	 * base de datos Neodatis.
	 * 
	 * @param args Argumentos de línea de comandos.
	 */
	public static void main(String[] args) {

		// Abrir la base de datos.
		odb = ODBFactory.open(DB_NAME);

		// Crear el objeto Scanner para la interacción con el usuario.
		Scanner scanner = new Scanner(System.in);

		int opcion;

		do {

			menu();
			opcion = scanner.nextInt();

			switch (opcion) {
			case 1:
				crearBD();
				break;
			case 2:
				listarProyecto();
				break;
			case 3:
				insertarParticipacion();
				break;
			case 0:
				System.out.println("FIN DEL PROGRAMA");
				break;
			default:
				System.out.println("Opción no válida.");
			}
		} while (opcion != 0);

		// Cerrar la base de datos y el Scanner.
		odb.close();
		scanner.close();

	}

	/**
	 * Muestra el menú interactivo de opciones al usuario.
	 */
	private static void menu() {
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("\t................................................................");
		System.out.println("\t.  1. Crear BD.");
		System.out.println("\t.  2. Listar un proyecto.");
		System.out.println("\t.  3. Insertar participación.");
		System.out.println("\t.  0. Salir");
		System.out.println("\t.................................................................");
		System.out.println("---------------------------------------------------------------------------");
		System.out.print("TECLEA OPERACIÓN:");
	}

	/**
	 * Crea la base de datos Neodatis utilizando datos provenientes de una base de
	 * datos Oracle.
	 * <p>
	 * Este método verifica primero si la base de datos Neodatis ya contiene
	 * proyectos. Si no es así, conecta con la base de datos Oracle, extrae los
	 * proyectos y sus participaciones, y los almacena en Neodatis. También inserta
	 * los estudiantes que no participan en ningún proyecto.
	 * </p>
	 */
	private static void crearBD() {

		Objects<Proyectos> proyectos = odb.getObjects(Proyectos.class);

		// Verificar si la base de datos Neodatis ya tiene proyectos.
		if (!proyectos.hasNext()) {
			
			try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
				
				// Consultar las tablas de la base de datos Oracle.
				Statement stmt = conn.createStatement();

				// Obtener proyectos de Oracle.
				ResultSet rsProyectos = stmt.executeQuery("SELECT * FROM proyectos");
				
				while (rsProyectos.next()) {
					
					int codigoproyecto = rsProyectos.getInt("codigoproyecto");
					String nombre = rsProyectos.getString("nombre");
					Date fechainicio = rsProyectos.getDate("fechainicio");
					Date fechafin = rsProyectos.getDate("fechafin");
					float presupuesto = rsProyectos.getFloat("presupuesto");
					float extraaportacion = rsProyectos.getFloat("extraaportacion");

					// Crear el proyecto en Neodatis.
					Proyectos proyecto = new Proyectos(codigoproyecto, nombre, fechainicio, fechafin, presupuesto,
							extraaportacion);
					odb.store(proyecto);

					// Obtener participaciones del proyecto desde Oracle.
					PreparedStatement stmtParticipa = conn
							.prepareStatement("SELECT * FROM participa WHERE codigoproyecto = ?");
					stmtParticipa.setInt(1, codigoproyecto);
					ResultSet rsParticipa = stmtParticipa.executeQuery();
					
					List<Participa> participaciones = new ArrayList<>();
					
					while (rsParticipa.next()) {
						
						int codparticipacion = rsParticipa.getInt("codparticipacion");
						int codestudiante = rsParticipa.getInt("codestudiante");
						String tipoparticipacion = rsParticipa.getString("tipoparticipacion");
						int numaportaciones = rsParticipa.getInt("numaportaciones");

						// Crear la participación y vincular al estudiante y proyecto.
						Estudiantes estudiante = obtenerEstudiantePorCodigo(codestudiante, conn);

						// Verificar si el estudiante ya está en Neodatis.
						Objects<Estudiantes> estudiantesNeodatis = odb.getObjects(Estudiantes.class);
						
						boolean estudianteExiste = false;
						
						while (estudiantesNeodatis.hasNext()) {
							
							Estudiantes e = estudiantesNeodatis.next();
							
							if (e.getCodestudiante() == estudiante.getCodestudiante()) {
								estudianteExiste = true;
								estudiante = e;
								break;
							}
							
						}

						// Si no existe, insertar estudiante en Neodatis.
						if (!estudianteExiste) {
							odb.store(estudiante);
						}

						// Crear y almacenar la participación.
						Participa participa = new Participa(codparticipacion, estudiante, proyecto, tipoparticipacion,
								numaportaciones);
						participaciones.add(participa);
						odb.store(participa);
						
					}

					// Asignar las participaciones al proyecto y almacenarlo.
					proyecto.setParticipantes(participaciones);
					odb.store(proyecto);
					
				}

				// Insertar estudiantes que no participen en proyectos.
				ResultSet rsEstudiantes = stmt.executeQuery("SELECT * FROM estudiantes");
				
				while (rsEstudiantes.next()) {
					
					int codestudiante = rsEstudiantes.getInt("codestudiante");
					String nombre = rsEstudiantes.getString("nombre");
					String direccion = rsEstudiantes.getString("direccion");
					String tlf = rsEstudiantes.getString("tlf");
					Date fechaalta = rsEstudiantes.getDate("fechaalta");

					// Verificar si el estudiante ya está en Neodatis.
					Objects<Estudiantes> estudiantesNeodatis = odb.getObjects(Estudiantes.class);
					
					boolean estudianteExiste = false;
					
					while (estudiantesNeodatis.hasNext()) {
						
						Estudiantes e = estudiantesNeodatis.next();
						
						if (e.getCodestudiante() == codestudiante) {
							estudianteExiste = true;
							break;
						}
						
					}

					// Si no existe, insertarlo en Neodatis.
					if (!estudianteExiste) {
						Estudiantes nuevoEstudiante = new Estudiantes(codestudiante, nombre, direccion, tlf, fechaalta);
						odb.store(nuevoEstudiante);
					}
					
				}

				System.out.println("\nBase de datos Neodatis creada con éxito.\n");
				
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error al conectar o leer desde la base de datos Oracle.\n");
			}
			
		} else {
			
			System.out.println("\nLa base de datos Neodatis ya tiene proyectos.\n");
			
		}
	}

	/**
	 * Obtiene un objeto Estudiantes desde la base de datos Oracle dado su código.
	 * 
	 * @param codEstudiante 	Código del estudiante a buscar.
	 * @param conn          	Conexión activa a la base de datos Oracle.
	 * @return Objeto Estudiantes correspondiente o null si no se encuentra.
	 * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
	 */
	private static Estudiantes obtenerEstudiantePorCodigo(int codEstudiante, Connection conn) throws SQLException {
		
		PreparedStatement stmtEstudiante = conn.prepareStatement("SELECT * FROM estudiantes WHERE codestudiante = ?");
		stmtEstudiante.setInt(1, codEstudiante);
		
		ResultSet rsEstudiante = stmtEstudiante.executeQuery();

		if (rsEstudiante.next()) {
			
			int codestudiante = rsEstudiante.getInt("codestudiante");
			String nombre = rsEstudiante.getString("nombre");
			String direccion = rsEstudiante.getString("direccion");
			String tlf = rsEstudiante.getString("tlf");
			Date fechaalta = rsEstudiante.getDate("fechaalta");
			return new Estudiantes(codestudiante, nombre, direccion, tlf, fechaalta);
			
		}
		
		return null;
		
	}

	/**
	 * Lista los detalles de un proyecto específico, incluyendo sus participantes y
	 * totales de aportaciones e importes.
	 * <p>
	 * Actualmente, el código de proyecto está fijo en 1. Para una implementación
	 * más flexible, se podría modificar para que el usuario ingrese el código del
	 * proyecto a listar.
	 * </p>
	 */
	private static void listarProyecto() {

		// Código de proyecto a listar
		int codigoProyecto = 1;

		// Buscar el proyecto en la base de datos Neodatis
		Objects<Proyectos> proyectos = odb.getObjects(Proyectos.class);
		
		boolean encontrado = false;

		while (proyectos.hasNext()) {
			
			Proyectos proyecto = proyectos.next();
			
			if (proyecto.getCodigoproyecto() == codigoProyecto) {
				
				encontrado = true;

				// Mostrar los detalles del proyecto
				System.out.println();
				System.out.printf("%-22s %-14s %-30s %n", "---------------------", "-------------",
						"-----------------------------");
				System.out.printf("%-36s %-40s %n", "Código proyecto: " + proyecto.getCodigoproyecto(),
						"Nombre: " + proyecto.getNombre());
				System.out.printf("%-36s %-40s %n", "Fecha inicio: " + proyecto.getFechainicio(),
						"Fecha fin: " + proyecto.getFechafin());
				System.out.printf("%-36s %-40s %n", "Presupuesto: " + proyecto.getPresupuesto(),
						"Extraaportación: " + proyecto.getExtraaportacion());
				System.out.printf("%-22s %-14s %-30s %n", "---------------------", "-------------",
						"-----------------------------");
				System.out.println("Participantes en el proyecto:");
				System.out.println("----------------------------");

				System.out.printf("%-20s %-20s %-25s %-20s %-18s %-18s\n", "CODPARTICIPACION", "CODESTUDIANTE",
						"NOMBREESTUDIANTE", "TIPAPORTACION", "NUMAPORTACIONES", "IMPORTE");
				System.out.printf("%-20s %-20s %-25s %-20s %-18s %-18s\n", "-------------------", "-------------------",
						"------------------------", "-------------------", "----------------", "----------------");

				float totalImporte = 0;
				int totalAportaciones = 0;

				for (Participa participa : proyecto.getParticipantes()) {
					
					Estudiantes estudiante = participa.getEstudiante();
					float importe = participa.getNumaportaciones() * proyecto.getExtraaportacion();
					totalImporte += importe;
					totalAportaciones += participa.getNumaportaciones();

					System.out.printf("%-20d %-20d %-25s %-20s %-18d %-18.2f\n", participa.getCodparticipacion(),
							estudiante.getCodestudiante(), estudiante.getNombre(), participa.getTipoparticipacion(),
							participa.getNumaportaciones(), importe);
					
				}

				System.out.printf("%-20s %-20s %-25s %-20s %-18s %-18s\n", "-------------------", "-------------------",
						"------------------------", "-------------------", "----------------", "----------------");
				System.out.printf("%-20s %-20s %-25s %-20s %-18s %-18s\n", "TOTALES:", "", "", "", totalAportaciones,
						totalImporte);
				System.out.println();
				break;
				
			}
		}

		if (!encontrado) {
			System.out.println("El proyecto con código " + codigoProyecto + " no existe en la base de datos.");
		}
	}

	/**
	 * Inserta una nueva participación en un proyecto específico para un estudiante
	 * dado.
	 * <p>
	 * Actualmente, los datos de la participación (código de estudiante, código de
	 * proyecto, tipo y número de aportaciones) están fijados en el método. Para una
	 * implementación más flexible, se podría modificar para que el usuario ingrese
	 * estos datos.
	 * </p>
	 */
	private static void insertarParticipacion() {
		
		// Datos de prueba
		int codEstudiante = 2; 
		int codProyecto = 1; 
		String tipoAportacion = "Becario"; 
		int numAportaciones = 3; 

		// Verificar si el proyecto y el estudiante existen en la base de datos
		Objects<Estudiantes> estudiantes = odb.getObjects(Estudiantes.class);
		Objects<Proyectos> proyectos = odb.getObjects(Proyectos.class);
		
		Estudiantes estudiante = null;
		Proyectos proyecto = null;

		while (estudiantes.hasNext()) {
			
			Estudiantes e = estudiantes.next();
			
			if (e.getCodestudiante() == codEstudiante) {
				estudiante = e;
				break;
			}
			
		}

		while (proyectos.hasNext()) {
			
			Proyectos p = proyectos.next();
			
			if (p.getCodigoproyecto() == codProyecto) {
				proyecto = p;
				break;
			}
			
		}

		// Si el estudiante y el proyecto existen
		if (estudiante != null && proyecto != null) {
			
			// Crear un nuevo código de participación (máximo código actual + 1)
			int codParticipacion = obtenerCodigoParticipacionMaximo() + 1;

			// Crear la nueva participación
			Participa nuevaParticipacion = new Participa(codParticipacion, estudiante, proyecto, tipoAportacion,
					numAportaciones);

			// Almacenar la nueva participación en la base de datos
			odb.store(nuevaParticipacion);

			// Verificar e inicializar la lista de participaciones de Estudiantes y Proyectos si es necesario
			if (proyecto.getParticipantes() == null) {
				proyecto.setParticipantes(new ArrayList<>());
			}
			
			if (estudiante.getParticipaen() == null) {
				estudiante.setParticipaen(new ArrayList<>());
			}

			// Actualizar las listas de participaciones de Proyectos y Estudiantes
			proyecto.getParticipantes().add(nuevaParticipacion);
			estudiante.getParticipaen().add(nuevaParticipacion);

			// Almacenar los cambios en Proyectos y Estudiantes
			odb.store(proyecto);
			odb.store(estudiante);

			// Mensaje indicando qué participación fue insertada
			System.out.println("\nParticipación insertada con éxito.");
			System.out.println("Detalles de la participación insertada:");
			System.out.println("Código Participación: " + nuevaParticipacion.getCodparticipacion());
			System.out.println(
					"Estudiante: " + estudiante.getNombre() + " (Código: " + estudiante.getCodestudiante() + ")");
			System.out.println("Proyecto: " + proyecto.getNombre() + " (Código: " + proyecto.getCodigoproyecto() + ")");
			System.out.println("Tipo de Aportación: " + tipoAportacion);
			System.out.println("Número de Aportaciones: " + numAportaciones);
			System.out.println();
			
		} else {
			
			// Si el proyecto o el estudiante no existen, mostrar un mensaje de error
			if (estudiante == null) {
				System.out.println(
						"\nError: El estudiante con código " + codEstudiante + " no existe en la base de datos.\n");
			}
			
			if (proyecto == null) {
				System.out.println(
						"\nError: El proyecto con código " + codProyecto + " no existe en la base de datos.\n");
			}
			
		}
	}

	/**
	 * Obtiene el código máximo de participación existente en la base de datos
	 * Neodatis.
	 * 
	 * @return Código máximo de participación.
	 */
	private static int obtenerCodigoParticipacionMaximo() {
		
		Objects<Participa> participaciones = odb.getObjects(Participa.class);
		
		int maxCodigo = 0;
		
		while (participaciones.hasNext()) {
			
			Participa participa = participaciones.next();
			if (participa.getCodparticipacion() > maxCodigo) {
				maxCodigo = participa.getCodparticipacion();
			}
			
		}
		
		return maxCodigo;
		
	}
}
