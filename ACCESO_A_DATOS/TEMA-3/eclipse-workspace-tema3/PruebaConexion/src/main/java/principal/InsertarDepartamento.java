package principal;

import java.math.BigInteger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import clasesMapeadas.*;

public class InsertarDepartamento {
	
	private static SessionFactory sesion;

	public static void main(String[] args) {

		sesion = Conexion.getSession(); // Creo la sessionFactory una única vez.

		insertardepartamento();

		sesion.close();
	}

	private static void insertardepartamento() {
		Session session = sesion.openSession(); // creo una sesión de trabajo
		Transaction tx = session.beginTransaction();

		Departamentos dep = new Departamentos();
		dep.setDeptNo(BigInteger.valueOf(64));
		dep.setDnombre("MARKETs");
		dep.setLoc("GUADALAJARA");

		try {
			session.persist(dep);
			tx.commit();
			System.out.println("Reg INSERTADO.");

// } catch (javax.persistence.PersistenceException e) { //Si no se dispara esta

		} catch (jakarta.persistence.PersistenceException e) {

			if (e.getMessage().contains("org.hibernate.exception.ConstraintViolationException")) {
				System.out.println("CLAVE DUPLICADA. DEPARTAMENTO YA EXISTE");
			} else if (e.getMessage().contains("org.hibernate.exception.DataException")) {
				System.out.println("ERROR EN LOS DATOS DE DEPARTAMENTO, DEMASIADOS CARACTERES");
			} else if (e.getMessage().contains("org.hibernate.exception.GenericJDBCException")) {
				System.out.println("ERROR JDBC. NO SE HA PODIDO EJECUATR LA CONSULTA");
			} else
				System.out.println("HA ocurrido un error: " + e.getMessage());

		} catch (Exception e) {
			System.out.println("ERROR NO CONTROLADO....");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		session.close(); // cierro la sesión de trabajo

	}

}
