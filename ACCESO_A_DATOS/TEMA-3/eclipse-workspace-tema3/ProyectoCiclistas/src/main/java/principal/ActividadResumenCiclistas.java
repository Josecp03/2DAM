package principal;

import java.math.BigInteger;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class ActividadResumenCiclistas {

	private static SessionFactory sesion;

	public static void main(String[] args) {

		// Quitar los INFO de hiberante
		LogManager.getLogManager().reset();
		Logger globalLogger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
		globalLogger.setLevel(java.util.logging.Level.OFF);

		// Inicializar la sesión
		sesion = Conexion.getSession();

		// Llamar al método
		mostrarResumenCiclistas(3);
		
	}

	private static void mostrarResumenCiclistas(int codigoEquipo) {

	    // Abrir sesión
	    Session session = sesion.openSession();

	    // Consulta parera contar cuantos equipos existen con ese código
	    Query<Long> equipoExistenteQuery = session.createQuery(
	            "SELECT COUNT(e.codigoequipo) FROM Equipos e WHERE e.codigoequipo = :codigoEquipo", Long.class);
	    
	    // Establecer el parámetro a la consulta
	    equipoExistenteQuery.setParameter("codigoEquipo", codigoEquipo);

	    // Guardar en una variable el resultado de la consulta
	    Long equipoExistente = equipoExistenteQuery.uniqueResult();

	    // Comprobar si el resultado de la consulta es 0
	    if (equipoExistente == 0) {
	    	
	    	// Mostrar mensaje de error
	        System.out.println("Error. El código de equipo no existe en la base de datos.");
	        
	        // Cerrar la seisón
	        session.close();
	        
	        // Salirse del método
	        return; 
	        
	    }
	    
	    
	    // Realizar consulta
	    Query<Object[]> cons = session.createQuery("SELECT e.codigoequipo, " +
	            "       e.nombreequipo," +
	            "       e.pais," +
	            "       jefe.nombreciclista," +
	            "       c.codigociclista," +
	            "       c.nombreciclista," +
	            "       (SELECT COUNT(et.codigoetapa) " +
	            "        FROM Etapas et " +
	            "        WHERE et.ciclistas.codigociclista = c.codigociclista) AS numEtapasGanadas," +
	            "       (SELECT COUNT(tp.codigotramo) " +
	            "        FROM Tramospuertos tp " +
	            "        WHERE tp.ciclistas.codigociclista = c.codigociclista) AS numTramosGanados," +
	            "       (SELECT COUNT(rc.numveces) " +
	            "        FROM ResumenCamisetas rc " +
	            "        WHERE rc.ciclistas.codigociclista = c.codigociclista) AS numVecesCamiseta " +
	            "FROM Equipos e " +
	            "JOIN e.ciclistases c " +
	            "JOIN Ciclistas jefe ON jefe.codigociclista = c.ciclistas.codigociclista " +
	            "WHERE e.codigoequipo = :codigoEquipo " +
	            "ORDER BY c.codigociclista", Object[].class);

	    // Establecer el parámetro a la consulta
	    cons.setParameter("codigoEquipo", codigoEquipo);

	    // Crear lista con los datos
	    List<Object[]> datos = cons.list();
	    
	    // Comprobar si no hay ciclistas en el equipo
	    if (datos.isEmpty()) {
	    	
	    	// Mostrar mensaje de error
	        System.out.println("El equipo existe, pero no tiene ciclistas asociados.");
	        
	        // Cerrar Sesión
	        session.close();
	        
	        // Salirse del método
	        return; 
	        
	    }
	    
	    // Variables para guardar el nombre del corredor con más etapas y tramos ganados
	    String corredorMasEtapas = "No Hay";
	    String corredorMasTramos = "No Hay";
	    long maxEtapas = 0;
	    long maxTramos = 0;

	    // Imprimir encabezado
	    System.out.println("COD-EQUIPO: " + datos.get(0)[0] + "    NOMBRE: " + datos.get(0)[1]);
	    System.out.println("PAIS: " + datos.get(0)[2] + ", Jefe de Equipo: " + datos.get(0)[3]);
	    System.out.println("----------------------------------------------------------------------------");
	    System.out.printf("%-10s %-40s %-20s %-20s %-20s %n", "CODIGO", "NOMBRE", "Etap Ganadas", "Tramos Ganados", "Nº VecesCamiseta");
	    System.out.printf("%-10s %-40s %-20s %-20s %-20s %n", "=========", "=======================================", "===================", "===================", "===================");

	    // Recorrer todos los datos de la consulta
	    for (Object[] fila : datos) {

	        // Descomponer cada fila
	        BigInteger codigoCiclista = (BigInteger) fila[4];
	        String nombreCiclista = (String) fila[5];
	        long numEtapasGanadas = ((Number) fila[6]).longValue();
	        long numTramosGanados = ((Number) fila[7]).longValue();
	        long numVecesCamiseta = ((Number) fila[8]).longValue();

	        // Actualizar corredor con más etapas ganadas
	        if (numEtapasGanadas > maxEtapas) {
	            maxEtapas = numEtapasGanadas;
	            corredorMasEtapas = nombreCiclista;
	        }

	        // Actualizar corredor con más tramos ganados
	        if (numTramosGanados > maxTramos) {
	            maxTramos = numTramosGanados;
	            corredorMasTramos = nombreCiclista;
	        }

	        // Imprimir datos formateados
		    System.out.printf("%-10s %-40s %-20s %-20s %-20s %n", codigoCiclista, nombreCiclista, numEtapasGanadas, numTramosGanados, numVecesCamiseta);

	    }

	    System.out.println("----------------------------------------------------------------------------");
	    System.out.println("Nombre/s de corredor/es con más etapas ganadas (si los hay): " + corredorMasEtapas);
	    System.out.println("Nombre/s de corredor/es con más tramos de montaña ganados (si los hay): " + (maxTramos > 0 ? corredorMasTramos : "No Hay"));

	    // Cerrar sesión
	    session.close();
	    
	}


}
