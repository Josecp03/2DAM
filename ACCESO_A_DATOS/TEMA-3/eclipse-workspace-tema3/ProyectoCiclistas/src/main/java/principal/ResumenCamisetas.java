package principal;

import java.math.BigInteger;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import clasesMapeadas.*;

public class ResumenCamisetas {
	
	// Atributo de conexión
	private static SessionFactory sesion;

	public static void main(String[] args) {
		
		// Quitar los INFO de hiberante
		LogManager.getLogManager().reset();
		Logger globalLogger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
		globalLogger.setLevel(java.util.logging.Level.OFF);
		
		// Inicializar la sesión
		sesion = Conexion.getSession(); 
		
		// Llamar al método
		imprimirConsulta();
		
	}

	private static void imprimirConsulta() {
	    // Abrir sesión
	    Session session = sesion.openSession();
	    Transaction transaction = session.beginTransaction();

	    // Realizar consulta
	    Query<Object[]> cons = session.createQuery(
	            "select " +
	            "    l.ciclistas.equipos.codigoequipo as codigoEquipo, " +
	            "    l.ciclistas.equipos.nombreequipo as nombreEquipo, " +
	            "    c.codigocamiseta as codigoCamiseta, " +
	            "    l.ciclistas.codigociclista as codigoCiclista, " +
	            "    l.ciclistas.nombreciclista as nombreCiclista, " +
	            "    count(l) as numVeces, " +
	            "    count(l) * c.importepremio as importePremio " +
	            "from " +
	            "    Camisetas c " +
	            "join " +
	            "    c.llevas l " +
	            "group by " +
	            "    l.ciclistas.equipos.codigoequipo, l.ciclistas.equipos.nombreequipo, " +
	            "    c.codigocamiseta, l.ciclistas.codigociclista, l.ciclistas.nombreciclista, c.importepremio " +
	            "order by " +
	            "    l.ciclistas.equipos.codigoequipo, l.ciclistas.codigociclista", 
	            Object[].class);

	    // Crear lista con los datos
	    List<Object[]> datos = cons.list();

	    // Inicializar el equipo actual
	    String equipoActual = "";

	    // Imprimir la cabecera
	    System.out.println("Llenar Tabla RESUMEN-CAMISETAS");

	    for (Object[] par : datos) {
	        // Descomponer cada fila
	        BigInteger codigoEquipo = (BigInteger) par[0];
	        String nombreEquipo = (String) par[1];
	        BigInteger codigoCamiseta = (BigInteger) par[2];
	        BigInteger codigoCiclista = (BigInteger) par[3];
	        String nombreCiclista = (String) par[4];
	        Long numVeces = ((Number) par[5]).longValue();
	        Double importePremio = ((Number) par[6]).doubleValue(); 

	        // Comprobar si el nombre del equipo actual es igual a la variable
	        if (!equipoActual.equals(nombreEquipo)) {
	            equipoActual = nombreEquipo;

	            // Imprimir cabecera
	            System.out.printf("%n %-70s %-15s %-15s %-30s %n", "Equipo : " + codigoEquipo + ", " + nombreEquipo, "CAMISETA", "NºVECES", "IMPORTE PRECIO");
	            System.out.println("----------------------------------------------------------------------------------------------------------------------");
	        }

	        // Imprimir datos formateados
	        System.out.printf("%-70s %-15s %-15s %-30s %n", "\tInsertado : " + codigoCiclista + " " + nombreCiclista, codigoCamiseta, numVeces, importePremio);

	        // Crear objeto ResumenCamisetas
	        ResumenCamisetasId id = new ResumenCamisetasId(codigoEquipo, codigoCiclista, codigoCamiseta);

	        // Establecer relaciones (asegurarse de que las entidades existen)
	        Camisetas camiseta = session.get(Camisetas.class, codigoCamiseta);
	        Ciclistas ciclista = session.get(Ciclistas.class, codigoCiclista);
	        Equipos equipo = session.get(Equipos.class, codigoEquipo);

	        if (camiseta == null || ciclista == null || equipo == null) {
	            System.out.println("Error: Alguna de las entidades relacionadas es nula.");
	            continue; // Salta este registro
	        }

	        ResumenCamisetas resumen = new ResumenCamisetas();
	        resumen.setId(id);
	        resumen.setCamisetas(camiseta);
	        resumen.setCiclistas(ciclista);
	        resumen.setEquipos(equipo);
	        resumen.setNumveces(BigInteger.valueOf(numVeces));
	        resumen.setImportepremio(BigInteger.valueOf(importePremio.longValue()));

	        // Guardar el objeto en la base de datos
	        session.save(resumen);
	    }

	    // Confirmar la transacción
	    transaction.commit();

	    // Cerrar sesión
	    session.close();
	}






}
