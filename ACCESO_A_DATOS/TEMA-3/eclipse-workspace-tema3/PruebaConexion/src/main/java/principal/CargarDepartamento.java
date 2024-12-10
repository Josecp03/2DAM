package principal;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clasesMapeadas.*;


public class CargarDepartamento {
	
	private static SessionFactory sesion;

	public static void main(String[] args) {
		
		sesion = Conexion.getSession(); //SesionFactory
		
		cargardeparget(10);
		cargardepar(10);
		cargardeparget(88);
		cargardepar(88);
		
		
		sesion.close();
		
	}
	
	
	private static void cargardepar(int nu) {
		
		
		Session session = sesion.openSession();
		try {
			Departamentos dep = (Departamentos) session.load(Departamentos.class, nu);
			System.out.println("Nombre: " + dep.getDnombre());
			System.out.println("Localidad: " + dep.getLoc());
		} catch (ObjectNotFoundException ob) {
			System.out.println("NO EXISTE EL DEPARTAMENTO.");
		}
		session.close();
	}
	
	private static void cargardeparget(int nu) {
		Session session = sesion.openSession();
		Departamentos dep = (Departamentos) session.get(Departamentos.class, nu);
		if (dep == null) {
			System.out.println("El departamento no existe");
		} else {
			System.out.println("Nombre Dep:" + dep.getDnombre());
			System.out.println("Localidad:" + dep.getLoc());
		}
		session.close();
	}


	
	
}
