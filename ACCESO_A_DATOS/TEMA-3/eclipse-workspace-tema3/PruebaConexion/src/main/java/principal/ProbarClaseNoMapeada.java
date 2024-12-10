package principal;

import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class ProbarClaseNoMapeada {

	private static SessionFactory sesion;

	
	public static void main(String[] args) {
		
		LogManager.getLogManager().reset();
		Logger globalLogger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
		globalLogger.setLevel(java.util.logging.Level.OFF);

		sesion = Conexion.getSession(); // SesionFactory
		consultaconobjetos();
		
	}
	
	private static void consultaconobjetos() {
		Session session = sesion.openSession();
		Query cons = session.createQuery("select d.deptNo, count(em.empNo), "
				+ " coalesce(avg(em.salario),0), "
				+ " d.dnombre from Departamentos d left join d.empleadoses em  " 
				+ " group by d.deptNo,d.dnombre");
		System.out.printf("%n%10s %-15s %14s %-14s", "NUMERO DEP", "NOMBRE", 
	             "SALARIO MEDIO", "NUM EMPLES");
		System.out.printf("%n%10s %-15s %14s %-14s", "----------", "---------------",
	       "--------------", "--------------");

		List filas = cons.list();
		for (int i = 0; i < filas.size(); i++) {
			Object[] filaActual = (Object[]) filas.get(i); // Acceso a una fila
			System.out.printf("%n%10s %-15s %14s %-14s", filaActual[0], 
	                filaActual[3], filaActual[2], filaActual[1]);
		}
	}

	
}
