package principal;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import clasesMapeadas.*;

public class ListarDepartamentos {

	private static SessionFactory sesion;

	public static void main(String[] args) {

		sesion = Conexion.getSession(); // SesionFactory

		listardepartamentos();

	}

	private static void listardepartamentos() {
		Session session = sesion.openSession();
		Departamentos depar = new Departamentos();

		Query<Departamentos> q = session.createQuery("from Departamentos");
		List<Departamentos> lista = q.getResultList(); // )list();
		int num = lista.size();
		System.out.println("Número de departamentos: " + num);
		for (int i = 0; i < num; i++) {
			// extraer el objeto
			depar = (Departamentos) lista.get(i);
			// obtenemos empleados
			Set<Empleados> listaemple = depar.getEmpleadoses();
			Iterator<Empleados> it = listaemple.iterator();
			System.out.println();
			System.out.println("Num dep: " + depar.getDeptNo() + " Nombre Dep:" + depar.getDnombre() + "  Localidad:"
					+ depar.getLoc() + "  Número de empleados: " + depar.getEmpleadoses().size());
			System.out.printf("%10s %15s %15s %15s %15s %n", "EMPNO", "APELLIDO", "OFICIO", "FECHAALTA", "SALARIO");
			System.out.printf("%10s %15s %15s %15s %15s %n", "----------", "---------------", "---------------",
					"---------------", "---------------");
			float totalsalario = 0;
			while (it.hasNext()) {
				Empleados emple = new Empleados();
				emple = it.next();
				System.out.printf("%10s %15s %15s %15s %15s %n", emple.getEmpNo(), emple.getApellido(),
						emple.getOficio(), emple.getFechaAlt(), emple.getSalario());
				totalsalario = (float) (totalsalario + emple.getSalario());
			}
			System.out.printf("%10s %15s %15s %15s %15s %n", "----------", "---------------", "---------------",
					"---------------", "---------------");
			System.out.printf("%-26s %15s %15s %15s %n", "Total salario: ", "", "", totalsalario);
			System.out.printf("%10s %15s %15s %15s %15s %n", "----------", "---------------", "---------------",
					"---------------", "---------------");
		}

		session.close();
	}

}
