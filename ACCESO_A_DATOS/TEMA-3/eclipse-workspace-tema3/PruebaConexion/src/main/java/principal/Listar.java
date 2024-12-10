package principal;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import clasesMapeadas.Departamentos;
import clasesMapeadas.Empleados;
import jakarta.persistence.TypedQuery;


public class Listar {

	private static SessionFactory sesion;

	public static void main(String[] args) {

		sesion = Conexion.getSession(); //SesionFactory
		
		listaremplesdeundep(10);
		
		
	}

	private static void listaremplesdeundep(int dep) {
		Session session = sesion.openSession();
		Empleados emple = new Empleados();
		System.out.println("-------------------------------");
		Query<Empleados> q = 
                    session.createQuery("from Empleados e where e.departamentos.deptNo= " + dep);
		List<Empleados> lista = q.list();
		int num = lista.size();
		System.out.println("Número de empleados del departamento " + dep + " : " + num);
		for (int i = 0; i < num; i++) {
			emple = (Empleados) lista.get(i);
			System.out.println(emple.getEmpNo() + "*" + emple.getApellido());
		}

		session.close();
	}


}
