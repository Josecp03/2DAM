package principal;

import java.math.BigInteger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import clasesMapeadas.*;

public class InsertarEmpleado {
	private static SessionFactory sesion;

	public static void main(String[] args) {

		sesion = Conexion.getSession(); //SesionFactory
		System.out.println("------------------------");
		insertarunempleado();

		sesion.close();
	}

	private static void insertarunempleado() {
		
		Session session = sesion.openSession(); //creo una sesión de trabajo
		Transaction tx = session.beginTransaction();

		System.out.println("Inserto un EMPLEADO EN EL DEPARTAMENTO 10.");

		double salario = 1500;// inicializo el salario
		double comision = 10; // inicializo la comisión

		Empleados em = new Empleados(); // creo un objeto empleados
		em.setEmpNo(BigInteger.valueOf(4455)); // el número de empleado es 4455
		em.setApellido("PEPE");
		em.setOficio("VENDEDOR");
		em.setSalario(salario);
		em.setComision(comision);

		Departamentos d = new Departamentos(); // creo un objeto Departamentos
		d.setDeptNo(BigInteger.valueOf(10)); // el número de dep es 10
		em.setDepartamentos(d);

		// fecha de alta
		java.util.Date hoy = new java.util.Date();
		java.sql.Date fecha = new java.sql.Date(hoy.getTime());
		em.setFechaAlt(fecha);

		try {
			session.persist(em);
			tx.commit();
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
		session.close();
		
	}
}

	
