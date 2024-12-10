package principal;

import java.math.BigInteger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import clasesMapeadas.*;


public class ModificarDepartamento {

	private static SessionFactory sesion;

	
	public static void main(String[] args) {
		
		sesion = Conexion.getSession(); //SesionFactory
		// insertamodifdepart(15,"DEP ENFERMERIA", "TALAVERA");
		
		// actualizardepalempleado(7566,20);
		
		insertaempleadoalsetdedepartamento(20, 7900);
		
	}
	
	
	private static void insertamodifdepart(int nu, String nom, String loc) {

		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		System.out.println("Cargo departamento.");
		Departamentos dep = (Departamentos) session.get(Departamentos.class, nu);
		System.out.println("==============================");
		System.out.println("DATOS DEL DEPARTAMENTO " + nu);
		if (dep == null) {
			System.out.println("El departamento no existe, LO CREO");
			dep = new Departamentos();
			dep.setDeptNo(BigInteger.valueOf(nu));
			dep.setDnombre(nom);
			dep.setLoc(loc);
			session.persist(dep);
		} else {
			System.out.println("El departamento existe, LO MODIFICO");
			dep.setDnombre(nom);
			dep.setLoc(loc);
			session.persist(dep);
		}
		tx.commit();
		session.close();
	}

	private static void actualizardepalempleado(int emp, int nu ) {
		
		Session session = sesion.openSession();
		Empleados emple = (Empleados) session.get(Empleados.class, emp);
		if (emple == null) {
			System.out.println("El Empleado no existe. No se puede actualizar.");
		} else {
			Departamentos dep = (Departamentos) session.get(Departamentos.class, nu);
			if (dep == null) 
				System.out.println("El departamento no existe. No se puede actualizar.");
				else {
					Transaction tx = session.beginTransaction();
					emple.setDepartamentos(dep);
					System.out.println("Empleado " + emp + " actualizado al departamento " + nu);
					// en desuso session.update(emple);
					session.merge(emple);
					tx.commit();
					}
			}
			session.close();
		}
	
	private static void insertaempleadoalsetdedepartamento(int nu, int emp) {
		Session session = sesion.openSession();

		Departamentos dep = (Departamentos) session.get(Departamentos.class, nu);
		if (dep == null) {
			System.out.println("El departamento no existe. No se puede insertar.");
		} else {
			// compruebo empleado
			Empleados emple = (Empleados) session.get(Empleados.class, (short) emp);
			if (emple == null) {
				System.out.println("El Empleado no existe. No se puede insertar.");
			} else {
				// lo añado al set
				Transaction tx = session.beginTransaction();
				dep.getEmpleadoses().add(emple);
				System.out.println("Empleado " + emp + " añadido al departamento " + nu);
				session.update(dep);          
				tx.commit();
			}
		}
		session.close();

	}


	
	
	
}
