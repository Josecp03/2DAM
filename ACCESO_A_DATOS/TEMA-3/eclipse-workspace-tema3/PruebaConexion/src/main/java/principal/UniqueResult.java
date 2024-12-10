package principal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import clasesMapeadas.*;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class UniqueResult {
	
	private static SessionFactory sesion;

	
	public static void main(String[] args) {

		LogManager.getLogManager().reset();
		Logger globalLogger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
		globalLogger.setLevel(java.util.logging.Level.OFF);
		
		sesion = Conexion.getSession(); // SesionFactory

		consultauniqueresul();

	}
	
	private static void consultauniqueresul() {
		
		Session session = sesion.openSession();
		//Visualiza los datos del departamento 10  
		System.out.println("------------------------");
		Departamentos depart = (Departamentos) session.createQuery(
			    "from Departamentos as dep where dep.deptNo = 10").uniqueResult();
		System.out.println(depart.getLoc() +"*"+depart.getDnombre());	

		//Visualiza los datos del departamento con nombre CONTABILIDAD
		System.out.println("------------------------");
		depart = (Departamentos) session.createQuery("from Departamentos as dep where dep.dnombre = 'CONTABILIDAD'").uniqueResult();
		System.out.println(depart.getLoc() +"*"+depart.getDeptNo());

		System.out.println("------------------------");
		Long cont =  (Long) session.createQuery("select count(*) from Empleados ").uniqueResult();
		System.out.println("Número de empleados: " + cont);

		System.out.println("------------------------");
		Double media =  (double) session.createQuery("select avg(salario) from Empleados ").uniqueResult();
		System.out.println("Media de salario de empleados: " + media);

			
		System.out.println("------------------------");
		Double maxi = (Double)session.createQuery("select max(salario) from Empleados ").uniqueResult();
		System.out.println("Máximo de salario de empleados: " + maxi);

		session.close();
			
		}


}
