package principal;

import java.math.BigInteger;

import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import clasesMapeadas.*;

public class Principal {

	public static void main(String[] args) {

		// Inicializa el entorno Hibernate
		Configuration cfg = new Configuration().configure();
		// Crea el ejemplar de sesion factory (fabrica de sesiones)
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		// Obtiene un objeto session
		Session sesion = sessionFactory.openSession();
		Transaction tx = sesion.beginTransaction();

		System.out.println("Inserto una fila en la tabla DEPARTAMENTOS.");

		Departamentos dep = new Departamentos();
		dep.setDeptNo(BigInteger.valueOf(63));
		dep.setDnombre("MARKETING");
		dep.setLoc("GUADALAJARA");
		try {
			sesion.save(dep);
			tx.commit();
			System.out.println("REGISTRO GRABADO ");
		} catch (JDBCException j) {
			System.out.println("Codigo error: " + j.getErrorCode());
			System.out.println("Mensaje : " + j.getMessage());
		} catch (Exception e) {
			System.out.println("Codigo error: " + e.hashCode());
			System.out.println("Mensaje : " + e.getMessage());
		}
		sesion.close();
		sessionFactory.close();

	}

}
