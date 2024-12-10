package principal;

import java.util.logging.LogManager;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clasesMapeadas.*;

public class Principal {
	
	private static SessionFactory sesion;

	public static void main(String[] args) {
		
		LogManager.getLogManager().reset();
		Logger globalLogger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
		globalLogger.setLevel(java.util.logging.Level.OFF);
		sesion = Conexion.getSession(); 
		
		cargarCamisetas(10);
		
		
	}
	
	private static void cargarCamisetas(int numero) {
		Session session = sesion.openSession();
		Camisetas camiseta = (Camisetas) session.get(Camisetas.class, numero);
		if (camiseta == null) {
			System.out.println("La camiseta no existe");
		} else {
			System.out.println(camiseta.getCodigocamiseta() + " | " + camiseta.getColor());
		}
		session.close();
	}

}
