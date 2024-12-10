package principal;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import clasesMapeadas.Departamentos;

public class ConsultarDepartamentos {

	private static SessionFactory sesion;

	public static void main(String[] args) {
		
		sesion = Conexion.getSession(); //SesionFactory

		listardepartamentos();
	}
	
	private static void listardepartamentos() {
		Session session = sesion.openSession();
		Departamentos depar = new Departamentos();
		System.out.println("-------------------------------");

		Query<Departamentos> q = session.createQuery("from Departamentos");
		List<Departamentos> lista = q.list();
		int num = lista.size();
		System.out.println("Número de departamentos: " + num);
		for (int i = 0; i < num; i++) {
			// extraer el objeto
			depar = (Departamentos) lista.get(i);
			System.out.println(depar.getDeptNo() + "*" + depar.getDnombre());
		}
		
		session.close();
	}

	
	
}
