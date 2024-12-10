package principal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import clasesMapeadas.Departamentos;

public class BorrarDepartamento {
	
	private static SessionFactory sesion;

	public static void main(String[] args) {
		
		sesion = Conexion.getSession(); //SesionFactory
		borrardepar(63);
		
	}
	
	private static void borrardepar(int nu) {
		Session session = sesion.openSession();
		System.out.println("Cargo departamento.");

		Departamentos dep = (Departamentos) session.get(Departamentos.class, nu);
		if (dep == null) {
			System.out.println("El departamento no existe. NO SE PUEDE BORRAR.");
		} else {
			try {
				Transaction tx = session.beginTransaction();
				session.remove(dep);
				tx.commit();
			} catch (jakarta.persistence.PersistenceException e) {
				System.out.println("NO SE PUEDE BORRRAR. TIENE REGISTROS RELACIONADOS");
			}

		}
		session.close();
	}


}
