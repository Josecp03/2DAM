package principal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class ActividadConsultas {

	private static SessionFactory sesion;

	public static void main(String[] args) {

		// Quitar los INFO de hiberante
		LogManager.getLogManager().reset();
		Logger globalLogger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
		globalLogger.setLevel(java.util.logging.Level.OFF);

		// Inicializar la sesión
		sesion = Conexion.getSession();

		// Llamar al método de la primera consulta
		primeraConsulta();
		
		// Llamar al método de la segunda consulta
		segundaConsulta();
		
		// Llamar al método de la tercera consulta
		terceraConsulta();
		
		// Llamar al método de la cuarta consulta
		cuartaConsulta();

	}

	private static void cuartaConsulta() {
		
		System.out.println("\n\n4. CUARTA CONSULTA");

		// Abrir sesión
		Session session = sesion.openSession();

		// Realizar consulta
		Query<Object[]> cons = session.createQuery("select "
				+ "	e.codigoequipo as codigoEquipo,"
				+ "	e.nombreequipo as nombreEquipo,"
				+ "	c.codigocamiseta as codigoCamiseta,"
				+ "	c.color as colorCamiseta,"
				+ "	count(*) as vecesLlevadas"
				+ " from "
				+ "	Equipos e"
				+ " join"
				+ "	ResumenCamisetas rc on rc.equipos.codigoequipo = e.codigoequipo"
				+ " join"
				+ "	Camisetas c on c.codigocamiseta = rc.camisetas.codigocamiseta"
				+ " group by"
				+ "	e.codigoequipo,"
				+ "	e.nombreequipo,"
				+ "	c.codigocamiseta,"
				+ "	c.color"
				+ " order by"
				+ "	e.codigoequipo", Object[].class);


		// Crear lista con los datos
		List<Object[]> datos = cons.list();

		// Imprimir cabecera
		System.out.printf("%n%-20s %-40s %-40s %-20s %-20s %n", "COD EQUIPO", "NOM EQUIPO", "COD CAMISETA","COLOR CAMISETA", "VECES LLEVADA");
		System.out.printf("%-20s %-40s %-40s %-20s %-20s %n", "===================", "=======================================", "=======================================", "===================", "===================");

		
	    // Iterar y mostrar los datos
	    for (Object[] fila : datos) {
	        
	        // Descomponer cada fila
	    	BigInteger codigoEquipo = (BigInteger) fila[0];
	    	String nombreEquipo = (String) fila[1];
	    	BigInteger codigoCamiseta = (BigInteger) fila[2];
	    	String colorCamiseta = (String) fila[3];
	    	long vecesLlevada = ((Number) fila[4]).longValue();

	    	
	        // Imprimir los datos de forma legible
			System.out.printf("%-20s %-40s %-40s %-20s %-20s %n", codigoEquipo, nombreEquipo, codigoCamiseta, colorCamiseta, vecesLlevada);

	    }

	    // Cerrar sesión
	    session.close();
		
		
	}

	private static void terceraConsulta() {
		
		System.out.println("\n\n3. TECERA CONSULTA");

		// Abrir sesión
		Session session = sesion.openSession();

		// Realizar consulta
		Query<Object[]> cons = session.createQuery("select"
				+ "	e.codigoequipo as codigoEquipo,"
				+ "	e.nombreequipo as nombreEquipo,"
				+ "	c.nombreciclista as nombreCiclista,"
				+ "	count(*) as vecesLlevadas"
				+ " from "
				+ "	Equipos e"
				+ " join"
				+ "	Ciclistas c on c.equipos.codigoequipo = e.codigoequipo"
				+ " join"
				+ "	Lleva l on l.ciclistas.codigociclista = c.codigociclista"
				+ " join"
				+ "	Camisetas ca on ca.codigocamiseta = l.camisetas.codigocamiseta"
				+ " where "
				+ "	ca.color LIKE 'Lunares'"
				+ " group by"
				+ "	e.codigoequipo,"
				+ "    e.nombreequipo,"
				+ "    c.nombreciclista"
				+ " order by"
				+ "	e.codigoequipo,"
				+ "	c.nombreciclista", Object[].class);


		// Crear lista con los datos
		List<Object[]> datos = cons.list();

		// Imprimir cabecera
		System.out.printf("%n%-20s %-40s %-40s %-20s %n", "COD EQUIPO", "NOM EQUIPO", "NOM CICLISTA", "VECES LLEVADA");
		System.out.printf("%-20s %-40s %-40s %-20s %n", "===================", "=======================================", "=======================================", "===================");

		
	    // Iterar y mostrar los datos
	    for (Object[] fila : datos) {
	        
	        // Descomponer cada fila
	    	BigInteger codigoEquipo = (BigInteger) fila[0];
	    	String nombreEquipo = (String) fila[1];
	    	String nombreCiclista = (String) fila[2];
	    	long vecesLlevada = ((Number) fila[3]).longValue();

	    	
	        // Imprimir los datos de forma legible
			System.out.printf("%-20s %-40s %-40s %-20s %n", codigoEquipo, nombreEquipo, nombreCiclista, vecesLlevada);

	    }

	    // Cerrar sesión
	    session.close();
		
		
	}

	private static void segundaConsulta() {
		
		System.out.println("\n\n2. SEGUNDA CONSULTA");

		// Abrir sesión
		Session session = sesion.openSession();

		// Realizar consulta
		Query<Object[]> cons = session.createQuery("select"
				+ "	c.codigociclista as codigoCiclista,"
				+ "	c.nombreciclista as nombreCiclista,"
				+ "	e.codigoetapa as codigoEtapa,"
				+ "	e.tipoetapa as tipoEtapa,"
				+ "	t.codigotramo as codigoTramo,"
				+ "	t.nombretramo as nombreTramo,"
				+ "	t.categoria as categoria"
				+ " from"
				+ "	Ciclistas c"
				+ " join"
				+ "	Tramospuertos t on t.ciclistas.codigociclista = c.codigociclista"
				+ " join"
				+ "	Etapas e on e.ciclistas.codigociclista = c.codigociclista"
				+ " where"
				+ "	t.pendiente LIKE '%5,5%%'"
				+ " order by"
				+ "	c.codigociclista", Object[].class);


		// Crear lista con los datos
		List<Object[]> datos = cons.list();

		// Imprimir cabecera
		System.out.printf("%n%-20s %-20s %-20s %-20s %-20s %-40s %-20s %n", "COD CICLISTA", "NOM CICLISTA", "COD ETAPA", "TIPO ETAPA", "COD TRAMO", "NOM TRAMO", "CATEGORIA");
		System.out.printf("%-20s %-20s %-20s %-20s %-20s %-40s %-20s %n", "===================", "===================", "===================", "===================", "===================", "=======================================", "===================");

		
	    // Iterar y mostrar los datos
	    for (Object[] fila : datos) {
	        
	        // Descomponer cada fila
	    	BigInteger codigoCiclista = (BigInteger) fila[0];
	    	String nombreCiclista = (String) fila[1];
	    	BigInteger codigoEtapa = (BigInteger) fila[2];
	    	String tipoEtapa = (String) fila[3];
	    	BigInteger codigoTramo = (BigInteger) fila[4];
	    	String nombreTramo = (String) fila[5];
	    	BigInteger categoria = (BigInteger) fila[6];
	    	
	        // Imprimir los datos de forma legible
			System.out.printf("%-20s %-20s %-20s %-20s %-20s %-40s %-20s %n", codigoCiclista, nombreCiclista, codigoEtapa, tipoEtapa, codigoTramo, nombreTramo, categoria );

	    }

	    // Cerrar sesión
	    session.close();

	}

	private static void primeraConsulta() {

		System.out.println("\n\n1. PRIMERA CONSULTA");
		
		// Abrir sesión
		Session session = sesion.openSession();

		// Realizar consulta
		Query<Object[]> cons = session.createQuery("select"
				+ "	e.codigoetapa,"
				+ "	e.km,"
				+ "	e.pobsalida,"
				+ "	e.pobllegada,"
				+ "	c.nombreciclista"
				+ " from"
				+ "	Etapas e"
				+ " join"
				+ "	Ciclistas c on c.codigociclista = e.ciclistas.codigociclista"
				+ " where "
				+ "	e.pobsalida = e.pobllegada and e.tipoetapa IN ('Media MontaÃ±a', 'MontaÃ±a')"
				+ ""
				+ "", Object[].class);


		// Crear lista con los datos
		List<Object[]> datos = cons.list();

		// Imprimir cabecera
		System.out.printf("%n%-20s %-10s %-40s %-40s %-40s %n", "COD ETAPA", "KM", "POB SALIDA", "POB LLEGADA", "CICLISTA GANADOR");
		System.out.printf("%-20s %-10s %-40s %-40s %-40s %n", "===================", "=========", "=======================================", "=======================================", "=======================================");

		
	    // Iterar y mostrar los datos
	    for (Object[] fila : datos) {
	        
	        // Descomponer cada fila
	        BigInteger codigoEtapa = (BigInteger) fila[0];
	        BigDecimal km = (BigDecimal) fila[1]; 
	        String pobSalida = (String) fila[2];
	        String pobLlegada = (String) fila[3];
	        String nombreCiclista = (String) fila[4];

	        // Imprimir los datos de forma legible
			System.out.printf("%-20s %-10s %-40s %-40s %-40s %n", codigoEtapa, km, pobSalida, pobLlegada, nombreCiclista);

	    }

	    // Cerrar sesión
	    session.close();

	}

}