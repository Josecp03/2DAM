package principal;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;


import clasesMapeadas.*;


import clasesMapeadas.Empleados;

public class ConsultaParametros {
	
	private static SessionFactory sesion;

	
	public static void main(String[] args) {

		LogManager.getLogManager().reset();
		Logger globalLogger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
		globalLogger.setLevel(java.util.logging.Level.OFF);
		
		sesion = Conexion.getSession(); // SesionFactory

		consultasconparametros();

	}
	
	private static void consultasconparametros() {
		
		Session session = sesion.openSession();

		// El siguiente ejemplo consulta el empleado con numero 7369
		// utiliza un parámetro nombrado
		String hql = "from Empleados where empNo = :numemple";
		// UTILIZAMOS ESTA QUERY q PARA TODAS LAS CONSULTAS

		Query q = session.createQuery(hql);
		q.setParameter("numemple", (short) 7369);
		Empleados emple = (Empleados) q.uniqueResult();

		System.out.println("------------------------");
		System.out.println("Empleado número 7369");

		System.out.printf("%s, %s %n", emple.getApellido(), emple.getOficio());

		// El siguiente ejemplo consulta los empleados cuyo número de departamento es 10
		// y el oficio DIRECTOR. Utiliza parámetros nombrados
		hql = "from Empleados emp where emp.departamentos.deptNo = :ndep and emp.oficio = :ofi";
		q = session.createQuery(hql);
		System.out.println("------------------------");
		System.out.println("Directores del dep 10");
		q.setParameter("ndep", (byte) 10);
		q.setParameter("ofi", "DIRECTOR");
		List<Empleados> lista = q.list();
		emple = new Empleados();
		for (int i = 0; i < lista.size(); i++) {
			emple = lista.get(i);
			System.out.println(emple.getApellido());
		}



		// El mismo ejemplo con parámetros posicionales de estilo JDBC (el uso de estos
		// parámetros se considera obsoleto por lo que se recomienda usar los parámetros
		// nombrados) quedaría así:

//		hql = "from Empleados emp where emp.departamentos.deptNo = ?0 and emp.oficio = ?1";
//		q = session.createQuery(hql);
//		q.setParameter(0, (byte) 10);
//		q.setParameter(1, "DIRECTOR");
//		System.out.println("------------------------");
//		System.out.println("Directores del dep 10, dos ");
//		List<Empleados> lista2 = q.list();
//		emple = new Empleados();
//		for (int i = 0; i < lista2.size(); i++) {
//			emple = lista2.get(i);
//			System.out.println(emple.getApellido());
//		}

		// El siguiente ejemplo Obtiene los empleados cuya fecha de alta es
		// 1991-12-03. Utiliza parámetro nombrado:
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
		String strFecha = "1991-12-03";
		formatoDelTexto.setLenient(false);
		java.util.Date fecha = null;
		try {
		fecha = (Date) formatoDelTexto.parse(strFecha);
		hql = "from Empleados where fechaAlt = :fechalta";
		q = session.createQuery(hql);
		q.setParameter("fechalta", fecha);

		List<Empleados> lista4 = q.list();

		System.out.println("------------------------");
		System.out.println("Empleados con fecha alta: " + strFecha);
		for (int i = 0; i < lista4.size(); i++) {
			emple = lista4.get(i);
			System.out.println(emple.getApellido());
		}
            } catch (ParseException ex) {
	             System.out.println("FECHA ERRÓNEA. NO SE PUEDE CONSULTAR");
			ex.printStackTrace();
		}

		//Lista de parámetros nombrados
		// El siguiente ejemplo asigna a un parámetro nombrado llamado :listadep una
		// colección de valores llamada numeros con los valores 10 y 20 para obtener
		// aquellos empleados cuyo número de departamento sea 10 o 20; se usa el método
		// setParameerList():
		List<Byte> numeros = new ArrayList<Byte>();
		numeros.add((byte) 10);
		numeros.add((byte) 20);

		hql = "from Empleados emp where emp.departamentos.deptNo in (:listadep) order by emp.departamentos.deptNo ";
		q = session.createQuery(hql);
		q.setParameterList("listadep", numeros);

		List<Empleados> lista3 = q.list();
		System.out.println("------------------------");
		System.out.println("Empleados del dep 10 y 20");
		for (int i =0; i < lista3.size(); i++) {
			emple = lista3.get(i);
			System.out.println(emple.getApellido());
		}
		session.close();
		

	}

}
