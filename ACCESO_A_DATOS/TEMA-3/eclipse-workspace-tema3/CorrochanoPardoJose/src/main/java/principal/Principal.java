package principal;

import java.math.BigInteger;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import clases.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class Principal {

	// Atributo paara la conexión
	private static SessionFactory sesion;

	public static void main(String[] args) {

		// Quitar los INFO de hibernate
		LogManager.getLogManager().reset();
		Logger globalLogger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
		globalLogger.setLevel(java.util.logging.Level.OFF);

		// Establecer conexión con la BD
		sesion = Conexion.getSession();

		// Crear la clase Scanner para leer lo que introduce el usuario
		Scanner sc = new Scanner(System.in);

		// Declarar e inicializar la variable que guarda la opción que escoge el usuario
		int op = -1;

		// Mostrar el menú al usuario hasta que pulse el 0 para salir
		while (op != 0) {

			// Llamar al método para mostrar el menú
			menu();

			// Asignar a una variable lo que el usuario introduce por teclado
			op = sc.nextInt();

			// Switch case con los diferentes métodos por cada opción
			switch (op) {

			case 1:
				
				// Llamar al método para mostrar la información de cada curso
				mostrarAsignaturasPorCurso();

				break;

			case 2:

				// Primera consulta
				obtenerAsignaturasConMasAlumnos();

				// Segunda Consulta
				obtenerAlumnosConMayorNotaMedia();

				break;

			case 3:

				// Llamar al método para que me inserte los nuevos empleados
				insertarNuevosAlumnos();
				
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

	public static void obtenerAlumnosConMayorNotaMedia() {

		System.out.println("\nCONSULTA ALUMNOS\n");

		Session session = sesion.openSession();

		// Consulta para obtener el alumno o alumnos con la mayor nota media
		String hql = "SELECT a.codalum, a.nombre, " + "AVG(m.notaasig) AS mediaNotas " + "FROM Alumnos a "
				+ "JOIN a.matriculases m " + "GROUP BY a.codalum, a.nombre " + "HAVING AVG(m.notaasig) = ("
				+ "    SELECT MAX(media) " + "    FROM (" + "        SELECT AVG(m2.notaasig) AS media "
				+ "        FROM Alumnos a2 " + "        JOIN a2.matriculases m2 " + "        GROUP BY a2.codalum"
				+ "    ) subquery" + ")";

		try {
			Query<Object[]> query = session.createQuery(hql, Object[].class);
			List<Object[]> resultados = query.list();

			for (Object[] row : resultados) {
				BigInteger codAlum = (BigInteger) row[0];
				String nombreAlum = (String) row[1];
				Double mediaNotas = (Double) row[2];

				System.out.println("Código Alumno: " + codAlum);
				System.out.println("Nombre Alumno: " + nombreAlum);
				System.out.println("Nota Media: " + mediaNotas);
				System.out.println("------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public static void obtenerAsignaturasConMasAlumnos() {

		System.out.println("\nCONSULTA ASIGNATURAS\n");

		Session session = sesion.openSession();

		// Consulta para obtener la asignatura o asignaturas con más alumnos
		String hql = "SELECT a.codasig, a.nombreasig, COUNT(m) " + "FROM Asignaturas a " + "JOIN a.matriculases m "
				+ "GROUP BY a.codasig, a.nombreasig " + "HAVING COUNT(m) = (" + "    SELECT MAX(numAlumnos) "
				+ "    FROM (" + "        SELECT COUNT(m2) AS numAlumnos " + "        FROM Asignaturas a2 "
				+ "        JOIN a2.matriculases m2 " + "        GROUP BY a2.codasig" + "    ) subquery" + ")";

		try {
			Query<Object[]> query = session.createQuery(hql, Object[].class);
			List<Object[]> resultados = query.list();

			for (Object[] row : resultados) {
				BigInteger codAsig = (BigInteger) row[0];
				String nombreAsig = (String) row[1];
				Long numAlumnos = (Long) row[2];

				System.out.println("Código Asignatura: " + codAsig);
				System.out.println("Nombre Asignatura: " + nombreAsig);
				System.out.println("Número de Alumnos: " + numAlumnos);
				System.out.println("------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public static void mostrarAsignaturasPorCurso() {

		Session session = sesion.openSession();

		// Consulta para obtener todos los cursos
		String hqlCursos = "FROM Cursos c";
		try {
			Query<Cursos> queryCursos = session.createQuery(hqlCursos, Cursos.class);
			List<Cursos> cursos = queryCursos.list();

			// Iterar sobre cada curso
			for (Cursos curso : cursos) {

				System.out.printf("%n%-40s %-40s %n", "CODCURSO: " + curso.getCodcurso(),
						"DENOMINACIÓN: " + curso.getDenominacion());
				System.out.printf("%-40s %-40s %n", "PRECIO: " + curso.getPrecio(), "NIVEL: " + curso.getNivel());

				// Obtener número de alumnos en el curso
				long numAlumnosCurso = curso.getAlumnoses().size();

				// Obtener las asignaturas del curso
				Set<Cursoasig> cursoasigs = curso.getCursoasigs();
				long numAsignaturas = cursoasigs.size();

				System.out.printf("%-30s %-30s %n", "Número de alumnos: " + numAlumnosCurso,
						"Número de asignaturas: " + numAsignaturas);
				System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %n", "CODASIG", "NOMBREASIG", "PRECIOASIG",
						"TIPOASIG", "INCREMENTO", "NUM_ALUMNOS", "TOTALASIG");
				System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %n", "-------------------",
						"-------------------", "-------------------", "-------------------", "-------------------",
						"-------------------", "-------------------");

				// Variables para los totales
				int totalNumAlumnos = 0;
				double totalAsignaturas = 0.0;

				// Iterar sobre cada asignatura del curso
				for (Cursoasig cursoasig : cursoasigs) {
					Asignaturas asignatura = cursoasig.getAsignaturas();

					// Obtener los detalles de la asignatura
					BigInteger codAsig = asignatura.getCodasig();
					String nombreAsig = asignatura.getNombreasig();
					BigInteger precioAsig = asignatura.getPrecioasig();
					Character tipoAsig = asignatura.getTipoasig();
					long numAlumnosAsig = asignatura.getMatriculases().size();

					// Calcular el incremento en el precio según el tipo de asignatura
					double incremento = 0.0;
					switch (tipoAsig) {
					case 'A':
						incremento = precioAsig.doubleValue() * 0.05;
						break;
					case 'B':
						incremento = precioAsig.doubleValue() * 0.06;
						break;
					case 'C':
						incremento = precioAsig.doubleValue() * 0.08;
						break;
					case 'D':
						incremento = precioAsig.doubleValue() * 0.10;
						break;
					}

					// Calcular el total de la asignatura (número de alumnos * (precio +
					// incremento))
					double totalAsig = numAlumnosAsig * (precioAsig.doubleValue() + incremento);

					// Imprimir los detalles de la asignatura
					System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %n", codAsig, nombreAsig, precioAsig,
							tipoAsig, incremento, numAlumnosAsig, totalAsig);

					// Acumular los totales
					totalNumAlumnos += numAlumnosAsig;
					totalAsignaturas += totalAsig;
				}

				// Mostrar los totales por curso
				System.out.println(
						"------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %n", "TOTALES=>", "", "", "", "",
						totalNumAlumnos, totalAsignaturas);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	private static void insertarNuevosAlumnos() {
		
		// Abrir sesión
		Session session = sesion.openSession();
		Transaction transaction = session.beginTransaction();

		try {

			// Leer los datos de la nueva tabla
			String hqlNuevosAlumnos = "FROM Nuevosalumnos";

			// Realizar la consulta y agregar los datos a una lista
			Query<Nuevosalumnos> queryNuevosAlumnos = session.createQuery(hqlNuevosAlumnos, Nuevosalumnos.class);
			List<Nuevosalumnos> nuevosAlumnos = queryNuevosAlumnos.list();

			// Recorrer los datos de la lista
			for (Nuevosalumnos nuevo : nuevosAlumnos) {

				// Consulta para comprobar si el nombre del alumno ya está en la tabla alumnos
				String hqlCheckNombre = "SELECT COUNT(a.codalum) FROM Alumnos a WHERE a.nombre = :nombre";
				Query<Long> queryCheckNombre = session.createQuery(hqlCheckNombre, Long.class);
				queryCheckNombre.setParameter("nombre", nuevo.getNombre());
				Long countNombre = queryCheckNombre.uniqueResult();

				// Comprobar el resultado de la consulta
				if (countNombre > 0) {
					
					// Mostrar mensaje de error
					System.out.println("El alumno " + nuevo.getNombre() + " ya está registrado");
					continue;

				}

				// Lo mismo que he hecho antes pero con el código
				String hqlCheckCodigo = "SELECT COUNT(a.codalum) FROM Alumnos a WHERE a.codalum = :codalum";
				Query<Long> queryCheckCodigo = session.createQuery(hqlCheckCodigo, Long.class);
				queryCheckCodigo.setParameter("codalum", nuevo.getCodalum());
				Long countCodigo = queryCheckCodigo.uniqueResult();
				BigInteger nuevoCodigo = nuevo.getCodalum();
				
				if (countCodigo > 0) {

					// Obtengo el codigo mayor y asignar el siguiente
					String hqlMaxCodigo = "SELECT MAX(a.codalum) FROM Alumnos a";
					Query<BigInteger> queryMaxCodigo = session.createQuery(hqlMaxCodigo, BigInteger.class);
					BigInteger maxCodigo = queryMaxCodigo.uniqueResult();
					nuevoCodigo = maxCodigo.add(BigInteger.ONE);

					// Mostrar mensaje 
					System.out.println("El código " + nuevo.getCodalum() + " ya existe, asignamos el nuevo código: " +nuevoCodigo);
					
				}



				// Se determina el representante y el curso segun la poblacion
				BigInteger representante = null;
				BigInteger curso = null;

				switch (nuevo.getPoblacion().toLowerCase()) {

				case "talavera":

					representante = BigInteger.ONE;
					curso = BigInteger.ONE;
					break;

				case "toledo":

					representante = BigInteger.valueOf(8);
					curso = BigInteger.valueOf(4);
					break;

				default:

					representante = BigInteger.valueOf(11);
					curso = BigInteger.valueOf(5);
					break;

				}

				// Inserto el nuevo alumno en la tabla ALUMNOS
				Alumnos alumno = new Alumnos();
				alumno.setCodalum(nuevoCodigo);
				alumno.setNombre(nuevo.getNombre());
				alumno.setDireccion(nuevo.getDireccion());
				alumno.setPoblacion(nuevo.getPoblacion());
				alumno.setTelef(nuevo.getTelef());
				alumno.setAlumnos(session.get(Alumnos.class, representante)); 
				alumno.setCursos(session.get(Cursos.class, curso)); 

				session.persist(alumno);
				
				System.out.println("Se ha insertado el nuevo alumnno " + nuevo.getNombre() + " con el código" + nuevoCodigo);

			}

			transaction.commit();

		} catch (Exception e) {

			if (transaction != null) {
				transaction.rollback();
			}

			System.err.println("Error al ejecutar el ejercicio: " + e.getMessage());

		} finally {

			session.close();

		}

	}
	
	
	private static void menu() {

		System.out.println("---------------------------------------------------------------------------");
		System.out.println("\t................................................................");
		System.out.println("\t.  1. EJERCICIO 1: Mostrar Información Cursos.");
		System.out.println("\t.  2. EJERCICIO 2: Consultas.");
		System.out.println("\t.  3. EJERCIICO 3: Insertar Nuevos Alumnos.");
		System.out.println("\t.  0. Salir");
		System.out.println("\t.................................................................");
		System.out.println("---------------------------------------------------------------------------");
		System.out.print("TECLEA OPERACIÓN:");

	}

}
