package chuletario;

import java.sql.ResultSet;
import java.sql.Statement;

public class VerificarTablaColumna {

	// Inicializar variables
	int numeroColumnasEncontradas = 0;
	boolean datosActualizados = false;

	// Realizar consulta
	Statement sentencia = conexion.createStatement();
	String sql = "select count(*) from all_tab_columns where owner = 'jardineriaad' and table_name = 'empleados' and column_name = 'numclientes'";
	ResultSet resul = sentencia.executeQuery(sql);

	// Saltar de línea
	resul.next();

	// Asignar el numero de coumnas encontradas
	numeroColumnasEncontradas = resul.getInt(1);

	// Comprobar si se ha encontrado la columna buscada
	if (numeroColumnasEncontradas > 0) {

		// Comprobar que exista la columna
		datosActualizados = true;

	}

	// Cerrar consulta
	resul.close();
	sentencia.close();

	// Devolver el valor booleano
	return datosActualizados;
	
}
