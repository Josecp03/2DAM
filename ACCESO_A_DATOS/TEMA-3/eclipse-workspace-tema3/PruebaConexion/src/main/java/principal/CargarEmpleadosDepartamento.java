package principal;

import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import clasesMapeadas.*;


public class CargarEmpleadosDepartamento {

	private static SessionFactory sesion;

	
	public static void main(String[] args) {
		
		sesion = Conexion.getSession(); //SesionFactory
		verdepartamento(10);		
		verdepartamento(88);

		
	}

	private static void verdepartamento(int nu) {
		Session session = sesion.openSession();
		System.out.println("Cargo departamento.");

		Departamentos dep = (Departamentos) session.get(Departamentos.class, nu);
		System.out.println("==============================");
		System.out.println("DATOS DEL DEPARTAMENTO " + nu);
		if (dep == null) {
			System.out.println("El departamento no existe");
		} else {
			System.out.println("Nombre Dep:" + dep.getDnombre());
			System.out.println("Localidad:" + dep.getLoc());
			System.out.println("==============================");
			System.out.println("EMPLEADOS DEL DEPARTAMENTO");

			Set<Empleados> listaemple = dep.getEmpleadoses();
			// obtenemos empleados
			Iterator<Empleados> it = listaemple.iterator();
			System.out.println("Número de empleados: " + listaemple.size());
			while (it.hasNext()) {
				Empleados emple = new Empleados();
				emple = it.next();
				System.out.println(emple.getApellido() + " * " + 
                              emple.getSalario());
			}
		}
		System.out.println("==============================");
		session.close();
	}

	
}
