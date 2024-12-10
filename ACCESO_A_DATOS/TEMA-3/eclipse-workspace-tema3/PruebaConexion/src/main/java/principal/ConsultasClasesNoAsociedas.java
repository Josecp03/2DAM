package principal;

import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import clasesMapeadas.*;

public class ConsultasClasesNoAsociedas {

	private static SessionFactory sesion;

	public static void main(String[] args) {

		LogManager.getLogManager().reset();
		Logger globalLogger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
		globalLogger.setLevel(java.util.logging.Level.OFF);

		sesion = Conexion.getSession(); // SesionFactory
		consultasobjetos();

	}

	private static void consultasobjetos() {
		Session session = sesion.openSession();
		Query cons = session.createQuery("from Empleados e, Departamentos d where  e.departamentos.deptNo=d.deptNo order by e.apellido");
		List datos = cons.list();
		for (int i = 1; i < datos.size(); i++) {
			Object[] par = (Object[]) datos.get(i);
			Empleados em = (Empleados) par[0]; // objeto empleado el primero
			Departamentos de = (Departamentos) par[1]; // objeto departamento el segundo
			System.out.println(em.getApellido() + "*" + em.getSalario() + "*" + de.getDnombre() + "*" + de.getLoc());
		}
		session.close();
	}

}
